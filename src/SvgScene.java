import java.io.FileWriter;
import java.io.IOException;

// Zadanie 4 - tablica Shape zamiast Polygon
public class SvgScene {

    private Shape[] shapes = new Shape[3];
    private int index = 0;

    public void addPolygon(Polygon polygon) {
        addShape(polygon);
    }

    // Zadanie 4 - ogólna metoda dodająca dowolny Shape
    public void addShape(Shape shape) {
        shapes[index % shapes.length] = shape;
        index++;
    }

    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        for (Shape s : shapes) {
            if (s != null) sb.append(s.toSvg()).append("\n");
        }
        return sb.toString();
    }

    public void save(String path) throws IOException {
        double maxX = 0, maxY = 0;
        for (Shape s : shapes) {
            if (s instanceof Polygon p) {
                BoundingBox bb = p.boundingBox();
                if (bb.x() + bb.width()  > maxX) maxX = bb.x() + bb.width();
                if (bb.y() + bb.height() > maxY) maxY = bb.y() + bb.height();
            }
        }

        String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + maxX + "\" height=\"" + maxY + "\">\n"
                + toSvg()
                + "</svg>";

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(svg);
        }
    }
}
