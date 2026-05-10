public class Segment {

    public Point start;
    public Point end;

    // Zadanie 4 - konstruktor
    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    // Zadanie 4 - length()
    public double length() {
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Zadanie 5 - longest() (przeniesione z Main do Segment)
    public static Segment longest(Segment[] segments) {
        if (segments == null || segments.length == 0) {
            throw new IllegalArgumentException("Tablica odcinków jest pusta.");
        }
        Segment maxSegment = segments[0];
        for (int i = 1; i < segments.length; i++) {
            if (segments[i].length() > maxSegment.length()) {
                maxSegment = segments[i];
            }
        }
        return maxSegment;
    }

    @Override
    public String toString() {
        return "Segment(" + start + " -> " + end + ")";
    }
}
