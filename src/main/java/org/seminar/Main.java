package org.seminar;

import Entities.ArchiveEntry;
import Entities.ConfrontationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
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


    }
}