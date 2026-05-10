public class Segment {

    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }

    public Point getStart() { return new Point(start); }
    public Point getEnd()   { return new Point(end); }

    public double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Segment perpendicular() {
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0;
        double dx = (end.getX() - start.getX()) / 2.0;
        double dy = (end.getY() - start.getY()) / 2.0;
        return new Segment(
            new Point(mx + dy, my - dx),
            new Point(mx - dy, my + dx)
        );
    }

    // STATYCZNA METODA WYTWÓRCZA (static factory method)
    // Zamiast new Polygon(...) wywołujemy Segment.square(...).
    // Zalety: ma opisową nazwę ("square" mówi co robi), może zwrócić null lub podtyp,
    //         ukrywa złożoność tworzenia obiektu.
    // Zasada: gdy tworzenie obiektu jest skomplikowane lub wymaga nazwy - użyj metody wytwórczej.
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
