package ch.sobrado.radar;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;

@Path("/technologies/{techId}/versions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VersionsResource {

    @POST
    @Transactional
    public Response create(@PathParam("techId") Long techId, Version version) {
        Technology technology = Technology.findById(techId);
        if (technology == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean exists = Version.find("name = ?1 and technology.id = ?2", version.name, techId)
                .firstResult() != null;

        if (exists) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Version with this name already exists for this technology")
                    .build();
        }

        version.technology = technology;
        version.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", version.id))
                .build();
    }


    @PUT
    @Path("/{versionId}")
    @Transactional
    public Response update(@PathParam("techId") Long techId,
                           @PathParam("versionId") Long versionId,
                           Version updated) {

        Version existing = Version.findById(versionId);
        if (existing == null || !existing.technology.id.equals(techId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.name = updated.name;
        existing.description = updated.description;

        return Response.ok(existing).build();
    }

    @DELETE
    @Path("/{versionId}")
    @Transactional
    public Response delete(@PathParam("techId") Long techId,
                           @PathParam("versionId") Long versionId) {

        Version existing = Version.findById(versionId);
        if (existing == null || !existing.technology.id.equals(techId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.delete();
        return Response.noContent().build();
    }
}
