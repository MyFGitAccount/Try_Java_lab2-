import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;
import java.lang.foreign.ValueLayout;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_FLOAT;
import static java.lang.foreign.ValueLayout.ADDRESS;

public class SpaceInvadersFFI {
    // raylib key codes (KeyboardKey enum)
    private static final int KEY_LEFT = 263;
    private static final int KEY_RIGHT = 262;
    private static final int KEY_SPACE = 32;
    private static final int FLAG_WINDOW_UNDECORATED = 2; // not used, example
    private static final int FLAG_WINDOW_RESIZABLE   = 4; // not used, example

    public static void main(String[] args) throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            System.loadLibrary("raylib");

            Linker linker = Linker.nativeLinker();
            SymbolLookup lookup = SymbolLookup.loaderLookup();

            // Bind core functions
            MethodHandle SetConfigFlags   = down(linker, lookup, "SetConfigFlags", FunctionDescriptor.ofVoid(JAVA_INT));
            MethodHandle InitWindow       = down(linker, lookup, "InitWindow", FunctionDescriptor.ofVoid(JAVA_INT, JAVA_INT, ADDRESS));
            MethodHandle CloseWindow      = down(linker, lookup, "CloseWindow", FunctionDescriptor.ofVoid());
            MethodHandle WindowShouldClose= down(linker, lookup, "WindowShouldClose", FunctionDescriptor.of(JAVA_INT));
            MethodHandle BeginDrawing     = down(linker, lookup, "BeginDrawing", FunctionDescriptor.ofVoid());
            MethodHandle EndDrawing       = down(linker, lookup, "EndDrawing", FunctionDescriptor.ofVoid());
            MethodHandle ClearBackground  = down(linker, lookup, "ClearBackground", FunctionDescriptor.ofVoid(ADDRESS));
            MethodHandle DrawRectangle    = down(linker, lookup, "DrawRectangle", FunctionDescriptor.ofVoid(JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, ADDRESS));
            MethodHandle DrawCircle       = down(linker, lookup, "DrawCircle", FunctionDescriptor.ofVoid(JAVA_INT, JAVA_INT, JAVA_FLOAT, ADDRESS));
            MethodHandle DrawText         = down(linker, lookup, "DrawText", FunctionDescriptor.ofVoid(ADDRESS, JAVA_INT, JAVA_INT, JAVA_INT, ADDRESS));
            MethodHandle SetTargetFPS     = down(linker, lookup, "SetTargetFPS", FunctionDescriptor.ofVoid(JAVA_INT));
            MethodHandle GetFrameTime     = down(linker, lookup, "GetFrameTime", FunctionDescriptor.of(JAVA_FLOAT));
            MethodHandle IsKeyDown        = down(linker, lookup, "IsKeyDown", FunctionDescriptor.of(JAVA_INT, JAVA_INT));
            MethodHandle IsKeyPressed     = down(linker, lookup, "IsKeyPressed", FunctionDescriptor.of(JAVA_INT, JAVA_INT));
            MethodHandle IsWindowFocused  = down(linker, lookup, "IsWindowFocused", FunctionDescriptor.of(JAVA_INT));

            // Colors (raylib Color: 4 bytes RGBA)
            try (Arena colors = Arena.ofConfined()) {
                MemorySegment BLACK  = rgba(colors, 0, 0, 0, 255);
                MemorySegment WHITE  = rgba(colors, 255, 255, 255, 255);
                MemorySegment RED    = rgba(colors, 255, 60, 60, 255);
                MemorySegment GREEN  = rgba(colors, 60, 255, 120, 255);
                MemorySegment BLUE   = rgba(colors, 60, 140, 255, 255);
                MemorySegment YELLOW = rgba(colors, 255, 220, 0, 255);

                int W = 640, H = 800;

                // Configure window before InitWindow (helps with input/focus consistency)
                int flags = 0; // you could OR flags like FLAG_WINDOW_RESIZABLE, etc.
                SetConfigFlags.invokeExact(flags);

                MemorySegment title = arena.allocateFrom("Space Out - FFI");
                InitWindow.invokeExact(W, H, title);
                SetTargetFPS.invokeExact(60);

                // Player
                int playerW = 60, playerH = 12;
                float playerX = (W - playerW)/2.0f;
                float playerY = H - 70;
                float playerSpeed = 280.0f;
                int lives = 3;

                // Player bullets
                int maxBullets = 10;
                float[] bX = new float[maxBullets];
                float[] bY = new float[maxBullets];
                boolean[] bLive = new boolean[maxBullets];
                float bSpeed = 560.0f;
                float bRadius = 4.0f;
                long lastFireMs = 0;
                long fireCooldownMs = 120;

                // Enemy grid
                int cols = 10, rows = 5;
                float eW = 40, eH = 22;
                float gapX = 16, gapY = 16;
                float baseX = 30, baseY = 100;
                boolean[][] alive = new boolean[rows][cols];
                for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) alive[r][c] = true;

