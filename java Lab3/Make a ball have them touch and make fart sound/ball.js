const canvas=document.getElementById("canvas");
const ctx=canvas.getContext("2d");
const width=600;
const height=600;
canvas.width=width;
canvas.height=height;

let ballRadius=20;
let ballPosX=width/2;
let ballPosY=height/2;
let ballRotation=Math.PI*2;
let startAngle=0;
let endAngle=Math.PI*2;
let ballSpeedX=5;
let ballSpeedY=0.8;

let ball2Radius=20;
let ball2PosX=width/2;
let ball2PosY=height/2;
let ball2Rotation=Math.PI*2;
let start2Angle=0;
let end2Angle=Math.PI*2;
let ball2SpeedX=10;
let ball2SpeedY=0.6;
canvas.style.backgroundColor="grey"

let fartAudio=new Audio("fart-83471.mp3");
fartAudio.volume=1;
function drawCanvas(){
    ctx.beginPath();
    ctx.moveTo(0,0);
    ctx.lineTo(width,0);
    ctx.lineTo(width,height);
    ctx.lineTo(0,height);
    ctx.lineTo(0,0);
    ctx.stroke();
    ctx.closePath();
}
function drawBall(x,y,radius,rot,start,end,color){
    ctx.beginPath();
    ctx.ellipse(x,y,radius,radius,rot,start,end)
    ctx.fillStyle=color;
    ctx.fill();
    ctx.closePath();
}
function update(){
   ballPosX+=ballSpeedX;
   ballPosY+=ballSpeedY;
}
function update2(){
    ball2PosX+=ball2SpeedX;
    ball2PosY+=ball2SpeedY;
 }
function ballBoundX(){
    if(ballPosX>width||ballPosX<0){
        ballSpeedX*=-1;
        fartAudio.play();
    }
}
function ball2BoundX(){
    if(ball2PosX>width||ball2PosX<0){
        ball2SpeedX*=-1;
        fartAudio.play();
    }
}
function ballBoundY(){
    if(ballPosY>width||ballPosY<0){
        ballSpeedY*=-1;
        fartAudio.play();
    }
}
function ball2BoundY(){
    if(ball2PosY>height||ball2PosY<0){
        ball2SpeedY*=-1;
        fartAudio.play();
    }
}
function isTwoBallCollision(){
    if(ballPosX==ball2PosX&&ballPosY==ball2PosY){
        ballSpeedX*=-1;
        ballSpeedY*=-1;
        ball2SpeedX*=-1;
        ball2SpeedY*=-1;
    }
}
function animate(){
    ctx.clearRect(0,0,width,height);
    drawCanvas()
    drawBall(ballPosX,ballPosY,ballRadius,ballRotation,startAngle,endAngle,"red");
    drawBall(ball2PosX,ball2PosY,ball2Radius,ball2Rotation,start2Angle,end2Angle,"yellow")
    ballBoundX()
    ballBoundY()
    ball2BoundX()
    ball2BoundY()
    isTwoBallCollision()
    update()
    update2()
    requestAnimationFrame(animate)
}
drawCanvas()
animate()