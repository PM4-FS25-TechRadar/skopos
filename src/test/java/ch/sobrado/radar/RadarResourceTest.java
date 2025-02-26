package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class RadarResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/radar")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy on Sobrado Radar"));
    }

    @Test
    public void testDataEndpoint() {
        given()
          .when().get("/radar/data/2022")
          .then()
             .statusCode(200)
             .contentType(MediaType.APPLICATION_JSON); 
    }

}