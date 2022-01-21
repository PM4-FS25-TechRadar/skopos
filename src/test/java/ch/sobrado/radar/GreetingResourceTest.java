package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/radar")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy on Sobrado Radar"));
    }

}