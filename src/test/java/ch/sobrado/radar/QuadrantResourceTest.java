package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ch.sobrado.radar.TestHelper.*;

@QuarkusTest
class QuadrantResourceTest {

    @Test
    @TestTransaction
    void testCreateQuadrant() {
        int radarId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"CreateRadar\"}")
                .when()
                .post("/radars")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"CreateQuadrant\"}")
                .when()
                .post("/radars/" + radarId + "/quadrants")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");
    }

    @Test
    @TestTransaction
    void testDuplicateQuadrantName() {
        int radarId = createTestRadar("Test Radar");

        createTestQuadrant("Test Quadrant", radarId);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Test Quadrant\"}")
                .when()
                .post("/radars/" + radarId + "/quadrants")
                .then()
                .statusCode(409);
    }

    @Test
    @TestTransaction
    void testUpdateQuadrant() {
        int radarId = createTestRadar("Test Radar");

        int quadrantId = createTestQuadrant("Init Quadrant", radarId);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"UpdatedQuadrant\"}")
                .when()
                .put("/radars/" + radarId + "/quadrants/" + quadrantId)
                .then()
                .statusCode(200);
    }

    @Test
    @TestTransaction
    void testDeleteQuadrant() {
        int radarId = createTestRadar("Test Radar");

        int quadrantId = createTestQuadrant("Test Quadrant", radarId);

        given()
                .when()
                .delete("/radars/" + radarId + "/quadrants/" + quadrantId)
                .then()
                .statusCode(204);
    }
}