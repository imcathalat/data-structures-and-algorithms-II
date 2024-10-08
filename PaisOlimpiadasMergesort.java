/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.paisolimpiadasmergesort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 1487306
 */
class Medalha {

    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TipoMedalha medalType;
    private LocalDate medalDate;
    private String discipline;
    private String event;

    public Medalha(TipoMedalha type, LocalDate date, String discipline, String event) {
        this.medalType = type;
        this.discipline = discipline;
        this.event = event;
        this.medalDate = date;
    }

    public TipoMedalha getMedalType() {
        return medalType;
    }

    @Override
    public String toString() {
        String formattedDate = medalDate.format(displayFormatter);
        return medalType + " - " + discipline + " - " + event + " - " + formattedDate;
    }
}

class Medalhista implements Comparable<Medalhista> {

    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String name;
    private String gender;
    private Pais country;
    private Medalha[] medals;
    private int medalCount = 0;
    private LocalDate birthDate;
    private int goldMedals = 0, silverMedals = 0, bronzeMedals = 0;

    public Medalhista(String nome, String gender, LocalDate birthDate, Pais country) {
        this.name = nome;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.medals = new Medalha[10];
    }

    public void incluirMedalha(Medalha medalha) {
        if (medalCount == medals.length) {
            Medalha[] novoArray = new Medalha[medals.length + 10];

            System.arraycopy(medals, 0, novoArray, 0, medals.length);

            medals = novoArray;
        }

// Adicionar a nova medalha
        medals[medalCount] = medalha;
        medalCount++;

        boolean isString = medalha.getMedalType().toString() instanceof String;

        switch (medalha.getMedalType().toString()) {
            case "OURO" ->
                this.goldMedals++;
            case "PRATA" ->
                this.silverMedals++;
            case "BRONZE" ->
                this.bronzeMedals++;
            default -> {
            }
        }
    }

    @Override
    public int compareTo(Medalhista outroMedalhista) {
        // Comparar medalhas de ouro (maior valor de ouro vem primeiro)
        if (this.goldMedals != outroMedalhista.goldMedals) {
            return Integer.compare(outroMedalhista.goldMedals, this.goldMedals); // Ordem decrescente
        }

        // Se o número de medalhas de ouro for o mesmo, comparar medalhas de prata
        if (this.silverMedals != outroMedalhista.silverMedals) {
            return Integer.compare(outroMedalhista.silverMedals, this.silverMedals); // Ordem decrescente
        }

        // Se o número de medalhas de prata for o mesmo, comparar medalhas de bronze
        if (this.bronzeMedals != outroMedalhista.bronzeMedals) {
            return Integer.compare(outroMedalhista.bronzeMedals, this.bronzeMedals); // Ordem decrescente
        }

        // Se todos os números de medalhas forem iguais, ordenar por nome em ordem alfabética
        return this.name.compareTo(outroMedalhista.name); // Ordem crescente (A-Z)

    }

    public int getGoldMedals() {
        return this.goldMedals;
    }

    public int getSilverMedals() {
        return this.silverMedals;
    }

    public int getBronzeMedals() {
        return this.bronzeMedals;
    }

    public String getGender() {
        return gender;
    }

    public Pais getCountry() {
        return country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public int getMedalCount() {
        return this.medalCount;
    }

    @Override
    public String toString() {
        String formattedDate = birthDate.format(displayFormatter);

        // Construir a exibição de medalhas
        StringBuilder medalDisplay = new StringBuilder();

        if (goldMedals > 0) {
            medalDisplay.append("Quantidade de medalhas de ouro: ").append(goldMedals).append("\n");
        }

        if (silverMedals > 0) {
            medalDisplay.append("Quantidade de medalhas de prata: ").append(silverMedals).append("\n");
        }

        if (bronzeMedals > 0) {
            medalDisplay.append("Quantidade de medalhas de bronze: ").append(bronzeMedals).append("\n");
        }

        // Caso o medalhista não tenha nenhuma medalha
        if (medalDisplay.length() == 0) {
            medalDisplay.append("Nenhuma medalha conquistada.");
        }

        // Retornar o resultado formatado
        return name + ", " + gender + ". Nascimento: " + formattedDate + ". País: " + country + "\n"
                + medalDisplay.toString();
    }

}

enum TipoMedalha {
    OURO,
    PRATA,
    BRONZE;

    @Override
    public String toString() {
        return name();
    }
}

class Pais implements Comparable<Pais> {

    private String name;
    private int goldMedals = 0, silverMedals = 0, bronzeMedals = 0, medalCount = 0;
    private Medalhista[] medalhistas;
    private Medalha[] medals;

    public Pais(String country) {
        this.name = country;
        this.medals = new Medalha[10];
    }

