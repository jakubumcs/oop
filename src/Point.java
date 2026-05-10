public class Point {

    // Zadanie 1 - prywatne pola
    private double x;
    private double y;

    // Zadanie 1 - konstruktor z argumentami
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Zadanie 1 - konstruktor bezargumentowy (0, 0)
    public Point() {
        this(0, 0);
    }

    // Zadanie 2 - konstruktor kopiujący
    public Point(Point other) {
        this(other.x, other.y);
    }

    // Zadanie 1 - akcesory (gettery)
    public double getX() { return x; }
    public double getY() { return y; }

    // Zadanie 1 - mutatory (settery)
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    public String toSvg() {
        return "<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"5\" fill=\"black\" />";
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public Point translated(double dx, double dy) {
        return new Point(this.x + dx, this.y + dy);
    }
}
