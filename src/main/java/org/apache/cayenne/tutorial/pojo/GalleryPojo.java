package org.apache.cayenne.tutorial.pojo;

import org.apache.cayenne.tutorial.persistent.Gallery;

public class GalleryPojo {

    private String name;

    public GalleryPojo(Gallery gallery) {
        this.name = gallery.getName();
    }

    public String getName() {
        return name;
    }
}
