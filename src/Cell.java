import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;
  float rainLevel =0f, temperature=0.5f, windX=0f, windY=0f;
  boolean hasRain =false, hasTemp=false, hasWind=false;



  public void applyWeather(String attr, float value){
    switch(attr){
     case "rain"  -> { rainLevel = value; hasRain = true; }
    case "temp"  -> { temperature = value; hasTemp = true; }
    case "windx" -> { windX = value * 2f - 1f; hasWind = true; }
    case "windy" -> { windY = value * 2f - 1f; hasWind = true; }
    }
  }

  public void decay(float amt){
  if (hasRain) {
    rainLevel = Math.max(0f, rainLevel - amt);
    if (rainLevel == 0f) hasRain = false;
  }
}

  


  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

  public void paint(Graphics g, Point mousePos) {
  if (contains(mousePos)) g.setColor(Color.LIGHT_GRAY);
  else g.setColor(Color.WHITE);
  g.fillRect(x, y, size, size);


  float r = hasTemp ? temperature : 0f;
  float b = hasRain ? rainLevel : 0f;

  int blue = (int)(Math.min(1f, b * 2f) * 255);
    int red  = (int)(Math.min(1f, r * 0.6f) * 255);
    int alpha = 120;

  if (blue > 0 || red > 0) {
    g.setColor(new Color(red, 0, blue, alpha)); // semi-transparent
    g.fillRect(x, y, size, size);
  }

  if (hasWind) {
    g.setColor(Color.BLACK);
    int cx = x + size/2, cy = y + size/2;
    g.drawLine(cx, cy, cx + (int)(windX*8), cy - (int)(windY*8));
  }

  g.setColor(Color.BLACK);
  g.drawRect(x, y, size, size);
  }

  @Override
  public boolean contains(Point p) {
    return p != null && super.contains(p);
  }

  public int leftOfComparison(Cell c) {
    return Integer.compare(col, c.col);
  }

  public int aboveComparison(Cell c) {
    return Integer.compare(row, c.row);
  }
}


