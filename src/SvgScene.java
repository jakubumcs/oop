import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {

    // Zadanie 5 - prywatna tablica 3 referencji do Polygon
    private Polygon[] polygons = new Polygon[3];
    private int index = 0;

    // Zadanie 5 - addPolygon() z nadpisywaniem od początku gdy brak miejsca
    public void addPolygon(Polygon polygon) {
        polygons[index % 3] = polygon;
        index++;
    }

    // Zadanie 6 - toSvg() bez rozmiaru (pomocnicza)
    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        for (Polygon p : polygons) {
            if (p != null) {
                sb.append(p.toSvg()).append("\n");
            }
        }
        return sb.toString();
    }

    // Zadanie 8 - save()
    public void save(String path) throws IOException {
        // Oblicz rozmiar na podstawie boundingBox wszystkich wielokątów
        double maxX = 0, maxY = 0;
        for (Polygon p : polygons) {
            if (p != null) {
                BoundingBox bb = p.boundingBox();
                if (bb.x() + bb.width() > maxX)  maxX = bb.x() + bb.width();
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
