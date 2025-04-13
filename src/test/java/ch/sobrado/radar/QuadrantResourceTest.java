package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class QuadrantResourceTest {

    @Inject
    TestData testData;

    RadarGroup group;
    Radar radar;
    Quadrant quadrant;

    @BeforeEach
    @Transactional
    void setUp() {
        group = testData.createGroup("Test Group");
        radar = testData.createRadar("Radar A", group);
        quadrant = testData.addQuadrant(radar, "Test Quadrant");
    }

    @Test
    void testCreateQuadrant() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"CreateQuadrant\"}")
                .when()
                .post("/radars/" + radar.id + "/quadrants")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testDuplicateQuadrantName() {
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
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Quadrant\"}")
                .when()
                .put("/radars/" + radar.id + "/quadrants/" + quadrant.id)
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteQuadrant() {
        given()
                .when()
                .delete("/radars/" + radar.id + "/quadrants/" + quadrant.id)
                .then()
                .statusCode(204);
    }
}