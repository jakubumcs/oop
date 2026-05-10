public interface Shape {
    BoundingBox boundingBox();

    // Zadanie 1 - toSvg() przyjmuje teraz parametr "params" (dodatkowe atrybuty do tagu SVG).
    // Dzięki temu klasy dziedziczące mogą wstrzyknąć np. fill="red" do tagu bez przepisywania
    // całej logiki budowania tagu.
    // Zasada: interfejs definiuje KONTRAKT - każda klasa implementująca musi mieć obie wersje toSvg.

    String toSvg(String params);

    // METODA DOMYŚLNA W INTERFEJSIE (default method)
    // Od Java 8 interfejs może mieć metody z ciałem oznaczone "default".
    // Dzięki temu stary kod wywołujący toSvg() nadal działa bez zmian -
    // domyślnie wywołuje toSvg("") z pustym parametrem.
    // Zasada: "default" w interfejsie używamy gdy chcemy dodać metodę bez łamania
    //         istniejących implementacji (wsteczna kompatybilność).
    default String toSvg() {
        return toSvg("");
    }
}
