package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

@QuarkusTest
class QuadrantResourceTest {

    @Inject
    TestData testData;

    Radar radar;
    Quadrant quadrant;

    @BeforeEach
    @Transactional
    void setUp() {
        radar = testData.createRadar("Radar A");
        quadrant = testData.replaceQuadrant(radar, 0,"Test Quadrant");
    }

    @Test
    void testDuplicateQuadrantName() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Test Quadrant\"}")
                .when()
                .put("/radars/" + radar.id + "/quadrants/" + quadrant.id)
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

}