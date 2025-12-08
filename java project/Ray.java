import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;
import java.lang.foreign.ValueLayout;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.ADDRESS;

public class Ray {
    public static void main(String[] args) throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            // Ensure libraylib.so is discoverable (e.g., in the current dir with -Djava.library.path=.)
            System.loadLibrary("raylib");

            Linker linker = Linker.nativeLinker();
            SymbolLookup lookup = SymbolLookup.loaderLookup();

            // InitWindow(int width, int height, const char* title) -> void
            MethodHandle initWindow = linker.downcallHandle(
                    lookup.find("InitWindow").orElseThrow(() -> new UnsatisfiedLinkError("InitWindow not found")),
                    FunctionDescriptor.ofVoid(JAVA_INT, JAVA_INT, ADDRESS)
            );

            // CloseWindow(void) -> void
            MethodHandle closeWindow = linker.downcallHandle(
                    lookup.find("CloseWindow").orElseThrow(() -> new UnsatisfiedLinkError("CloseWindow not found")),
                    FunctionDescriptor.ofVoid()
            );

            // WindowShouldClose(void) -> int
            MethodHandle windowShouldClose = linker.downcallHandle(
                    lookup.find("WindowShouldClose").orElseThrow(() -> new UnsatisfiedLinkError("WindowShouldClose not found")),
                    FunctionDescriptor.of(JAVA_INT)
            );

            // BeginDrawing(void) -> void
            MethodHandle beginDrawing = linker.downcallHandle(
                    lookup.find("BeginDrawing").orElseThrow(() -> new UnsatisfiedLinkError("BeginDrawing not found")),
                    FunctionDescriptor.ofVoid()
            );

            // EndDrawing(void) -> void
            MethodHandle endDrawing = linker.downcallHandle(
                    lookup.find("EndDrawing").orElseThrow(() -> new UnsatisfiedLinkError("EndDrawing not found")),
                    FunctionDescriptor.ofVoid()
            );

            // ClearBackground(Color) -> void
            MethodHandle clearBackground = linker.downcallHandle(
                    lookup.find("ClearBackground").orElseThrow(() -> new UnsatisfiedLinkError("ClearBackground not found")),
                    FunctionDescriptor.ofVoid(ADDRESS)
            );

            // Optional: SetTargetFPS(int) -> void (keeps CPU sane)
            MethodHandle setTargetFPS = null;
            try {
                setTargetFPS = linker.downcallHandle(
                        lookup.find("SetTargetFPS").orElseThrow(),
                        FunctionDescriptor.ofVoid(JAVA_INT)
                );
            } catch (Throwable ignored) {
                // If not found, it's fine—loop will still run.
            }

            // Title as C string (JDK 21)
            MemorySegment title = arena.allocateFrom("hello raylib");

            // Open window
            initWindow.invokeExact(600, 600, title);

            // If available, set FPS to 60
            if (setTargetFPS != null) {
                setTargetFPS.invokeExact(60);
            }

            // Create a raylib Color { r, g, b, a } using 4 bytes
            MemorySegment red = arena.allocate(4);
            red.set(ValueLayout.JAVA_BYTE, 0, (byte) 255); // r
            red.set(ValueLayout.JAVA_BYTE, 1, (byte) 0);   // g
            red.set(ValueLayout.JAVA_BYTE, 2, (byte) 0);   // b
            red.set(ValueLayout.JAVA_BYTE, 3, (byte) 255); // a

            // Main render loop
            while ((int) windowShouldClose.invokeExact() == 0) {
                beginDrawing.invokeExact();
                clearBackground.invokeExact(red);
                endDrawing.invokeExact();
            }

            // Cleanup
            closeWindow.invokeExact();
        }
    }
}
