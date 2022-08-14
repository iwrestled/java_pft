package ru.stqa.pft.sandbox;

public class Point {

    public static void main(String[] args) {

        double x1 = 0;
        double y1 = 1;
        double x2 = 2;
        double y2 = -2;
        double len= Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
        System.out.println("Точки на двумерной проскости x1;y1 = " + x1 +";"+ y1 + " x2;y2 = "+ x2 +";"+ y2);
        System.out.println("Расстояние между точками = " + len);

    }


}
