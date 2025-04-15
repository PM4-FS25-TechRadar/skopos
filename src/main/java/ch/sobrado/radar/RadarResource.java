package ch.sobrado.radar;

import java.util.Collections;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RadarResource {

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/data/{year}")
    public String data(@PathParam("year") int year) {
        RadarView e = RadarView.find("year", year).firstResult();
        return e.jsondata;
    }

    @POST
    @Transactional
    public Response create(@Valid Radar radar) {
        /*
        RadarGroup group = RadarGroup.findById(radar.radarGroup.id);
        if (group == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Radar group with id " + radar.radarGroup.id + " not found")
            .build();
        }
        radar.radarGroup = group;
        */

        radar.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", radar.id))
                .build();
    }

    @PUT
    @Path("/{radarId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
                           @Valid Radar updatedRadar) {

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
