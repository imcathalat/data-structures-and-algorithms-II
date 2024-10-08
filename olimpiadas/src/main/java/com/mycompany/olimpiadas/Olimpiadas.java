/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


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

class Medalhista {

    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String name, gender, country;
    private Medalha[] medals;
    private int medalCount;
    private LocalDate birthDate;

    public Medalhista(String nome, String gender, LocalDate birthDate, String country) {
        this.name = nome;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.medals = new Medalha[10];
        this.medalCount = 0;
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
        String formattedDate = birthDate.format(displayFormatter);
        return name + ", " + gender + ". Nascimento: " + formattedDate + ". Pais: " + country;
    }

    public String getName() {
        return name;
    }
    
    public int getMedalCount() {
        return medalCount;
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

public class Olimpiadas {

    public static Map<Medalhista, List<Medalha>> leArquivo(String local) {
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
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return medalhistasMap;

    }

    public static void main(String[] args) {
        String caminhoArquivo = "/tmp/medallists.csv";
        Map<Medalhista, List<Medalha>> medalhistasMap = leArquivo(caminhoArquivo);
        

        Scanner scanner = new Scanner(System.in);
        String atleta = scanner.nextLine();
        String regex = "[,]";
        String[] atletaMedalha = atleta.split(regex);

        while (!atleta.equals("FIM")) {
            int medalsCount = 0;

            for (Map.Entry<Medalhista, List<Medalha>> medalhistaEntry : medalhistasMap.entrySet()) {

                if (medalhistaEntry.getKey().getName().equals(atletaMedalha[0])) {
                    for (Medalha medalha : medalhistaEntry.getValue()) {
                        if (medalha.getMedalType().toString().equals(atletaMedalha[1])) {
                            medalsCount++;
                       }
                    }

                    if (medalsCount != 0) {
                        System.out.println(medalhistaEntry.getKey().toString());
                        for (Medalha medalha : medalhistaEntry.getValue()) {
                            if (medalha.getMedalType().toString().equals(atletaMedalha[1])) {
                                System.out.println(medalha.toString());
                            }
                        }
                        System.out.println("");

                    } else {
                        System.out.println(medalhistaEntry.getKey().toString());
                        System.out.println("Nao possui medalha de " + atletaMedalha[1]);
                        System.out.println("");
                    }
                }

            }

            atleta = scanner.nextLine();
            atletaMedalha = atleta.split(regex);
        }

    }
}
