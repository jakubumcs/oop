public class Segment {

    // Zadanie 2 - prywatne pola
    private Point start;
    private Point end;

    // Zadanie 2 - konstruktor z głęboką kopią (niewrażliwość na zmianę zewnętrznych punktów)
    public Segment(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }

    public double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Zadanie 2 - toString()
    @Override
    public String toString() {
        return "Segment(" + start + " -> " + end + ")";
    }

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
}
