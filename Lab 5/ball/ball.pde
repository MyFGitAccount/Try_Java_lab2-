class Vector{
  float x;
  float y;
  Vector(float _x,float _y){
     this.x=_x;
     this.y=_y;
  }
  void add(Vector v){
    this.x+=v.x;
    this.y+=v.y;
  }
  void mult(Vector v){
     this.x*=v.x;
     this.y*=v.y;
  }
}

int width=640;
int height=640;
static int c=100;
Vector ballpos=new Vector(width/2,height/2);
Vector ballspeed=new Vector(20.345,1.7325);
float ballRadius=15;
public void updateColor(){
 c+=1;
 if(c==255) c=0;
 delay(10);
}
void setup(){
  size(640,640);
  smooth();
  background(45,45,45,45);
}

void draw(){
   stroke(0);
   fill(c,50+c,120+c);
   ellipse(ballpos.x,ballpos.y,ballRadius,ballRadius);
   ballpos.add(ballspeed);
   if(ballpos.x>width||ballpos.x<0){
      ballspeed.x*=-1;
   }
   if(ballpos.y>height||ballpos.y<0){
      ballspeed.y*=-1;
   }
   fill(c);
   updateColor();
   
   rect(ballpos.x,ballpos.y,width,height);
}
