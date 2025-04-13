package ch.sobrado.radar;

import java.util.Collections;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars/{radarId}/rings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RingResource {

    @POST
    @Transactional
    public Response create(@PathParam("radarId") Long radarId, Ring ring) {
        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean exists = Ring.find("name = ?1 and radar.id = ?2", ring.name, radarId)
                .firstResult() != null;

        if (exists) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("ring with this name already exists for this radar")
                    .build();
        }
        ring.radar = radar;
        radar.rings.add(ring);
        ring.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", ring.id))
                .build();
    }

    @PUT
    @Path("/{ringId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
            @PathParam("ringId") Long ringId,
            Ring updatedRing) {

        Ring existingRing = Ring.findById(ringId);
        if (existingRing == null || !existingRing.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingRing.name = updatedRing.name;

        return Response.ok(existingRing).build();
    }

    @DELETE
    @Path("/{ringId}")
    @Transactional
    public Response delete(@PathParam("radarId") Long radarId,
            @PathParam("ringId") Long ringId) {

        Ring ring = Ring.findById(ringId);
        if (ring == null || !ring.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ring.delete();
        return Response.noContent().build();
    }
}
