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

  void show() {
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

  boolean edges() {
    if (x+r > width || y+r > height || r > x || r > y) {
      return false;
    }

    return true;
  }

  void grow() {
    if (growing) {
      stroke(255, 0, 0);
      r = r+1;
      show();
    } else {
      stroke(50);
    }
  }
}
