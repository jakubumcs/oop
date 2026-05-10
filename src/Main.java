public class Main {

    public static void main(String[] args) throws Exception {
        // Zadanie 1 - Style + Polygon ze stylem
        Style redStyle = new Style("red", "darkred", 2.0);
        Point[] pts1 = {new Point(10, 10), new Point(100, 10), new Point(55, 80)};
        Polygon triangle = new Polygon(pts1, redStyle);
        System.out.println(triangle.toSvg());

        // Zadanie 1 - Polygon bez stylu (domyślny)
        Point[] pts2 = {new Point(120, 20), new Point(200, 20), new Point(160, 90)};
        Polygon triangle2 = new Polygon(pts2);
        System.out.println(triangle2.toSvg());

        // Zadanie 2 - perpendicular + square
        Segment diagonal = new Segment(new Point(50, 50), new Point(150, 150));
        Style blueStyle = new Style("lightblue", "blue", 1.5);
        Polygon sq = Segment.square(diagonal, blueStyle);
        System.out.println("Kwadrat: " + sq.toSvg());

        // Zadanie 4 - Ellipse
        Style ellipseStyle = new Style("yellow", "orange", 2.0);
        Ellipse ellipse = new Ellipse(new Point(100, 100), 60, 30, ellipseStyle);
        System.out.println(ellipse.toSvg());

        // Zadanie 4 - SvgScene z Shape (polimorfizm)
        SvgScene scene = new SvgScene();
        scene.addShape(triangle);
        scene.addShape(sq);
        scene.addShape(ellipse);

        scene.save("output.svg");
        System.out.println("Zapisano output.svg");
        System.out.println(scene.toSvg());
    }
}
