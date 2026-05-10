// Zadanie 1 + Zadanie 3
public class Polygon extends Shape {

    private static final Style DEFAULT_STYLE = new Style("none", "black", 1.0);

    private Point[] points;

    // Zadanie 1 - konstruktor z Style
    public Polygon(Point[] points, Style style) {
        super(style != null ? style : DEFAULT_STYLE);
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            this.points[i] = new Point(points[i]);
    }

    // Zadanie 1 - konstruktor bez Style (domyślny styl)
    public Polygon(Point[] points) {
        this(points, null);
    }

    // Konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        super(other.style);
        this.points = new Point[other.points.length];
        for (int i = 0; i < other.points.length; i++)
            this.points[i] = new Point(other.points[i]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon[\n");
        for (Point p : points) sb.append("  ").append(p).append("\n");
        sb.append("]");
        return sb.toString();
    }

    // Zadanie 1 - toSvg() uwzględnia styl
    @Override
    public String toSvg() {
        StringBuilder sb = new StringBuilder("<polygon points=\"");
        for (int i = 0; i < points.length; i++) {
            sb.append(points[i].getX()).append(",").append(points[i].getY());
            if (i < points.length - 1) sb.append(" ");
        }
        sb.append("\" ").append(style.toSvg()).append(" />");
        return sb.toString();
    }

    public BoundingBox boundingBox() {
        double minX = points[0].getX(), maxX = points[0].getX();
        double minY = points[0].getY(), maxY = points[0].getY();
        for (Point p : points) {
            if (p.getX() < minX) minX = p.getX();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getY() > maxY) maxY = p.getY();
        }
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }
}
