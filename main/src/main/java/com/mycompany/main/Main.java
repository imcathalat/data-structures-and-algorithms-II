/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;

/**
 *
 * @author 1487306
 */
public class Main {

    public static boolean callEhPalindromo(String palavra){
        return ehPalindromo(palavra, 0);  
    }
    
    private static boolean ehPalindromo(String palavra, int i){
        int indice = palavra.length() - 1 - i;
        char L1 = palavra.charAt(i);
        char L2 = palavra.charAt(indice);
        
        if(L1 == L2){
            if(indice == i){
                return true;
            }
            if(indice == palavra.length()/2 && i == (indice - 1)){
                return true;
            }
            return ehPalindromo(palavra, i+1);
        } else {
            return false;
        }
        
    
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palavra;
        boolean result;
        do{
            
            palavra = scanner.nextLine();
            
            if("FIM".equals(palavra)){
                break;
            }
        
            result = callEhPalindromo(palavra);
            
            if(result){
            System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            
        }while(!palavra.equals("FIM"));

    }
}
