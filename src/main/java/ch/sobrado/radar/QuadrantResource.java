package ch.sobrado.radar;

import java.util.Collections;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/radars/{radarId}/quadrants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuadrantResource {

    @POST
    @Transactional
    public Response create(@PathParam("radarId") Long radarId, Quadrant quadrant) {
        Radar radar = Radar.findById(radarId);
        if (radar == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean exists = Quadrant.find("name = ?1 and radar.id = ?2", quadrant.name, radarId)
                .firstResult() != null;

        if (exists) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Quadrant with this name already exists for this radar")
                    .build();
        }

        quadrant.radar = radar;
        quadrant.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", quadrant.id))
                .build();
    }

    @PUT
    @Path("/{quadrantId}")
    @Transactional
    public Response update(@PathParam("radarId") Long radarId,
            @PathParam("quadrantId") Long quadrantId,
            Quadrant updatedQuadrant) {

        Quadrant existingQuadrant = Quadrant.findById(quadrantId);
        if (existingQuadrant == null || !existingQuadrant.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingQuadrant.name = updatedQuadrant.name;

        return Response.ok(existingQuadrant).build();
    }

    @DELETE
    @Path("/{quadrantId}")
    @Transactional
    public Response delete(@PathParam("radarId") Long radarId,
            @PathParam("quadrantId") Long quadrantId) {

        Quadrant quadrant = Quadrant.findById(quadrantId);
        if (quadrant == null || !quadrant.radar.id.equals(radarId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        quadrant.delete();
        return Response.noContent().build();
    }
}
