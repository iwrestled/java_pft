package ru.stqa.pft.sandbox;

public class MainPoint {
    public static void main(String[] args) {
        Point cord = new Point(5, 3, 3, -1);
        cord.p1 = (cord.x2 - cord.x1) * (cord.x2 - cord.x1);
        cord.p2 = (cord.y2 - cord.y1) * (cord.y2 - cord.y1);
        System.out.println("Расстояние между точками = " + cord.distance());
    }
}
