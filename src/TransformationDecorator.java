// Zadanie 4
// WZORZEC BUDOWNICZY (Builder Pattern)
// Problem: gdybyśmy chcieli obsługiwać translację, rotację i skalowanie przez konstruktor,
// mielibyśmy wiele parametrów i wiele kombinacji (np. tylko rotacja, tylko translacja itp.).
// Builder pozwala budować obiekt krok po kroku, dodając tylko to czego potrzebujemy.
// Zasada: użyj Buildera gdy obiekt ma wiele opcjonalnych parametrów konfiguracyjnych.

public class TransformationDecorator extends ShapeDecorator {

    // Łańcuch transformacji SVG, np. "translate(10,20) rotate(45,100,100)"
    private String transform;

    // Konstruktor prywatny - obiekt można stworzyć TYLKO przez Builder.
    // To wymusza użycie Buildera i zapewnia że transform jest zawsze poprawnie zbudowany.
    // Zasada: prywatny konstruktor + Builder = kontrola nad tworzeniem obiektu.
    private TransformationDecorator(Shape decoratedShape, String transform) {
        super(decoratedShape);
        this.transform = transform;
    }

    @Override
    public String toSvg(String params) {
        // Dodajemy atrybut transform="..." do tagu SVG.
        String transformParam = String.format("transform=\"%s\" %s", transform, params);
        return decoratedShape.toSvg(transformParam);
    }

    // KLASA WEWNĘTRZNA (inner class) - Builder jest zdefiniowany WEWNĄTRZ TransformationDecorator.
    // Ma przez to dostęp do prywatnego konstruktora TransformationDecorator.
    // Zasada: klasy wewnętrzne mają dostęp do prywatnych składowych klasy zewnętrznej -
    //         to pozwala Builderowi tworzyć obiekty których konstruktor jest prywatny.
    public static class Builder {

        // Pole transform budowane krok po kroku przez metody translate/rotate/scale.
        private String transform = "";

        // Każda metoda Buildera zwraca "this" (samego siebie).
        // Dzięki temu można łączyć wywołania w łańcuch (method chaining):
        //   new Builder().translate(...).rotate(...).scale(...).build(shape)
        // Zasada: metody Buildera zawsze zwracają this - to umożliwia płynny interfejs (fluent API).

        public Builder translate(Vec2 translation) {
            transform += String.format("translate(%f,%f) ", translation.x(), translation.y());
            return this;
        }

        public Builder rotate(float angle, Vec2 center) {
            transform += String.format("rotate(%f,%f,%f) ", angle, center.x(), center.y());
            return this;
        }

        public Builder scale(Vec2 scaleFactor) {
            transform += String.format("scale(%f,%f) ", scaleFactor.x(), scaleFactor.y());
            return this;
        }

        // build() kończy budowanie i tworzy właściwy TransformationDecorator.
        // Przyjmuje Shape który ma być udekorowany - Builder nie musi wiedzieć z góry co dekoruje.
        public TransformationDecorator build(Shape shape) {
            return new TransformationDecorator(shape, transform.trim());
        }
    }
}
