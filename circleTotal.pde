import java.util.*;

int attempts = 0;
int numCircles = 100;
ArrayList<Circle> circles;
void setup() {
  fullScreen();
  //size(500, 500);
  circles = new ArrayList<Circle>();
  //c = new Circle(150, 150);
  //for (int x=0; x<numCircles; x++) {
  //  int xCord = floor(random(10, height-10));
  //  int yCord = floor(random(10, width-10));
  //  cs[x] = new Circle(xCord, yCord);
  //}
}


void draw() {
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

void makeCircle() {
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
