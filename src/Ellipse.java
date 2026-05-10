// Ellipse również dziedziczy po Shape - tak samo jak Polygon.
// Obie klasy są "rodzajem" Shape, ale implementują toSvg() INACZEJ.
// To jest właśnie POLIMORFIZM - ta sama metoda, różne zachowanie w zależności od obiektu.
// Zasada: polimorfizm pozwala pisać kod ogólny (operujący na Shape),
//         który automatycznie robi właściwą rzecz dla każdego konkretnego kształtu.

public class Ellipse extends Shape {

    private Point center;
    private double radiusX;
    private double radiusY;

    public Ellipse(Point center, double radiusX, double radiusY, Style style) {
        // Wywołanie konstruktora Shape - obowiązkowe jako pierwsza instrukcja
        super(style);
        this.center = new Point(center);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    // Implementacja metody abstrakcyjnej z Shape.
    // Gdybyśmy jej nie napisali, kompilator zgłosiłby błąd -
    // nie można stworzyć nieabstrakcyjnej klasy bez implementacji wszystkich metod abstrakcyjnych.
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
