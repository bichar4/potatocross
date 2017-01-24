import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class potatocross extends PApplet {

int [][] box= new int [3][3];
int pad=20;
int bs=100;
boolean player;
int   winner=0;
int scoreDraw=0;
int scoreGreen=0;
int scoreRed=0;

public void setup()
{
  
  restart();
}
public void draw()
{
  background(0);
  rectt(0,0,width,height,10,color(255));
  for(int j=0;j<box.length;j++)
  for(int i=0;i<box[j].length;i++)
  {
    float x=pad+(pad+bs)*i;
    float y=pad+(pad+bs)*j;
    rectt(x,y,bs,bs,5,color(200));
    if(box[j][i]<0)
    {
      rectt(x,y,bs,bs,5,color(210,0,0));
    }
    if(box[j][i]>0)
    {
      rectt(x,y,bs,bs,5,color(00,210,0));
    }
    
  }
 textt(" RED="+scoreRed,32,0,width,50,color(255,0,0),20,LEFT);
 textt(" DRAW="+scoreDraw,0,0,width,50,color(0),20,CENTER);
  textt(" GREEN="+scoreGreen,-14,0,width-10,50,color(0,255,0),20,RIGHT);
  mainGameLogic();
  
    
    if(winner==5)
    {
      rectt(0,0,width,height,0,color(255,200));
      textt("THE GAME IS DRAW",0,(height/2)-60,width,50,color(0),30,CENTER);
        textt("PRESS SPACE TO CONTINUE",0,(height/2),width,100,color(0),30,CENTER);
       
    }
     if(winner==-2)
    {
      rectt(0,0,width,height,0,color(255,200));
      textt(" WINNER :RED",0,(height/2)-60,width,50,color(0),30,CENTER);
      textt("PRESS SPACE TO CONTINUE",0,(height/2),width,100,color(0),30,CENTER);
      
    }
     if(winner==2)
    {
      rectt(0,0,width,height,0,color(255,200));
      textt("WINNER :GREEN",0,(height/2)-60,width,50,color(0),30,CENTER);
      textt("PRESS SPACE TO CONTINUE",0,(height/2),width,100,color(0),30,CENTER);
    
    }
    if((winner==5 || winner ==2||winner==-2 )&&(keyPressed && key==' '))restart();
  
  
  
}
public void restart()
{
  for(int i=0;i<box.length;i++)
  for(int j=0;j<box[i].length;j++)
  box[i][j]=0;
    if(winner==5)   scoreDraw++;
    
     if(winner==-2) scoreRed++;
     if(winner==2)   scoreGreen++;
  //box=new int [3][3];
  winner=0;
  //spawning pieces
}
public void rectt(float x,float y,float w,float h, float r,int c)
{
  fill(c);
  rect(x,y,w,h,r);
}
public void mousePressed()
{
  int i,j;
  for(j=0;j<box.length;j++)
  for(i=0;i<box[j].length;i++)
  {
       float x1=pad+(pad+bs)*i;
    float y1=pad+(pad+bs)*j;
       float x2=(pad+bs)*(i+1);
    float y2=(pad+bs)*(j+1);
   if(box[j][i]==0)
    {
    if(((mouseX>x1&&mouseY>y1) && mouseX<x2 && mouseY<y2) && !( (winner==5 || winner ==2||winner==-2)))
    { 
      if (player==true)
      {
            box[j][i] =2;
      } 
      if(player==false)
      {
      box[j][i] =-2;
     }
     switchPlayer();
     
      
    }
    }
   
  } 
}


public void switchPlayer()
{
  if (player==true)
      {
            player=false;
      } 
      else
      player=true; 
}
public void mainGameLogic()
{
  //TODO enter the main game logic 
  if((box[0][0]>0 && box[0][1]>0 && box[0][2]>0) ||            //first row
      (box[1][0]>0 && box[1][1]>0 && box[1][2]>0) ||          //second row 
      (box[2][0]>0 && box[2][1]>0 && box[2][2]>0) ||          //third row 
      (box[0][0]>0 && box[1][0]>0 && box[2][0]>0) ||          //first column
      (box[0][1]>0 && box[1][1]>0 && box[2][1]>0) ||          //second column
      (box[0][2]>0 && box[1][2]>0 && box[2][2]>0) ||          //third column
      (box[0][0]>0 && box[1][1]>0 && box[2][2]>0) ||          //left diagonal
      (box[0][2]>0 && box[1][1]>0 && box[2][0]>0) )      //right diagonal
      {
        winner=2;            //player 1 is the winner 
      }
   else if((box[0][0]<0 && box[0][1]<0 && box[0][2]<0) ||            //first row
      (box[1][0]<0 && box[1][1]<0 && box[1][2]<0) ||          //second row 
      (box[2][0]<0 && box[2][1]<0 && box[2][2]<0) ||          //third row 
      (box[0][0]<0 && box[1][0]<0 && box[2][0]<0) ||          //first column
      (box[0][1]<0 && box[1][1]<0 && box[2][1]<0) ||          //second column
      (box[0][2]<0 && box[1][2]<0 && box[2][2]<0) ||          //third column
      (box[0][0]<0 && box[1][1]<0 && box[2][2]<0) ||          //left diagonal
      (box[0][2]<0 && box[1][1]<0 && box[2][0]<0) )      //right diagonal
      {
        winner=-2;        //player 2 is the winner 
      }  
 
 else if(box[0][0]!=0 && box[0][1]!=0 && box[0][2]!=0 &&
     box[1][0]!=0 && box[1][1]!=0 && box[1][2]!=0 &&
     box[2][0]!=0 && box[2][1]!=0 && box[2][2]!=0 )
     
     {
       winner=5;          //the game is draw 
     }
  
}
public void textt(String t,float x,float y,float w,float h,int c,float s,int align)
{
  fill(c);
  textAlign(align);
  textSize(s);
  text(t,x,y,w,h);

}
  
  public void settings() {  size(380,380); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "potatocross" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
