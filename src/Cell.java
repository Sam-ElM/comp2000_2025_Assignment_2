import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;
   boolean hasTree = false;

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

    


   public void paint(Graphics g, Point mousePos) {
   if(contains(mousePos)) {
     g.setColor(Color.GRAY);
     g.fillRect(x, y, size, size);
   } else {
     g.setColor(Color.GREEN);
     g.fillRect(x, y, size, size);

     g.setColor(new Color(0, 100, 0));
     int[][] offsets = {
       {5, 5}, {15, 5}, {25, 20}, {25, 25},
     };//dot sizes
     for (int i=0; i<offsets.length;i++){
       g.fillRect(x+offsets[i][0], y+offsets[i][1], 2, 2);//small dots
     }

     if(hasTree){
   g.setColor(new Color(139,69,19));
   g.fillRect(x+15,y+20,5,15);
   g.setColor(new Color(34,139,34));
   g.fillOval(x+5,y+5,25,25);

 }

   }

   g.setColor(Color.BLACK);
   g.drawRect(x, y, size, size);
 }


  @Override
  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }

  public int leftOfComparison(Cell c) {
    return Integer.compare(col, c.col);
  }

  public int aboveComparison(Cell c) {
    return Integer.compare(row, c.row);
  }
}
