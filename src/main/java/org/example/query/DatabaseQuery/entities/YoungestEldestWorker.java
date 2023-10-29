package org.example.query.DatabaseQuery.entities;

import java.time.LocalDate;

public class YoungestEldestWorker {
    private String TYPE;
    private String NAME;
    private LocalDate BIRTHDAY;

    public YoungestEldestWorker(String TYPE, String NAME, LocalDate BIRTHDAY) {
        this.TYPE = TYPE;
        this.NAME = NAME;
        this.BIRTHDAY = BIRTHDAY;
    }

    @Override
    public String toString() {
        return "YoungestEldestWorker{" +
                "TYPE='" + TYPE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", BIRTHDAY=" + BIRTHDAY +
                '}';
    }
}
