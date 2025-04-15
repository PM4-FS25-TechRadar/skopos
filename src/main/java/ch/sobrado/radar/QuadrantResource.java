package ch.sobrado.radar;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars/{radarId}/quadrants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuadrantResource {

    @PUT
    @Path("/{quadrantId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
            @PathParam("quadrantId") Long quadrantId,
            @Valid Quadrant updatedQuadrant) {

        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Quadrant existingQuadrant = Quadrant.findById(quadrantId);
        if (existingQuadrant == null || !existingQuadrant.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean duplicate = Quadrant.find("name = ?1 and radar.id = ?2", updatedQuadrant.name, radarId)
                .firstResult() != null;

        if (duplicate) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Quadrant with this name already exists for this radar")
                    .build();
        }

        existingQuadrant.name = updatedQuadrant.name;

        return Response.ok(existingQuadrant).build();
    }
}
