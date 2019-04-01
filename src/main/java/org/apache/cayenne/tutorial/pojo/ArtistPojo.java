package org.apache.cayenne.tutorial.pojo;

import java.time.LocalDate;

import org.apache.cayenne.tutorial.persistent.Artist;

public class ArtistPojo {

    private String name;
    private LocalDate date;

    public ArtistPojo(Artist artist) {
        this.name = artist.getName();
        this.date = artist.getDateOfBirth();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
