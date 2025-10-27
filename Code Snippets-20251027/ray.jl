const raylib="/home/young/lib/dynamic/raylib/src/libraylib.so"

struct Color
   R::Float32
   G::Float32
   B::Float32
   A::Float32
end

# void InitWindow(int width, int height, const char *title);
function InitWindow(width::Int32,height::Int32,title::String)
   @ccall raylib.InitWindow(width::Cint,height::Cint,title::Cstring)::Cvoid
end

#    bool WindowShouldClose(void);
function WindowShouldClose()
   @ccall raylib.WindowShouldClose()::Cuchar
end
# void ClearBackground(Color color);
function ClearBackground(color::Color)
   @ccall raylib.ClearBackground(color::Ref{Color})::Cvoid
end
#   void BeginDrawing(void);
function BeginDrawing()
   @ccall raylib.BeginDrawing()::Cvoid
end

#     void EndDrawing(void);
function EndDrawing()
   @ccall raylib.EndDrawing()::Cvoid
end

#void DrawCircle(int centerX, int centerY, float radius, Color color);
function DrawCircle(centerX::Int32,centerY::Int32,radius::Float32,color::Color)
   @ccall raylib.DrawCircle(centerX::Cint,centerY::Cint,radius::Cfloat,color::Ref{Color})::Cvoid
end


InitWindow(Int32(500),Int32(500),"Hello from julia")
const RC=Color(250.0f0,255.0f0,200.0f0,255.0f0)
const RED=Color(255.0f0,0f0,0f0,255.0f0)
#const BLACK = Color(0.0f0, 0.0f0, 0.0f0, 255.0f0)  # Fully opaque black
const BLACK = Color(0x00, 0x00, 0x00, 0xFF)
PosX=250;
PosY=250;
Radius=15.0;
SpeedX=10;
SpeedY=1;
while WindowShouldClose()==0
   BeginDrawing()
   #ClearBackground(BLACK)
   @ccall raylib.ClearBackground(Ref(BLACK)::Ref{Color})::Cvoid
   DrawCircle(Int32(PosX),Int32(PosY),Float32(Radius),RED)
   global PosX
   global PosY
   PosX+=SpeedX
   PosY+=SpeedY
   if PosX-Radius/2>500 || PosX+Radius/2 <0
      global SpeedX*=-1
   end
   if PosY-Radius/2>500 || PosY+Radius/2 <0
      global SpeedY*=-1
   end
   EndDrawing()
end