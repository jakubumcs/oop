// KLASA ABSTRAKCYJNA (abstract class)
// Klasa abstrakcyjna to klasa, której NIE MOŻNA bezpośrednio tworzyć obiektów (new Shape() - błąd!).
// Służy jako "szablon" dla klas dziedziczących.
// Używamy jej gdy:
//   - kilka klas ma wspólne cechy (np. każdy kształt ma styl)
//   - chcemy wymusić na klasach potomnych implementację pewnych metod
// Zasada: jeśli masz grupę podobnych klas z wspólnym zachowaniem -> rozważ klasę abstrakcyjną.

public abstract class Shape {

    // "protected" oznacza: widoczne w tej klasie ORAZ we wszystkich klasach dziedziczących.
    // Gdyby było "private", klasy potomne (Polygon, Ellipse) nie miałyby dostępu do "style".
    // Gdyby było "public", każdy mógłby je zmienić z zewnątrz.
    // Zasada: protected to kompromis - ukryj przed światem, udostępnij potomkom.
    protected Style style;

    public Shape(Style style) {
        this.style = style;
    }

    // METODA ABSTRAKCYJNA
    // Nie ma ciała (brak { }). Każda klasa dziedzicząca MUSI ją zaimplementować.
    // Dzięki temu możemy być pewni, że każdy kształt umie wygenerować SVG,
    // nawet jeśli każdy robi to inaczej.
    // Zasada: metoda abstrakcyjna = kontrakt, który potomkowie muszą wypełnić.
    public abstract String toSvg();
}
