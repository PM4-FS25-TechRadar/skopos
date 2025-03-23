package ch.sobrado.radar;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/radar")
public class RadarResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy on Sobrado Radar";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/data/{year}")
    public String data(@PathParam("year") int year) {
         RadarView e = RadarView.find("year", year).firstResult();
         return e.jsondata; 
    }
}