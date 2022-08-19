package ru.stqa.pft.sandbox;

public class MainPoint {
    public static void main(String[] args) {
        Point p1 = new Point(5,3);
        Point p2 = new Point(3,-1);

        System.out.println("Расстояние между точками = " + p1.distance(p2));
    }
}
