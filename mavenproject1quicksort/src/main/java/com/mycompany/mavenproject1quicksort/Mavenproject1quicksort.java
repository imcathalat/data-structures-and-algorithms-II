/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1quicksort;

import java.util.Random;

/**
 *
 * @author i.cathalat
 */
public class Mavenproject1quicksort {

    public static void quicksort(int[] numbers, int lowIndex, int highIndex) {
        int pivot = numbers[highIndex];
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            for (int i = lowIndex; i < highIndex; i++) {
                if (numbers[i] > pivot) {
                    while(numbers[highIndex] < numbers[lowIndex]){
                        int temp = numbers[highIndex];
                        numbers[highIndex] = numbers[lowIndex];
                        numbers[lowIndex] = temp;
                        highIndex--;
                    }
                }
                lowIndex++;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < 10; i++) {
            numbers[i] = random.nextInt(100);
        }

    }
}
