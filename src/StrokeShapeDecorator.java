// Zadanie 3
// Dekorator dodający obrys (stroke). Działa identycznie jak SolidFillShapeDecorator,
// ale dodaje inne atrybuty SVG.
// Kluczowa zaleta: możemy nałożyć oba dekoratory razem:
//   new StrokeShapeDecorator(new SolidFillShapeDecorator(polygon, "red"), "black", 2.0)
// To daje polygon z czerwonym wypełnieniem i czarnym obrysem - bez nowej klasy!

public class StrokeShapeDecorator extends ShapeDecorator {

    private String color;
    private double width;

    public StrokeShapeDecorator(Shape decoratedShape, String color, double width) {
        super(decoratedShape);
        this.color = color;
        this.width = width;
    }

    @Override
    public String toSvg(String params) {
        // %f formatuje liczbę zmiennoprzecinkową (double).
        // Dokładamy stroke i stroke-width do params, reszta idzie dalej w dół łańcucha dekoratorów.
        String strokeParam = String.format("stroke=\"%s\" stroke-width=\"%f\" %s", color, width, params);
        return decoratedShape.toSvg(strokeParam);
    }
}
