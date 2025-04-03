package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ch.sobrado.radar.TestHelper.*;



@QuarkusTest
class RadarsResourceTest {

    @Test
    @TestTransaction
    void testGetAllRadars() {
        // Ensure at least one radar exists via create endpoint.
        int id = createTestRadar("Radar for getAll");
        for (int i = 0; i < 5; i++) {
            createTestRadar("Radar " + i);
        }

        // Test GET /radars returns a non-empty list and includes our radar.
        given()
                .when().get("/radars")
                .then().statusCode(200)
                .body("$", not(empty()))
                .body("find { it.id == " + id + " }.name", equalTo("Radar for getAll"));
    }

    @Test
    @TestTransaction
    void testGetById() {
        int id = createTestRadar("Radar getById");

        given()
                .when().get("/radars/{id}", id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Radar getById"));
    }

    @Test
    @TestTransaction
    void testCreateRadar() {
        // Create a new radar and check for created status and id in return.
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"New Radar\"}")
                .when().post("/radars")
                .then().statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    @TestTransaction
    void testUpdateRadar() {
        int id = createTestRadar("Radar to update");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Radar\"}")
                .when().put("/radars/{id}", id)
                .then().statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Updated Radar"));

        given()
                .when().get("/radars/{id}", id)
                .then().statusCode(200)
                .body("name", equalTo("Updated Radar"));
    }

    @Test
    @TestTransaction
    void testDeleteRadar() {

        int id = createTestRadar("Radar to delete"); 

        given()
                .when().delete("/radars/{id}", id)
                .then().statusCode(204);

        given()
                .when().get("/radars/{id}", id)
                .then().statusCode(404);
    }
}