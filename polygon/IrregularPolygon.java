package polygon;

import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
      myPolygon.add(aPoint);    
    }

    public double perimeter() {
        double perimeter = 0;
        for (int i = 0; i < myPolygon.size(); i++) {
            if (i == myPolygon.size() - 1) {
                perimeter += myPolygon.get(i).distance(myPolygon.get(0));
            } 
            else {
                perimeter += myPolygon.get(i).distance(myPolygon.get(i + 1));
            }
        }
        return perimeter;
}

    public double area() {
        
        // TODO: Calculate the area.
        Double area = 0.0;
            for (int i = 0; i < myPolygon.size(); i++) {
                if (i == myPolygon.size() - 1) {
                    area += (myPolygon.get(i).getX() * myPolygon.get(0).getY()) - (myPolygon.get(0).getX() * myPolygon.get(i).getY());
                } 
                else {
                    area += (myPolygon.get(i).getX() * myPolygon.get(i + 1).getY()) - (myPolygon.get(i + 1).getX() * myPolygon.get(i).getY());
                }
            }   
        return Math.abs(area/2.0);
    }

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            // TODO: Draw the polygon.
            // Documents: https://pavao.org/compsci/gpdraw/html/gpdraw/DrawingTool.html
            // DrawingTool myDrawingTool = new DrawingTool(new SketchPad(500, 500));
            // myDrawingTool.move(50, 50);
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }

}
