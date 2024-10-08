/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bubblesort;

import java.util.Random;

/**
 *
 * @author i.cathalat
 */
public class Bubblesort {
    
    public static int[] bublesort(int[] array){
        int p = array.length - 1;
        boolean swapped;
        int comparacoes = 0;
        int trocas = 0;
        int[] compTroc = new int[2];
        for(int i=0; i<array.length - 1; i++){
            swapped = false;
            for(int j=0; j<array.length - i - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    trocas++;
                    swapped = true; 
                }
                comparacoes++;
            }
            
            if(!swapped){
                System.out.println("array ordenado");
                break;
            }
        }
        
        compTroc[0] = comparacoes;
        compTroc[1] = trocas;
        
        return compTroc;
    }

    public static void main(String[] args) {
        int[] numbers = {60, 32, 45, 5, 6, 2};
        int[] compTroc = new int[2];
        compTroc = bublesort(numbers);
        System.out.println("array ordenado");
        for(int i=0; i<numbers.length; i++){
            System.out.print(numbers[i] + " ");
        }
        System.out.println("\ncomparacoes: " + compTroc[0] + " - trocas: " + compTroc[1]);
        
        
    }
}
