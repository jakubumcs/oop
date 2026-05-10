public class Style {

    // Pola są "final" - oznacza to że po ustawieniu w konstruktorze nie można ich zmienić.
    // Używamy tego gdy obiekt ma być NIEMUTOWALNY (immutable) - jego stan nie zmienia się po utworzeniu.
    // Zasada: jeśli obiekt nie musi się zmieniać, zrób go immutable - to bezpieczniejsze.
    public final String fillColor;
    public final String strokeColor;
    public final Double strokeWidth;

    public Style(String fillColor, String strokeColor, Double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public String toSvg() {
        return "fill=\"" + fillColor + "\" stroke=\"" + strokeColor + "\" stroke-width=\"" + strokeWidth + "\"";
    }
}