                // Enemy movement
                float swarmOffsetX = 0;
                float swarmSpeed = 70.0f;
                int swarmDir = 1;
                float dropY = 18.0f;
                float margin = 18.0f;

                // Enemy bullets
                int maxEnemyBullets = 7;
                float[] ebX = new float[maxEnemyBullets];
                float[] ebY = new float[maxEnemyBullets];
                boolean[] ebLive = new boolean[maxEnemyBullets];
                float ebSpeed = 260.0f;
                float ebRadius = 4.0f;
                long lastEnemyShotMs = 0;
                long enemyShotCooldownMs = 380;

                int score = 0;
                int wave = 1;
                boolean gameOver = false;

                while ((int) WindowShouldClose.invokeExact() == 0) {
                    float dt = (float) GetFrameTime.invokeExact();

                    boolean focused = ((int) IsWindowFocused.invokeExact()) != 0;
                    boolean left = focused && (((int) IsKeyDown.invokeExact(KEY_LEFT)) != 0);
                    boolean right = focused && (((int) IsKeyDown.invokeExact(KEY_RIGHT)) != 0);
                    boolean shootPressed = focused && (((int) IsKeyPressed.invokeExact(KEY_SPACE)) != 0);
                    boolean shootHeld = focused && (((int) IsKeyDown.invokeExact(KEY_SPACE)) != 0);

                    if (!gameOver) {
                        // Player move
                        if (left) playerX -= playerSpeed * dt;
                        if (right) playerX += playerSpeed * dt;
                        if (playerX < 0) playerX = 0;
                        if (playerX > W - playerW) playerX = W - playerW;

                        // Player shoot (both pressed and held with cooldown)
                        long now = System.currentTimeMillis();
                        if ((shootPressed || shootHeld) && (now - lastFireMs) >= fireCooldownMs) {
                            int slot = firstFree(bLive);
                            if (slot >= 0) {
                                bLive[slot] = true;
                                bX[slot] = playerX + playerW/2.0f;
                                bY[slot] = playerY - 6;
                                lastFireMs = now;
                            }
                        }

                        // Move player bullets
                        for (int i = 0; i < maxBullets; i++) {
                            if (!bLive[i]) continue;
                            bY[i] -= bSpeed * dt;
                            if (bY[i] < -10) bLive[i] = false;
                        }

                        // Swarm movement
                        swarmOffsetX += swarmSpeed * swarmDir * dt;

                        float leftEdge = W, rightEdge = 0;
                        for (int r = 0; r < rows; r++) {
                            for (int c = 0; c < cols; c++) {
                                if (!alive[r][c]) continue;
                                float ex = baseX + swarmOffsetX + c * (eW + gapX);
                                leftEdge = Math.min(leftEdge, ex);
                                rightEdge = Math.max(rightEdge, ex + eW);
                            }
                        }
                        if (leftEdge < margin) {
                            swarmDir = 1;
                            baseY += dropY;
                            swarmOffsetX = margin - (leftEdge - swarmOffsetX);
                        } else if (rightEdge > W - margin) {
                            swarmDir = -1;
                            baseY += dropY;
                            swarmOffsetX = (W - margin) - (rightEdge - swarmOffsetX);
                        }

                        // Enemy shooting
                        if ((System.currentTimeMillis() - lastEnemyShotMs) >= enemyShotCooldownMs) {
                            int shooterIndex = pickRandomAlive(alive);
                            if (shooterIndex >= 0) {
                                int rr = shooterIndex / cols;
                                int cc = shooterIndex % cols;
                                float ex = baseX + swarmOffsetX + cc * (eW + gapX) + eW/2.0f;
                                float ey = baseY + rr * (eH + gapY) + eH;
                                int slot = firstFree(ebLive);
                                if (slot >= 0) {
                                    ebLive[slot] = true;
                                    ebX[slot] = ex;
                                    ebY[slot] = ey;
                                    lastEnemyShotMs = System.currentTimeMillis();
                                }
                            }
                        }

                        // Move enemy bullets
                        for (int i = 0; i < maxEnemyBullets; i++) {
                            if (!ebLive[i]) continue;
                            ebY[i] += ebSpeed * dt;
                            if (ebY[i] > H + 10) ebLive[i] = false;
                        }

                        // Collisions: player bullets vs enemies
                        for (int i = 0; i < maxBullets; i++) {
                            if (!bLive[i]) continue;
                            for (int r = 0; r < rows; r++) {
                                for (int c = 0; c < cols; c++) {
                                    if (!alive[r][c]) continue;
                                    float ex = baseX + swarmOffsetX + c * (eW + gapX);
                                    float ey = baseY + r * (eH + gapY);
                                    if (circleRect(bX[i], bY[i], bRadius, ex, ey, eW, eH)) {
                                        alive[r][c] = false;
                                        bLive[i] = false;
                                        score += 10;
                                        break;
                                    }
                                }
                                if (!bLive[i]) break;
                            }
                        }

                        // Collisions: enemy bullets vs player
                        for (int i = 0; i < maxEnemyBullets; i++) {
                            if (!ebLive[i]) continue;
                            if (circleRect(ebX[i], ebY[i], ebRadius, playerX, playerY, playerW, playerH)) {
                                ebLive[i] = false;
                                lives--;
                                if (lives <= 0) gameOver = true;
                            }
                        }

                        // Lose condition: enemies reach player line
                        float lowestY = -1;
                        for (int r = 0; r < rows; r++) {
                            for (int c = 0; c < cols; c++) {
                                if (!alive[r][c]) continue;
                                float ey = baseY + r * (eH + gapY);
                                lowestY = Math.max(lowestY, ey + eH);
                            }
                        }
                        if (lowestY >= playerY - 6) gameOver = true;

                        // Win condition: wave cleared
                        if (cleared(alive)) {
                            wave++;
                            for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) alive[r][c] = true;
                            baseY = 100;
                            swarmOffsetX = 0;
                            swarmDir = 1;
                            swarmSpeed += 20.0f;
                            for (int i = 0; i < maxBullets; i++) bLive[i] = false;
                            for (int i = 0; i < maxEnemyBullets; i++) ebLive[i] = false;
                            lives = Math.min(3, lives + 1);
                        }
                    }

