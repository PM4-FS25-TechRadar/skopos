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
class RadarsResourceTest {

    @Inject
    TestData testData;

    Radar radar;

    @BeforeEach
    @Transactional
    void setUp() {
        radar = testData.createRadar("Radar A");
    }

    @Test
    void testGetAllRadars() {
        Radar targetRadar = testData.createRadar("Radar for getAll");
        for (int i = 0; i < 5; i++) {
            testData.createRadar("Radar " + i);
        }

        // Test GET /radars returns a non-empty list and includes our radar.
        given()
                .when().get("/radar")
                .then().statusCode(200)
                .body("$", not(empty()))
                .body("find { it.id == " + targetRadar.id + " }.name", equalTo("Radar for getAll"));
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
        String radarJson = "{"
                + "\"name\":\"Create Radar\","
                + "\"quadrants\":["
                + "    {\"name\":\"Quadrant 1\"},"
                + "    {\"name\":\"Quadrant 2\"},"
                + "    {\"name\":\"Quadrant 3\"},"
                + "    {\"name\":\"Quadrant 4\"}"
                + "],"
                + "\"rings\":["
                + "    {\"name\":\"Ring 1\"},"
                + "    {\"name\":\"Ring 2\"},"
                + "    {\"name\":\"Ring 3\"},"
                + "    {\"name\":\"Ring 4\"}"
                + "]"
                + "}";

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(radarJson)
                .when().post("/radar")
                .then().statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRadar() {
        Radar radar = testData.createRadar("Radar to update");

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
        Radar radar = testData.createRadar("Radar to delete");

        given()
                .when().delete("/radar/{id}", radar.id)
                .then().statusCode(204);
    }
}