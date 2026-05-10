// Zadanie 2
// WZORZEC DEKORATOR (Decorator Pattern)
// Problem z dziedziczeniem (Zadanie 1): gdybyśmy chcieli dodać fill do Ellipse,
// musielibyśmy stworzyć SolidFilledEllipse - osobną klasę. Potem SolidFilledPolygon,
// SolidFilledEllipse, StrokePolygon, StrokeEllipse... liczba klas rośnie wykładniczo.
//
// Dekorator rozwiązuje to inaczej: opakowuje DOWOLNY obiekt Shape i dodaje do niego zachowanie.
// Jeden dekorator fill działa dla Polygon, Ellipse i wszystkich przyszłych kształtów.
// Zasada: użyj dekoratora gdy chcesz dodawać zachowania dynamicznie, bez tworzenia
//         podklas dla każdej kombinacji.

public class ShapeDecorator implements Shape {

    // "protected" - dostępne w tej klasie i w klasach dziedziczących po ShapeDecorator.
    // Dekorowany obiekt - może być Polygon, Ellipse, albo inny dekorator (dekoratory można składać!).
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    // Domyślnie po prostu przekazujemy wywołanie dalej do dekorowanego obiektu.
    // Klasy dziedziczące nadpiszą tę metodę i dodadzą swoje zachowanie.
    @Override
    public String toSvg(String params) {
        return decoratedShape.toSvg(params);
    }

    @Override
    public BoundingBox boundingBox() {
        // Bounding box nie zmienia się po dekoracji - delegujemy do oryginału.
        return decoratedShape.boundingBox();
    }
}
