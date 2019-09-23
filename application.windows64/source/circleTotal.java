import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class circleTotal extends PApplet {



int attempts = 0;
int numCircles = 100;
ArrayList<Circle> circles;
public void setup() {
  
  //size(500, 500);
  circles = new ArrayList<Circle>();
  //c = new Circle(150, 150);
  //for (int x=0; x<numCircles; x++) {
  //  int xCord = floor(random(10, height-10));
  //  int yCord = floor(random(10, width-10));
  //  cs[x] = new Circle(xCord, yCord);
  //}
}


public void draw() {
  //stroke(255);
  background(0);
  noFill();
  if (attempts < 15) {
    makeCircle();
  }
  else{
    circles.clear();
    background(0);
    attempts = 0;
  }
  for (Circle c : circles) {
    c.show();
    c.grow();
    if (!c.edges()) {
      c.strokeColor = false;
      c.growing = false;
    } else {
      for (Circle other : circles) {
        if (c != other) {
          float d = dist(other.x, other.y, c.x, c.y);
          if (d < other.r + c.r) {
            c.strokeColor = false;
            c.growing = false;
            break;
          }
        }
      }
    }
  }
}

public void makeCircle() {
  float xCord = random(width);
  float yCord = random(height);

  for (Circle testC : circles) {
    if (dist(testC.x, testC.y, xCord, yCord) <= testC.r) {
      attempts++;
      return;
    }
  }
  Circle c = new Circle(xCord, yCord);
  circles.add(c);
  attempts = 0;
}
class Circle {
  boolean strokeColor = true;
  float x;
  float y;
  float r = 0;

  boolean growing = true;

  Circle(float inX, float inY) {
    x = inX;
    y = inY;
  }

  public void show() {
    if (strokeColor) {
      fill(map(r,1,75,255,100),0,0);
      //fill(random(255),random(255),random(255));
      stroke(map(r,1,75,255,100), 0, 0);
    } else {
      noFill();
      stroke(240);
      //stroke(random(255),random(255),random(255));
    }
    ellipse(x, y, r*2, r*2);
  }

  public boolean edges() {
    if (x+r > width || y+r > height || r > x || r > y) {
      return false;
    }

    return true;
  }

  public void grow() {
    if (growing) {
      stroke(255, 0, 0);
      r = r+1;
      show();
    } else {
      stroke(50);
    }
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "circleTotal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
