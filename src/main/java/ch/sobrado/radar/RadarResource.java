package ch.sobrado.radar;

import java.util.Collections;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RadarsResource {

    @GET
    public List<Radar> getAll() {
        return Radar.listAll();
    }

    @GET
    @Path("/{radarId}")
    public Response getById(@PathParam("radarId") Long radarId) {
        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(radar).build();
    }

    @POST
    @Transactional
    public Response create(Radar radar) {
        radar.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", radar.id))
                .build();
    }

    @PUT
    @Path("/{radarId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
                           Radar updatedRadar) {

        Radar existingRadar = Radar.findById(radarId);
        if (existingRadar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingRadar.name = updatedRadar.name;

        return Response.ok(existingRadar).build();
    }

    @DELETE
    @Path("/{radarId}")
    @Transactional
    public Response delete(@PathParam("radarId") Long radarId) {

        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        radar.delete();
        return Response.noContent().build();
    }
}
