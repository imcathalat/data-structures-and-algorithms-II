/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.olimpiadasmedalhistas;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 1487306
 */
public class Medalhista {
    private String name, gender, country;
    private Medalha[] medals;
    private int medalCount;
    private LocalDate birthDate;
    
    public Medalhista(String nome, String gender, LocalDate birthDate, String country){
        this.name = nome;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.medals = new Medalha[10];
        this.medalCount = 0;
    }
    
    public void incluirMedalha(Medalha medalha){
        if (medalCount == medals.length) {
            Medalha[] novoArray = new Medalha[medals.length + 10];

            System.arraycopy(medals, 0, novoArray, 0, medals.length);
            
            medals = novoArray;
        }

        // Adicionar a nova medalha
        medals[medalCount] = medalha;
        medalCount++;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Medalhista{" + "name=" + name + ", gender=" + gender + ", country=" + country + ", medals=" + medals + ", birthDate=" + birthDate + '}';
    }
    
    
    
}
