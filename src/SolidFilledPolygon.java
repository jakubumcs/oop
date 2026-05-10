// Zadanie 1
// DZIEDZICZENIE PO KLASIE KONKRETNEJ:
// SolidFilledPolygon dziedziczy po Polygon (nie po interfejsie Shape bezpośrednio).
// Polygon już implementuje Shape, więc SolidFilledPolygon też jest Shape - dziedziczenie jest przechodnie.
// Zasada: dziedzicz po klasie gdy chcesz rozszerzyć jej zachowanie, nie przepisywać go od zera.

public class SolidFilledPolygon extends Polygon {

    private String color;

    public SolidFilledPolygon(Vec2[] points, String color) {
        // super() wywołuje konstruktor klasy nadrzędnej (Polygon).
        // Musi być pierwszą instrukcją - Polygon sam zajmuje się kopiowaniem punktów.
        super(points);
        this.color = color;
    }

    // Nadpisujemy toSvg(String params) z klasy Polygon.
    // Zamiast rysować pusty polygon, dodajemy fill="kolor".
    // Wywołujemy super.toSvg(...) - nie przepisujemy logiki budowania tagu,
    // tylko dokładamy swój parametr i przekazujemy wyżej.
    // Zasada: jeśli nadpisujesz metodę i chcesz zachować zachowanie rodzica - użyj super.metoda().
    @Override
    public String toSvg(String params) {
        // String.format buduje napis fill="color" i dokłada do niego zewnętrzny params.
        // Następnie przekazuje całość do Polygon.toSvg() który wstawi to do tagu <polygon>.
        String fillParam = String.format("fill=\"%s\" %s", color, params);
        return super.toSvg(fillParam);
    }
}
