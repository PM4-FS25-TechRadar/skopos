package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



@QuarkusTest
class RadarsResourceTest {

    @Inject
    TestData testData;

    RadarGroup group;
    Radar radar;

    @BeforeEach
    @Transactional
    void setUp() {
        group = testData.createGroup("Test Group");
        radar = testData.createRadar("Radar A", group);
    }

    @Test
    void testGetAllRadars() {
        Radar radar = testData.createRadar("Radar for getAll", group);
        for (int i = 0; i < 5; i++) {
            testData.createRadar("Radar " + i, group);
        }

        // Test GET /radars returns a non-empty list and includes our radar.
        given()
                .when().get("/radar")
                .then().statusCode(200)
                .body("$", not(empty()))
                .body("find { it.id == " + radar.id + " }.name", equalTo("Radar for getAll"));
    }

    @Test
    void testGetById() {
        given()
                .when().get("/radar/{id}", radar.id)
                .then().statusCode(200)
                .body("id", equalTo(radar.id.intValue()))
                .body("name", equalTo("Radar A"));
    }

    @Test
    void testCreateRadar() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Create Radar\", \"radarGroup\": {\"id\":" + group.id + "}}")
                .when().post("/radar")
                .then().statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRadar() {
        Radar radar = testData.createRadar("Radar to update", group);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Radar\"}")
                .when().put("/radar/{id}", radar.id)
                .then().statusCode(200)
                .body("id", equalTo(radar.id.intValue()))
                .body("name", equalTo("Updated Radar"));

        given()
                .when().get("/radar/{id}", radar.id)
                .then().statusCode(200)
                .body("name", equalTo("Updated Radar"));
    }

    @Test
    void testDeleteRadar() {
        Radar radar = testData.createRadar("Radar to delete", group);

        given()
                .when().delete("/radar/{id}", radar.id)
                .then().statusCode(204);
    }
}