public class Main {

    public static void main(String[] args) throws Exception {
        // Zadanie 1 - Point z getterami/setterami
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point();          // (0, 0)
        p2.setX(7.0);
        p2.setY(1.0);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.toSvg());

        // Zadanie 2 - Segment niewrażliwy na zmianę punktów
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        Segment s1 = new Segment(a, b);
        a.setX(100); // zmiana oryginału NIE wpływa na segment
        System.out.println("s1 length (powinno być 5.0): " + s1.length());

        Segment s2 = new Segment(new Point(1, 1), new Point(6, 13));
        Segment s3 = new Segment(new Point(-2, -2), new Point(1, 2));
        System.out.println("Najdłuższy: " + Segment.longest(new Segment[]{s1, s2, s3}));

        // Zadanie 3/4 - Polygon
        Point[] pts = {new Point(10, 10), new Point(100, 10), new Point(55, 80)};
        Polygon triangle = new Polygon(pts);
        pts[0].setX(999); // zmiana oryginału NIE wpływa na polygon (głęboka kopia)
        System.out.println(triangle);
        System.out.println(triangle.toSvg());

        // Zadanie 4 - konstruktor kopiujący
        Polygon copy = new Polygon(triangle);
        System.out.println("Kopia: " + copy);

        // Zadanie 7 - BoundingBox
        BoundingBox bb = triangle.boundingBox();
        System.out.println("BoundingBox: x=" + bb.x() + " y=" + bb.y()
                + " w=" + bb.width() + " h=" + bb.height());

        // Zadanie 5/6 - SvgScene
        Point[] pts2 = {new Point(120, 20), new Point(200, 20), new Point(160, 90)};
        Polygon triangle2 = new Polygon(pts2);

        SvgScene scene = new SvgScene();
        scene.addPolygon(triangle);
        scene.addPolygon(triangle2);
        System.out.println(scene.toSvg());

        // Zadanie 8 - zapis do pliku
        scene.save("output.svg");
        System.out.println("Zapisano output.svg");
    }
}
