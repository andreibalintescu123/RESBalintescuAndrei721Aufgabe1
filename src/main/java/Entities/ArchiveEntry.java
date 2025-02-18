package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ArchiveEntry {
    private int id;
    private String hero;
    private String villain;
    private ConfrontationType confrontationType;
    private String place;
    private LocalDate date;
    private Double globalInfluence;

    public ArchiveEntry(@JsonProperty("Id") int id,
                        @JsonProperty("Held") String hero,
                        @JsonProperty("Antagonist") String villain,
                        @JsonProperty("Konfrontationstyp") ConfrontationType confrontationType,
                        @JsonProperty("Ort") String place,
                        @JsonProperty("Datum") LocalDate date,
                        @JsonProperty("GlobalerEinfluss") Double globalInfluence) {

        this.id = id;
        this.hero = hero;
        this.villain = villain;
        this.confrontationType = confrontationType;
        this.place = place;
        this.date = date;
        this.globalInfluence = globalInfluence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getVillain() {
        return villain;
    }

    public void setVillain(String villain) {
        this.villain = villain;
    }

    public ConfrontationType getConfrontationType() {
        return confrontationType;
    }

    public void setConfrontationType(ConfrontationType confrontationType) {
        this.confrontationType = confrontationType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getGlobalInfluence() {
        return globalInfluence;
    }

    public void setGlobalInfluence(Double globalInfluence) {
        this.globalInfluence = globalInfluence;
    }

    public String toString() {
        return this.id + ", " + this.hero + ", " + this.villain + ", " + this.confrontationType + ", " + this.date + ", " + this.globalInfluence;
    }
}
