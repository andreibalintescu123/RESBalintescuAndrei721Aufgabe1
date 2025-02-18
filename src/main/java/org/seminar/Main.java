package org.seminar;

import Entities.ArchiveEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        
    }
}