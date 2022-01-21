package ch.sobrado.radar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
         Radarentries e = Radarentries.find("year", year).firstResult(); 
         return e.jsondata; 
    }
}