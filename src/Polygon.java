public class Polygon {

    // Zadanie 3 - prywatna tablica punktów
    private Point[] points;

    // Zadanie 3 - konstruktor (Zadanie 4: głęboka kopia tablicy i punktów)
    public Polygon(Point[] points) {
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = new Point(points[i]); // głęboka kopia każdego punktu
        }
    }

    // Zadanie 4 - konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        this.points = new Point[other.points.length];
        for (int i = 0; i < other.points.length; i++) {
            this.points[i] = new Point(other.points[i]);
        }
    }

    // Zadanie 3 - toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon[\n");
        for (Point p : points) {
            sb.append("  ").append(p).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    // Zadanie 3 - toSvg()
    public String toSvg() {
        StringBuilder sb = new StringBuilder("<polygon points=\"");
        for (int i = 0; i < points.length; i++) {
            sb.append(points[i].getX()).append(",").append(points[i].getY());
            if (i < points.length - 1) sb.append(" ");
        }
        sb.append("\" fill=\"none\" stroke=\"black\" stroke-width=\"1\" />");
        return sb.toString();
    }

    // Zadanie 7 - boundingBox()
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
