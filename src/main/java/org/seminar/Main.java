package org.seminar;

import Entities.ArchiveEntry;
import Entities.ConfrontationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);

        // Enable Java 8 LocalDate Support
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File jsonFile = new File("src/main/java/Files/marvel_konfrontationen.json");
        List<ArchiveEntry> archiveEntries = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ArchiveEntry.class));
        for(ArchiveEntry entry : archiveEntries) {
            System.out.println(entry);
        }
        System.out.println("Enter value:");
        Double value = Double.parseDouble(scanner.nextLine());
        Predicate<ArchiveEntry> greaterThanValue = obj -> obj.getGlobalInfluence() >= value;
        List<ArchiveEntry> filteredEntries= archiveEntries.stream().filter(greaterThanValue).toList();
        List<String> filteredNames = new ArrayList<>();
        for(ArchiveEntry entry : filteredEntries) {
            filteredNames.add(entry.getHero());
        }
        List<String> uniqueFilteredHeroes = filteredNames.stream().distinct().collect(Collectors.toList());
        for(String hero : uniqueFilteredHeroes) {
            System.out.println(hero);
        }
        Predicate<ArchiveEntry> checkGalactic = obj -> obj.getConfrontationType().equals(ConfrontationType.Galaktisch);
        List<ArchiveEntry> filteredGalactic = archiveEntries.stream().filter(checkGalactic).sorted(Comparator.comparing(ArchiveEntry::getDate).reversed()).toList();
        for(ArchiveEntry entry : filteredGalactic) {
            System.out.println(entry.getDate() + ": " + entry.getHero() + "vs. " + entry.getVillain() + " - " + entry.getPlace());
        }
        Map<ConfrontationType, Integer> confrontationsPerType = new HashMap<>();
        for(ArchiveEntry entry : archiveEntries) {
            confrontationsPerType.put(entry.getConfrontationType(), confrontationsPerType.getOrDefault(entry.getConfrontationType(), 0) + 1);
        }

        Map<ConfrontationType, Double> globalInfluencePerType = new HashMap<>();
        for(ArchiveEntry entry : archiveEntries) {
            globalInfluencePerType.put(entry.getConfrontationType(), globalInfluencePerType.getOrDefault(entry.getConfrontationType(), 0.00) + entry.getGlobalInfluence());
        }


        List<Map.Entry<ConfrontationType, Integer>> sortedConfrontations = new ArrayList<>(confrontationsPerType.entrySet());
        sortedConfrontations.sort(Comparator
                .comparing(Map.Entry<ConfrontationType, Integer>::getValue, Comparator.reverseOrder()));

        List<Map.Entry<ConfrontationType,Double>> sortedGlobalInfluences = new ArrayList<>(globalInfluencePerType.entrySet());
        sortedGlobalInfluences.sort(Comparator.comparing(Map.Entry<ConfrontationType, Double>::getValue, Comparator.reverseOrder()));

        FileWriter writer = new FileWriter("src/main/java/Files/bericht_konfrontationen.txt");
        for (Map.Entry<ConfrontationType, Integer> entry : sortedConfrontations) {
            writer.write(entry.getKey() + "&" + entry.getValue() + "$" + globalInfluencePerType.get(entry.getKey()) + "\n" );
        }
        writer.close();
    }

}