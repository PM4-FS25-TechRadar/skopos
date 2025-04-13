package ch.sobrado.radar;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class RingResourceTest {

    @Inject
    TestData testData;

    RadarGroup group;
    Radar radar;
    Ring ring;

    @BeforeEach
    @Transactional
    void setUp() {
        group = testData.createGroup("Test Group");
        radar = testData.createRadar("Test Radar", group);
        ring = testData.addRing(radar, "Test Ring");
    }

    @Test
    void testCreateRing() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\": \"Create Ring\"}")
                .when()
                .post("radars/" + radar.id + "/rings")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void testUpdateRing() {
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
        given()
                .when().delete("radars/" + radar.id + "/rings/" + ring.id)
                .then()
                .statusCode(204);

    }
}