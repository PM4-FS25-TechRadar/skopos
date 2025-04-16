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

    Radar radar;
    Ring ring;

    @BeforeEach
    @Transactional
    void setUp() {
        radar = testData.createRadar("Test Radar");
        ring = testData.replaceRing(radar, 0,"Test Ring");
    }

    @Test
    void testDuplicateRingName() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Test Ring\"}")
                .when()
                .put("/radars/" + radar.id + "/rings/" + ring.id)
                .then()
                .statusCode(409);
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

}