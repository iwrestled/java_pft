package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Dmirtiy");

        double l = 5;
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

        double f = 4;
        double g = 10;
        double h = 10;
        System.out.println("Площадь треугольника со сторонами " + f + " и " + g + " и " + h + " = " + area(f,g,h));

        double t = 8;
        double y = 9;
        System.out.println("Площадь прямоугольника со сторонами " + t + " и " + y + " = " + area(t,y));
    }
    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + " !");
    }
    public static double area(double len) {
        return len * len;

    }
    public static double area(double a,double b) {
        return a * b;

    }
    public static double area(double a,double b,double c) {
        return a * b * c;

    }

}