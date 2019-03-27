package org.apache.cayenne.tutorial.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.tutorial.persistent.Artist;

@Path("/")
public class CayenneRestService {

    private static ServerRuntime serverRuntime;

    static {
        serverRuntime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .build();
    }

    @GET
    @Path("/artists")
    @Produces(MediaType.TEXT_PLAIN)
    public String artists() {
        ObjectContext context = serverRuntime.newContext();
        List<Artist> artistList = ObjectSelect.query(Artist.class)
                .select(context);
        StringBuilder res = new StringBuilder();
        for(Artist artist : artistList) {
            res.append(artist.getName())
                    .append(" ")
                    .append(artist.getDateOfBirth())
                    .append('\n');
        }
        return res.toString();
    }

    @POST
    @Path("/artist")
    public Response createArtist(@QueryParam("name") String name, @QueryParam("date") String date) {
        ObjectContext context = serverRuntime.newContext();
        Artist artist = context.newObject(Artist.class);
        artist.setName(name);
        artist.setDateOfBirthString(date);
        context.commitChanges();
        return Response.status(201).entity("Success.").build();
    }

    @PUT
    @Path("/artist/{id}")
    public Response updateArtist(@PathParam("id") int id,
                                 @QueryParam("name") String newName,
                                 @QueryParam("date") String newDate) {
        ObjectContext context = serverRuntime.newContext();
        Artist artist = Cayenne.objectForPK(context, Artist.class, id);
        if(artist != null) {
            if (newName != null) {
                artist.setName(newName);
            }
            if (newDate != null) {
                artist.setDateOfBirthString(newDate);
            }
            context.commitChanges();
            return Response.status(200).entity("Success.").build();
        }
        return Response.status(404).entity("Wrong id. Artist not found.").build();
    }
}
