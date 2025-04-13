package ch.sobrado.radar;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("/technologies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TechnologiesResource {

    @GET
    public List<Technology> getAll() {
        return Technology.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Technology tech = Technology.findById(id);
        if (tech == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(tech).build();
    }

    @POST
    @Transactional
    public Response create(Technology tech) {
        if (Technology.find("name", tech.name).firstResult() != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Technology with name already exists").build();
        }
        tech.persist();
        return Response.status(Response.Status.CREATED)
                .entity(Collections.singletonMap("id", tech.id))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Technology updated) {
        Technology existing = Technology.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.name = updated.name;
        existing.description = updated.description;

        return Response.ok(existing).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Technology.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
