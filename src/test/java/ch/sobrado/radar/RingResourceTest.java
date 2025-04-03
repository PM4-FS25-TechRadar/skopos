package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ch.sobrado.radar.TestHelper.*;

@QuarkusTest
class RingResourceTest {

    @Test
    @TestTransaction
    void testCreateRing() {
        int radarId = createTestRadar("Test Radar");
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\": \"Test Ring\" }")
                .when()
                .post("radars/" + radarId + "/rings")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRing() {
        int radarId = createTestRadar("Test Radar");
        int ringId = createTestRing("Test Ring", radarId);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Ring\"}")
                .when()
                .put("radars/" + radarId + "/rings/" + ringId)
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteRing() {
        int radarId = createTestRadar("Test Radar");
        int ringId = createTestRing("Test Ring", radarId);

        given()
                .when().delete("radars/" + radarId + "/rings/" + ringId)
                .then()
                .statusCode(204);

    }
}