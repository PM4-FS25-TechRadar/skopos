package ch.sobrado.radar;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RadarEntryResource {

    @Inject
    EntityManager em;

    @GET
    @Path("radar/{radarId}/entries")
    public List<RadarEntry> getEntriesByRadar(@PathParam("radarId") Long radarId) {
        return RadarEntry.list("radar.id", radarId);
    }

    @PUT
    @Path("radar/{radarId}/entries")
    @Transactional
    public Response createEntry(@PathParam("radarId") Long radarId, EntryDTO dto) {
        Radar radar = Radar.findById(radarId);
        Ring ring = Ring.findById(dto.ringId);
        Quadrant quadrant = Quadrant.findById(dto.quadrantId);
        Version version = Version.findById(dto.versionId);

        if (radar == null || ring == null || quadrant == null || version == null) {
            return Response.status(BAD_REQUEST).entity("One or more referenced entities not found").build();
        }

        RadarEntry entry = new RadarEntry();
        entry.radar = radar;
        entry.ring = ring;
        entry.quadrant = quadrant;
        entry.version = version;
        entry.status = dto.status;
        entry.persist();

        return Response.status(CREATED).entity(entry).build();
    }

    @POST
    @Path("entries/{entryId}")
    @Transactional
    public Response updateEntry(@PathParam("entryId") Long entryId, EntryDTO dto) {
        RadarEntry entry = RadarEntry.findById(entryId);
        if (entry == null) {
            return Response.status(NOT_FOUND).build();
        }

        Ring ring = Ring.findById(dto.ringId);
        Quadrant quadrant = Quadrant.findById(dto.quadrantId);
        Version version = Version.findById(dto.versionId);

        if (ring == null || quadrant == null || version == null) {
            return Response.status(BAD_REQUEST).entity("Invalid ring, quadrant or version").build();
        }

        entry.ring = ring;
        entry.quadrant = quadrant;
        entry.version = version;
        entry.status = dto.status;

        return Response.ok(entry).build();
    }

    @DELETE
    @Path("entries/{entryId}")
    @Transactional
    public Response deleteEntry(@PathParam("entryId") Long entryId) {
        int result = em.createNativeQuery("DELETE FROM entries WHERE id = :id")
                .setParameter("id", entryId)
                .executeUpdate();

        return result > 0
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    public static class EntryDTO {
        public Long ringId;
        public Long quadrantId;
        public Long versionId;
        public EntryStatus status;
    }
}
