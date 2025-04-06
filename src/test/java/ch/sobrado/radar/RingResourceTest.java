package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ch.sobrado.radar.TestHelper.*;

@QuarkusTest
class RingResourceTest {

    @Test
    void testCreateRing() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\": \"Test Ring\", \"position\": \"ONE\" }")
                .when()
                .post("radars/" + radar.id + "/rings")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRing() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        Ring ring = createTestRing("Test Ring", radar, Position.ONE);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Updated Ring\"}")
                .when()
                .put("radars/" + radar.id + "/rings/" + ring.id)
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteRing() {
        RadarGroup testGroup = createTestGroup("Test Group");
        Radar radar = createTestRadar("Radar to update", testGroup);

        Ring ring = createTestRing("Test Ring", radar, Position.ONE);

        given()
                .when().delete("radars/" + radar.id + "/rings/" + ring.id)
                .then()
                .statusCode(204);

    }
}