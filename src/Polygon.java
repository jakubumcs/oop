// DZIEDZICZENIE: "extends Shape"
// Polygon dziedziczy po Shape. Oznacza to że:
//   1. Polygon JEST Shape (relacja "is-a") - możemy traktować Polygon jak Shape
//   2. Polygon dostaje pole "style" z Shape (bo jest protected)
//   3. Polygon MUSI zaimplementować toSvg() bo Shape tego wymaga (metoda abstrakcyjna)
// Zasada: dziedzicz gdy klasa potomna "jest rodzajem" klasy bazowej.
//         Polygon jest rodzajem Shape - ma sens.
//         Gdyby Polygon "miał" Shape - użylibyśmy kompozycji, nie dziedziczenia.

public class Polygon extends Shape {

    private static final Style DEFAULT_STYLE = new Style("none", "black", 1.0);

    private Point[] points;

    // WYWOŁANIE KONSTRUKTORA KLASY BAZOWEJ: super(...)
    // Konstruktor klasy potomnej MUSI wywołać konstruktor klasy bazowej jako PIERWSZĄ instrukcję.
    // super(...) przekazuje argumenty do konstruktora Shape.
    // Zasada: zawsze wywołuj super() na początku konstruktora gdy dziedziczysz.
    public Polygon(Point[] points, Style style) {
        super(style != null ? style : DEFAULT_STYLE);
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            this.points[i] = new Point(points[i]);
    }

    public Polygon(Point[] points) {
        this(points, null);
    }

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

    // @Override - adnotacja informująca że ta metoda NADPISUJE metodę z klasy bazowej (Shape).
    // Kompilator sprawdzi czy taka metoda faktycznie istnieje w Shape - chroni przed literówkami.
    // Zasada: zawsze pisz @Override gdy nadpisujesz metodę - to dobra praktyka.
    @Override
    public String toSvg() {
        StringBuilder sb = new StringBuilder("<polygon points=\"");
        for (int i = 0; i < points.length; i++) {
            sb.append(points[i].getX()).append(",").append(points[i].getY());
            if (i < points.length - 1) sb.append(" ");
        }
        // Używamy "style" z klasy Shape (pole protected - dostępne tutaj bo jesteśmy potomkiem)
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