    public void incluirMedalha(Medalha medalha) {

        if (medalCount == medals.length) {
            Medalha[] novoArray = new Medalha[medals.length + 10];

            System.arraycopy(medals, 0, novoArray, 0, medals.length);

            medals = novoArray;
        }

        medals[medalCount] = medalha;
        medalCount++;

        boolean isString = medalha.getMedalType().toString() instanceof String;

        switch (medalha.getMedalType().toString()) {
            case "OURO" ->
                this.goldMedals++;
            case "PRATA" ->
                this.silverMedals++;
            case "BRONZE" ->
                this.bronzeMedals++;
            default -> {
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }

    public int getmMedalsCount() {
        return this.medalCount;
    }

    public Medalhista[] getMedalhistas() {
        return medalhistas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoldMedals(int goldMedals) {
        this.goldMedals = goldMedals;
    }

    public void setSilverMedals(int silverMedals) {
        this.silverMedals = silverMedals;
    }

    public void setBronzeMedals(int bronzeMedals) {
        this.bronzeMedals = bronzeMedals;
    }

    public void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    public void setMedalhistas(Medalhista[] medalhistas) {
        this.medalhistas = medalhistas;
    }

    @Override
    public int compareTo(Pais outroPais) {
        // Comparar medalhas de ouro (maior valor de ouro vem primeiro)
        if (this.goldMedals != outroPais.goldMedals) {
            return Integer.compare(outroPais.goldMedals, this.goldMedals); // Ordem decrescente
        }

        // Se o número de medalhas de ouro for o mesmo, comparar medalhas de prata
        if (this.silverMedals != outroPais.silverMedals) {
            return Integer.compare(outroPais.silverMedals, this.silverMedals); // Ordem decrescente
        }

        // Se o número de medalhas de prata for o mesmo, comparar medalhas de bronze
        if (this.bronzeMedals != outroPais.bronzeMedals) {
            return Integer.compare(outroPais.bronzeMedals, this.bronzeMedals); // Ordem decrescente
        }

        // Se todos os números de medalhas forem iguais, ordenar por nome em ordem alfabética
        return this.name.compareTo(outroPais.name); // Ordem crescente (A-Z)

    }
    
    @Override
    public String toString() {
        return name + ": " + goldMedals + " ouros " + silverMedals + " pratas " + bronzeMedals + " bronzes Total " + medalCount + " medalhas.";
    }

}

class Olimpiadas {

    public static Map<Medalhista, List<Medalha>> leArquivo(String local) throws IOException {
        Map<Medalhista, List<Medalha>> medalhistasMap = new HashMap<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        HashSet<Pais> paises = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(local))) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",", -1);

                if (campos.length < 8) {
                    continue;
                }
                String nome = campos[0];
                TipoMedalha tipoMedalha = TipoMedalha.valueOf(campos[1].toUpperCase());
                LocalDate medalDate = LocalDate.parse(campos[2], inputFormatter);
                String gender = campos[3];
                LocalDate birthDate = LocalDate.parse(campos[4], inputFormatter);
                String country = campos[5];
                String discipline = campos[6];
                String event = campos[7];

                Medalha medalha = new Medalha(tipoMedalha, medalDate, discipline, event);

// Verifica se o medalhista já existe no map
                Medalhista medalhista = null;

                for (Map.Entry<Medalhista, List<Medalha>> medalhistaEntry : medalhistasMap.entrySet()) {
                    if (medalhistaEntry.getKey().getName().equals(nome)) {
                        medalhista = medalhistaEntry.getKey(); // Medalhista já existe
                        break;
                    }
                }
                
                Pais pais = null;
                for(Pais paiss : paises){
                    if(country.equals(paiss.getName())){
                        pais = paiss;
                    }
                }
                
                if(pais == null){
                    pais = new Pais(country);
                    paises.add(pais);
                }

// Se o pais não foi encontrado, cria um novo pais
                if (medalhista == null) {
                    medalhista = new Medalhista(nome, gender, birthDate, pais);
                    medalhistasMap.put(medalhista, new ArrayList<>()); // Adiciona o novo medalhista ao mapa
                }

// Adiciona a medalha ao medalhista, seja novo ou existente
                medalhistasMap.get(medalhista).add(medalha);
                medalhista.incluirMedalha(medalha);
                pais.incluirMedalha(medalha);
            }

            return medalhistasMap;

        }
    }
}

interface IOrdenator<T> {

    public T[] ordenar(List<T> list);

    public void setComparador(Comparator<T> comparador);

    public int getComparacoes();

    public int getMovimentacoes();

    public double getTempoOrdenacao();
}

class Selectionsort implements IOrdenator<Medalhista> {

    private Comparator<Medalhista> comparador;
    private int comparacoes = 0;
    private int movimentacoes = 0;
    private long tempoExecucao = 0;

    @Override
    public void setComparador(Comparator<Medalhista> comparador) {
        this.comparador = comparador;
    }

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoExecucao;
    }

    @Override
    public Medalhista[] ordenar(List<Medalhista> medalhistas) {
        long inicio = System.nanoTime();

        int n = medalhistas.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (comparador.compare(medalhistas.get(j), medalhistas.get(minIndex)) < 0) {
                    minIndex = j;
                }
                comparacoes++;
            }
            if (minIndex != i) {
                Medalhista temp = medalhistas.get(i);
                medalhistas.set(i, medalhistas.get(minIndex));
                movimentacoes++;
                medalhistas.set(minIndex, temp);
                movimentacoes++;
            }
            comparacoes++;
        }

        long fim = System.nanoTime();
        tempoExecucao = fim - inicio;

        return medalhistas.toArray(new Medalhista[0]);
    }

}

class QuickSort implements IOrdenator<Medalhista> {
    private Comparator<Pais> comparador;
    private int comparacoes = 0;
    private int movimentacoes = 0;
    private long tempoExecucao = 0;

    @Override
    public Medalhista[] ordenar(List<Pais> list) {
        
    }

    @Override
    public void setComparador(Comparator<Pais> comparador) {
        this.comparador = comparador;
    }

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoExecucao;
    }

}

public class PaisOlimpiadasMergesort {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
