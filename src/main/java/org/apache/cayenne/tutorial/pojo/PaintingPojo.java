package org.apache.cayenne.tutorial.pojo;

import org.apache.cayenne.tutorial.persistent.Painting;

public class PaintingPojo {

    private String name;

    public PaintingPojo(Painting painting) {
        this.name = painting.getName();
    }

    public String getName() {
        return name;
    }
}
