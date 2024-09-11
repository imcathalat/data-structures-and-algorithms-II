/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.olimpiadasmedalhistas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 1487306
 */
public class OlimpiadasMedalhistas {

    public static Map<Medalhista, List<Medalha>> leArquivo(String local) {
        Map<Medalhista, List<Medalha>> medalhistasMap = new HashMap<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(local))) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",", -1);

                if (campos.length < 8) {
                    continue; // Verifica se a linha tem o número esperado de campos
                }
                String nome = campos[0];
                TipoMedalha tipoMedalha = TipoMedalha.valueOf(campos[1].toUpperCase());
                LocalDate medalDate = LocalDate.parse(campos[2], dateFormatter);
                String gender = campos[3];
                LocalDate birthDate = LocalDate.parse(campos[4], dateFormatter);
                String country = campos[5];
                String discipline = campos[6];
                String event = campos[7];

                Medalha medalha = new Medalha(tipoMedalha, medalDate, discipline, event);

                Medalhista medalhista = new Medalhista(nome, gender, birthDate, country);
                if (!medalhistasMap.containsKey(medalhista)) {
                    medalhista.incluirMedalha(medalha);
                    medalhistasMap.put(medalhista, new ArrayList<>(Collections.singletonList(medalha)));
                } else {
                    medalhistasMap.get(medalhista).add(medalha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return medalhistasMap;

    }

    public static void main(String[] args) {
        String caminhoArquivo = "C:\\Users\\1487306\\Documents\\NetBeansProjects\\olimpiadasMedalhistas\\src\\main\\java\\com\\mycompany\\olimpiadasmedalhistas\\medallists.csv";
        Map<Medalhista, List<Medalha>> medalhistasMap = leArquivo(caminhoArquivo);

        
        for (Map.Entry<Medalhista, List<Medalha>> entry : medalhistasMap.entrySet()) {
            System.out.println(entry.getKey());
            for (Medalha medalha : entry.getValue()) {
                System.out.println("  " + medalha);
            }
        }
    }
}
