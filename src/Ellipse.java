// Zadanie 4
public class Ellipse extends Shape {

    private Point center;
    private double radiusX;
    private double radiusY;

    public Ellipse(Point center, double radiusX, double radiusY, Style style) {
        super(style);
        this.center = new Point(center);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public String toSvg() {
        return "<ellipse cx=\"" + center.getX() + "\" cy=\"" + center.getY()
                + "\" rx=\"" + radiusX + "\" ry=\"" + radiusY
                + "\" " + style.toSvg() + " />";
    }

    @Override
    public String toString() {
        return "Ellipse(center=" + center + ", rx=" + radiusX + ", ry=" + radiusY + ")";
    }
}
