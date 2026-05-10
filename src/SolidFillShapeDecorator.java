// Zadanie 2
// Konkretny dekorator dodający wypełnienie kolorem.
// Działa dla KAŻDEGO Shape - Polygon, Ellipse, a nawet innego dekoratora.
// Zasada: dekoratory można nakładać na siebie jak warstwy -
//         np. SolidFill(Stroke(Polygon)) doda i kolor i obrys.

public class SolidFillShapeDecorator extends ShapeDecorator {

    private String color;

    // Przyjmuje dowolny Shape (nie tylko Polygon!) - to przewaga nad dziedziczeniem.
    public SolidFillShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public String toSvg(String params) {
        // Dodajemy fill="color" do params i przekazujemy do dekorowanego obiektu.
        // decoratedShape może być Polygon, Ellipse, lub kolejny dekorator -
        // nie musimy tego wiedzieć. Polimorfizm zadba o resztę.
        String fillParam = String.format("fill=\"%s\" %s", color, params);
        return decoratedShape.toSvg(fillParam);
    }
}
