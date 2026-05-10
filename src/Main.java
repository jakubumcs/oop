public class Main {

    public static void main(String[] args) {
        // Zadanie 1 - Point
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point(7.0, 1.0);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);

        // Zadanie 2 - toSvg()
        System.out.println(p1.toSvg());
        System.out.println(p2.toSvg());

        // Zadanie 3 - translate / translated
        p1.translate(1.0, -1.0);
        System.out.println("p1 po translate(1, -1): " + p1);

        Point p3 = p2.translated(2.0, 3.0);
        System.out.println("p2 bez zmian: " + p2);
        System.out.println("p3 = p2.translated(2, 3): " + p3);

        // Zadanie 4 - Segment
        Segment s1 = new Segment(new Point(0, 0), new Point(3, 4));
        Segment s2 = new Segment(new Point(1, 1), new Point(6, 13));
        Segment s3 = new Segment(new Point(-2, -2), new Point(1, 2));
        System.out.println("s1 length: " + s1.length());
        System.out.println("s2 length: " + s2.length());
        System.out.println("s3 length: " + s3.length());

        // Zadanie 5 - longest segment
        Segment[] segments = {s1, s2, s3};
        Segment longest = Segment.longest(segments);
        System.out.println("Najdluzszy odcinek: " + longest + ", dlugosc: " + longest.length());
    }
}
