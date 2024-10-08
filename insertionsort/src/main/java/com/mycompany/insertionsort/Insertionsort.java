/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.insertionsort;

/**
 *
 * @author i.cathalat
 */
public class Insertionsort {
    
    public static void printArray(int[] array){
        for(int i=0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
    }
    
    public static void insertionSort(int[] numbers){
        for(int i=1; i<numbers.length; i++){
            int j = i-1, currentValue = numbers[i];
            while(j >= 0 && numbers[j] > currentValue){
                numbers[j+1] = numbers[j];
                j--;
            }
            numbers[j + 1] = currentValue;
            System.out.println("\ni: " + i);
            printArray(numbers);
        
        }
    }

    public static void main(String[] args) {
        int[] numbers =  {72, 12, 35, 1, 17, 4, 43, 11, 17, 1};
        insertionSort(numbers);
        System.out.println("ordenado: \n");
        printArray(numbers);
    }
}
