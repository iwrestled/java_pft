package ru.stqa.pft.sandbox;

import java.util.List;

public class Arrays {

/*    public static void main(String[] args) {
        int[] myArray = {3, 5, 11, 21, -2};
        int sum = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (i > 10 || i < 0) {
                sum = sum + myArray[i];
                System.out.println(sum);
            }
        }
    }
*/
    public static void main(String[] args) {
        int[] myArray = {3, 5, 11, 21, -2};
        int sum = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] >10 || myArray[i] <0)
                sum = sum + myArray[i];
            }
        System.out.println(sum);
        }
    }


