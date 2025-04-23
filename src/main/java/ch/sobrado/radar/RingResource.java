package ch.sobrado.radar;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars/{radarId}/rings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RingResource {

    @PUT
    @Path("/{ringId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
            @PathParam("ringId") Long ringId,
            @Valid Ring updatedRing) {

        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Ring existingRing = Ring.findById(ringId);
        if (existingRing == null || !existingRing.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean duplicate = Ring.find("name = ?1 and radar.id = ?2", updatedRing.name, radarId)
                .firstResult() != null;

        if (duplicate) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Ring with this name already exists for this radar")
                    .build();
        }

        existingRing.name = updatedRing.name;

        return Response.ok(existingRing).build();
    }
}
