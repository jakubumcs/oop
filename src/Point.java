public class Point {

    public double x;
    public double y;

    // Zadanie 1 - konstruktor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Zadanie 1 - toString()
    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    // Zadanie 2 - toSvg()
    // Zwraca element SVG <circle> reprezentujący punkt jako mały okrąg
    public String toSvg() {
        return "<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"5\" fill=\"black\" />";
    }

    // Zadanie 3 - translate() – przesuwa oryginalny punkt
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    // Zadanie 3 - translated() – zwraca nowy, przesunięty punkt
    public Point translated(double dx, double dy) {
        return new Point(this.x + dx, this.y + dy);
    }
}
