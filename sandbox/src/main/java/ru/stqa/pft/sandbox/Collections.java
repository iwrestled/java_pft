package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main (String[] args) {
        String[] langs = {"Java","C#","Python","PHP"};  //Массивы

        List<String> languages = Arrays.asList("Java","C#","Python","PHP"); //Списки

        for (String l: languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
