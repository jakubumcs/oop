import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {

    // POLIMORFIZM W PRAKTYCE:
    // Tablica przechowuje referencje typu Shape, ale mogą wskazywać na Polygon LUB Ellipse.
    // Java "wie" w runtime jaki jest prawdziwy typ obiektu i wywoła właściwą metodę toSvg().
    // Zasada: programuj do interfejsu/klasy bazowej (Shape), nie do konkretnych implementacji.
    //         Dzięki temu SvgScene nie musi wiedzieć nic o Polygon ani Ellipse -
    //         wystarczy że wie że każdy Shape ma toSvg().
    private Shape[] shapes = new Shape[3];
    private int index = 0;

    public void addPolygon(Polygon polygon) {
        addShape(polygon);
    }

    // Przyjmuje Shape - czyli może dostać Polygon, Ellipse, lub dowolny przyszły kształt
    // bez zmiany tej metody. To jest siła polimorfizmu.
    public void addShape(Shape shape) {
        shapes[index % shapes.length] = shape;
        index++;
    }

    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        for (Shape s : shapes) {
            if (s != null) {
                // Tutaj działa polimorfizm:
                // s.toSvg() wywoła Polygon.toSvg() jeśli s jest Polygon,
                // albo Ellipse.toSvg() jeśli s jest Ellipse.
                // My nie musimy sprawdzać typu - Java robi to automatycznie.
                sb.append(s.toSvg()).append("\n");
            }
        }
        return sb.toString();
    }

    public void save(String path) throws IOException {
        double maxX = 0, maxY = 0;
        for (Shape s : shapes) {
            // "instanceof" sprawdza czy obiekt jest danego typu.
            // Używamy go tylko tam gdzie MUSIMY znać konkretny typ (tu: by wywołać boundingBox()).
            // Zasada: unikaj instanceof gdzie możliwe - to sygnał że polimorfizm mógłby pomóc.
            //         Tutaj Ellipse nie ma boundingBox(), więc musimy sprawdzić typ.
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
