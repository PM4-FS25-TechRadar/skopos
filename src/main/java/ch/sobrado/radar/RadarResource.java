package ch.sobrado.radar;

import java.util.Collections;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars")
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
        for(Quadrant quadrant : radar.quadrants) {
            quadrant.radar = radar;
        }
        for(Ring ring : radar.rings) {
            ring.radar = radar;
        }
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
        existingRadar.quadrants.clear();
        for (Quadrant quadrant : updatedRadar.quadrants) {
            Quadrant managed = Quadrant.findById(quadrant.id);
            if (managed == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingRadar.quadrants.add(managed);
            managed.radar = existingRadar;
            managed.name = quadrant.name;
        }

        existingRadar.rings.clear();
        for (Ring ring : updatedRadar.rings) {
            Ring managed = Ring.findById(ring.id);
            if (managed == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingRadar.rings.add(managed);
            managed.radar = existingRadar;
            managed.name = ring.name;
        }
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
