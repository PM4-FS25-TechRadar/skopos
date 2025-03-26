package ch.sobrado.radar;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radar/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RadarEntriesResource {

    @GET
    public Response getAll() {
        return Response.ok(RadarEntry.listAll()).build();
    }

    @POST
    @Transactional
    public Response create(RadarEntry radar) {
        radar.persist();
        return Response.status(Response.Status.CREATED).entity(radar).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, RadarEntry updated) {
        RadarEntry existing = RadarEntry.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.label = updated.label;
        existing.quadrant = updated.quadrant;
        existing.ring = updated.ring;
        existing.moved = updated.moved;
        existing.year = updated.year;

        return Response.ok(existing).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = RadarEntry.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}