                    // Render
                    BeginDrawing.invokeExact();
                    ClearBackground.invokeExact(BLACK);

                    drawText(DrawText, arena, "Score: " + score, 16, 14, 20, WHITE);
                    drawText(DrawText, arena, "Wave: " + wave, W - 140, 14, 20, WHITE);
                    drawText(DrawText, arena, "Lives: " + lives, W/2 - 40, 14, 20, WHITE);
                    if (!focused) {
                        drawTextCenter(DrawText, arena, "Click the window to focus for input", W, 48, 18, YELLOW);
                    }

                    DrawRectangle.invokeExact((int) playerX, (int) playerY, playerW, playerH, BLUE);

                    for (int i = 0; i < maxBullets; i++) {
                        if (bLive[i]) DrawCircle.invokeExact((int) bX[i], (int) bY[i], bRadius, YELLOW);
                    }
                    for (int i = 0; i < maxEnemyBullets; i++) {
                        if (ebLive[i]) DrawCircle.invokeExact((int) ebX[i], (int) ebY[i], ebRadius, RED);
                    }
                    for (int r = 0; r < rows; r++) {
                        for (int c = 0; c < cols; c++) {
                            if (!alive[r][c]) continue;
                            float ex = baseX + swarmOffsetX + c * (eW + gapX);
                            float ey = baseY + r * (eH + gapY);
                            DrawRectangle.invokeExact((int) ex, (int) ey, (int) eW, (int) eH, GREEN);
                        }
                    }

                    EndDrawing.invokeExact();
                }

                CloseWindow.invokeExact();
            }
        }
    }

    // --- Helpers ---
    private static MethodHandle down(Linker linker, SymbolLookup lookup, String name, FunctionDescriptor fd) {
        return linker.downcallHandle(
                lookup.find(name).orElseThrow(() -> new UnsatisfiedLinkError(name + " not found")),
                fd
        );
    }

    private static MemorySegment rgba(Arena arena, int r, int g, int b, int a) {
        MemorySegment seg = arena.allocate(4);
        seg.set(ValueLayout.JAVA_BYTE, 0, (byte) r);
        seg.set(ValueLayout.JAVA_BYTE, 1, (byte) g);
        seg.set(ValueLayout.JAVA_BYTE, 2, (byte) b);
        seg.set(ValueLayout.JAVA_BYTE, 3, (byte) a);
        return seg;
    }

    private static int firstFree(boolean[] live) {
        for (int i = 0; i < live.length; i++) if (!live[i]) return i;
        return -1;
    }

    private static boolean cleared(boolean[][] alive) {
        for (int r = 0; r < alive.length; r++) {
            for (int c = 0; c < alive[r].length; c++) {
                if (alive[r][c]) return false;
            }
        }
        return true;
    }

    private static boolean circleRect(float cx, float cy, float cr, float rx, float ry, float rw, float rh) {
        float nx = clamp(cx, rx, rx + rw);
        float ny = clamp(cy, ry, ry + rh);
        float dx = cx - nx;
        float dy = cy - ny;
        return (dx * dx + dy * dy) <= (cr * cr);
    }

    private static float clamp(float v, float lo, float hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    private static void drawText(MethodHandle DrawText, Arena arena, String s, int x, int y, int size, MemorySegment color) throws Throwable {
        MemorySegment cstr = arena.allocateFrom(s);
        DrawText.invokeExact(cstr, x, y, size, color);
    }

    private static void drawTextCenter(MethodHandle DrawText, Arena arena, String s, int W, int y, int size, MemorySegment color) throws Throwable {
        int approxW = (int) (s.length() * size * 0.6);
        int x = (W - approxW) / 2;
        drawText(DrawText, arena, s, x, y, size, color);
    }
}
