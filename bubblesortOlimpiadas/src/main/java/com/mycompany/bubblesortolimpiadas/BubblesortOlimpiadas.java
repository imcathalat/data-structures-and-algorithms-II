/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bubblesortolimpiadas;

import static com.mycompany.bubblesortolimpiadas.Olimpiadas.leArquivo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author i.cathalat
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
    private String name, gender, country;
    private Medalha[] medals;
    private int medalCount = 0;
    private LocalDate birthDate;
    private int goldMedals = 0, silverMedals = 0, bronzeMedals = 0;

    public Medalhista(String nome, String gender, LocalDate birthDate, String country) {
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

    public String getCountry() {
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

class Olimpiadas {

    public static Map<Medalhista, List<Medalha>> leArquivo(String local) throws IOException {
        Map<Medalhista, List<Medalha>> medalhistasMap = new HashMap<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

// Verifica se o medalhista já existe no mapa
                Medalhista medalhista = null;

                for (Map.Entry<Medalhista, List<Medalha>> medalhistaEntry : medalhistasMap.entrySet()) {
                    if (medalhistaEntry.getKey().getName().equals(nome)) {
                        medalhista = medalhistaEntry.getKey(); // Medalhista já existe
                        break;
                    }
                }

// Se o medalhista não foi encontrado, cria um novo
                if (medalhista == null) {
                    medalhista = new Medalhista(nome, gender, birthDate, country);
                    medalhistasMap.put(medalhista, new ArrayList<>()); // Adiciona o novo medalhista ao mapa
                }

// Adiciona a medalha ao medalhista, seja novo ou existente
                medalhistasMap.get(medalhista).add(medalha);
                medalhista.incluirMedalha(medalha);
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

class Bubblesort implements IOrdenator<Medalhista> {

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
    public Medalhista[] ordenar(List<Medalhista> list) {
        long inicio = System.nanoTime();
        
        Medalhista[] atletas = list.toArray(new Medalhista[0]);
        int n = atletas.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparador.compare(atletas[j], atletas[j + 1]) > 0) {
                    Medalhista temp = atletas[j];
                    atletas[j] = atletas[j + 1];
                    atletas[j + 1] = temp;
                    movimentacoes++;
                }
                comparacoes++;
            }
            if(!swapped){
                break;
            }
        }
        
        long fim = System.nanoTime();
        tempoExecucao = fim - inicio;

        return atletas;
    }

}

public class BubblesortOlimpiadas {
    
     public static void escreverLog(long tempoExecucao, int comparacoes, int movimentacoes) {
        String matricula = "834506"; 
        String nomeArquivo = matricula + "_bubblesort.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(matricula + "\t" + tempoExecucao + "\t" + comparacoes + "\t" + movimentacoes);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        String caminhoArquivo = "C:\\Users\\i.cathalat\\OneDrive - Reply\\Documentos\\aed2-codes\\bubblesortOlimpiadas\\src\\main\\java\\com\\mycompany\\bubblesortolimpiadas\\medallists.csv";
        Map<Medalhista, List<Medalha>> medalhistasMap = leArquivo(caminhoArquivo);

        Scanner scanner = new Scanner(System.in);
        /*String atleta = scanner.nextLine();
String regex = "[,]";
String[] atletaMedalha = atleta.split(regex);*/

        int quant = scanner.nextInt();
        scanner.nextLine();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < quant; i++) {
            String nome = scanner.nextLine();
            list.add(nome);
        }

        List<Medalhista> atletas = new ArrayList<>();

        for (int i = 0; i < quant; i++) {
            for (Map.Entry<Medalhista, List<Medalha>> medalhistaEntry : medalhistasMap.entrySet()) {
                if (medalhistaEntry.getKey().getName().equals(list.get(i))) {
                    atletas.add(medalhistaEntry.getKey());
                }
            }
        }

        Bubblesort bubblesort = new Bubblesort();

        bubblesort.setComparador((Medalhista m1, Medalhista m2) -> m1.compareTo(m2));

        Medalhista[] sortedMedalhistas = bubblesort.ordenar(atletas);

        
        for (Medalhista m : sortedMedalhistas) {
            System.out.println(m.toString());
        }
        
        escreverLog((long) bubblesort.getTempoOrdenacao(), bubblesort.getComparacoes(), bubblesort.getMovimentacoes());

    }
}
