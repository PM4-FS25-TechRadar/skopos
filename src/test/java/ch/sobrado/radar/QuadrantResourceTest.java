package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ch.sobrado.radar.TestHelper.*;

@QuarkusTest
class QuadrantResourceTest {


    @Test
    void testCreateQuadrant() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"CreateQuadrant\", \"position\":\"ONE\"}")
                .when()
                .post("/radars/" + radar.id + "/quadrants")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");
    }

    @Test
    void testDuplicateQuadrantName() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        createTestQuadrant("Test Quadrant", radar, Position.ONE);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Test Quadrant\"}")
                .when()
                .post("/radars/" + radar.id + "/quadrants")
                .then()
                .statusCode(409);
    }

    @Test
    void testUpdateQuadrant() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        Quadrant quadrant = createTestQuadrant("Init Quadrant", radar, Position.ONE);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"UpdatedQuadrant\"}")
                .when()
                .put("/radars/" + radar.id + "/quadrants/" + quadrant.id)
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteQuadrant() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        Quadrant quadrant = createTestQuadrant("Test Quadrant", radar, Position.ONE);

        given()
                .when()
                .delete("/radars/" + radar.id + "/quadrants/" + quadrant.id)
                .then()
                .statusCode(204);
    }
}