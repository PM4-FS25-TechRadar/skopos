package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ch.sobrado.radar.TestHelper.*;



@QuarkusTest
class RadarsResourceTest {

    @Test
    void testGetAllRadars() {
        RadarGroup testGroup = createTestGroup("Test Group");
        // Ensure at least one radar exists via create endpoint.
        Radar radar = createTestRadar("Radar for getAll", testGroup);
        for (int i = 0; i < 5; i++) {
            createTestRadar("Radar " + i, testGroup);
        }

        // Test GET /radars returns a non-empty list and includes our radar.
        given()
                .when().get("/radars")
                .then().statusCode(200)
                .body("$", not(empty()))
                .body("find { it.id == " + radar.id + " }.name", equalTo("Radar for getAll"));
    }

    @Test
    void testGetById() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar getById", testGroup);

        given()
                .when().get("/radars/{id}", radar.id)
                .then().statusCode(200)
                .body("id", equalTo(radar.id.intValue()))
                .body("name", equalTo("Radar getById"));
    }

    @Test
    void testCreateRadar() {
        RadarGroup testGroup = createTestGroup("Test Group");
        // Create a new radar and check for created status and id in return.
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Create Radar\", \"radarGroup\": {\"id\":" + testGroup.id + "}}")
                .when().post("/radars")
                .then().statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRadar() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Radar\"}")
                .when().put("/radars/{id}", radar.id)
                .then().statusCode(200)
                .body("id", equalTo(radar.id.intValue()))
                .body("name", equalTo("Updated Radar"));

        given()
                .when().get("/radars/{id}", radar.id)
                .then().statusCode(200)
                .body("name", equalTo("Updated Radar"));
    }

    @Test
    void testDeleteRadar() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to delete", testGroup);

        given()
                .when().delete("/radars/{id}", radar.id)
                .then().statusCode(204);
    }
}