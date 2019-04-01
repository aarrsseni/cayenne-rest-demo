package org.apache.cayenne.tutorial.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.tutorial.filter.CayenneFilter;
import org.apache.cayenne.tutorial.persistent.Artist;
import org.apache.cayenne.tutorial.pojo.ArtistPojo;

@Path("/")
public class CayenneRestService {

    @Context
    private CayenneFilter cayenneFilter;

    @GET
    @Path("/artists")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistPojo> artists() {
        List<Artist> artistList = ObjectSelect.query(Artist.class)
                .select(cayenneFilter.getObjectContext());
        return artistList.stream()
                .map(ArtistPojo::new)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/artist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createArtist(@QueryParam("name") String name, @QueryParam("date") String date) {
        ObjectContext context = cayenneFilter.getObjectContext();
        Artist artist = context.newObject(Artist.class);
        artist.setName(name);
        artist.setDateOfBirthString(date);
        context.commitChanges();
        return Response.status(201).entity(new ArtistPojo(artist)).build();
    }

    @PUT
    @Path("/artist/{id}")
    public Response updateArtist(@PathParam("id") int id,
                                 @QueryParam("name") String newName,
                                 @QueryParam("date") String newDate) {
        ObjectContext context = cayenneFilter.getObjectContext();
        Artist artist = Cayenne.objectForPK(context, Artist.class, id);
        if(artist != null) {
            if (newName != null) {
                artist.setName(newName);
            }
            if (newDate != null) {
                artist.setDateOfBirthString(newDate);
            }
            context.commitChanges();
            return Response.status(200).entity(new ArtistPojo(artist)).build();
        }
        return Response.status(404).entity("Entity not found.").build();
    }
}
