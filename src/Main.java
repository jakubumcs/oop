import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Zadanie 1 - SolidFilledPolygon (dziedziczenie po Polygon)
        SolidFilledPolygon filledTriangle = new SolidFilledPolygon(new Vec2[]{
                new Vec2(0, 0),
                new Vec2(300, 0),
                new Vec2(150, 250)
        }, "tomato");

        // Zadanie 2 - dekorator fill na Polygon i Ellipse
        // Ten sam dekorator działa dla obu typów - to przewaga wzorca Dekorator nad dziedziczeniem.
        Shape filledRectangle = new SolidFillShapeDecorator(
                new Polygon(new Vec2[]{
                        new Vec2(350, 0),
                        new Vec2(750, 0),
                        new Vec2(750, 200),
                        new Vec2(350, 200)
                }), "steelblue");

        Shape filledEllipse = new SolidFillShapeDecorator(
                new Ellipse(new Vec2(500, 700), 400, 100),
                "gold");

        // Zadanie 3 - dekoratory można nakładać na siebie (Stroke na SolidFill)
        Shape styledPentagon = new StrokeShapeDecorator(
                new SolidFillShapeDecorator(
                        new Polygon(new Vec2[]{
                                new Vec2(0, 260),
                                new Vec2(100, 460),
                                new Vec2(300, 560),
                                new Vec2(500, 460),
                                new Vec2(600, 260)
                        }), "mediumseagreen"),
                "darkgreen", 3.0);

        Shape styledEllipse = new StrokeShapeDecorator(filledEllipse, "darkorange", 2.0);

        // Zadanie 4 - Builder buduje TransformationDecorator krok po kroku (method chaining)
        Shape transformedTriangle = new TransformationDecorator.Builder()
                .translate(new Vec2(50, 50))
                .rotate(15, new Vec2(150, 125))
                .scale(new Vec2(0.8, 0.8))
                .build(filledTriangle);

        SvgScene scene = new SvgScene();
        scene.addShape(transformedTriangle);
        scene.addShape(filledRectangle);
        scene.addShape(styledPentagon);
        scene.addShape(styledEllipse);
        scene.save("result.svg");

        System.out.println("Zapisano result.svg");
        System.out.println(scene.toSvg());
    }
}
