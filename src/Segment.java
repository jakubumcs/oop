// Zadanie 2
public class Segment {

    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }

    // Zadanie 2 - akcesory
    public Point getStart() { return new Point(start); }
    public Point getEnd()   { return new Point(end); }

    public double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Zadanie 2 - odcinek prostopadły, tej samej długości, przecinający się w środkach
    public Segment perpendicular() {
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0;
        double dx = (end.getX() - start.getX()) / 2.0;
        double dy = (end.getY() - start.getY()) / 2.0;
        // Obrót o 90° : (dx, dy) -> (-dy, dx)
        return new Segment(
            new Point(mx + dy, my - dx),
            new Point(mx - dy, my + dx)
        );
    }

    // Zadanie 2 - statyczna metoda wytwórcza kwadratu
    public static Polygon square(Segment diagonal, Style style) {
        Segment perp = diagonal.perpendicular();
        Point[] pts = {
            diagonal.getStart(),
            perp.getStart(),
            diagonal.getEnd(),
            perp.getEnd()
        };
        return new Polygon(pts, style);
    }

    @Override
    public String toString() {
        return "Segment(" + start + " -> " + end + ")";
    }

    public static Segment longest(Segment[] segments) {
        if (segments == null || segments.length == 0)
            throw new IllegalArgumentException("Tablica odcinków jest pusta.");
        Segment max = segments[0];
        for (int i = 1; i < segments.length; i++)
            if (segments[i].length() > max.length()) max = segments[i];
        return max;
    }
}
