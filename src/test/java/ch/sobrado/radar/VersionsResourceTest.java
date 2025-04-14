package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class VersionsResourceTest {

    @Disabled ("This test is disabled to test the deployment")
    @Test
    public void testCreateUpdateDeleteVersion() {
        int techId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"TechWithVersions\",\"description\":\"test\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201)
                .extract().path("id");

        int versionId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Version 1.0\",\"description\":\"init\"}")
                .when()
                .post("/technologies/" + techId + "/versions")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"Version 1.0\",\"description\":\"updated desc\"}")
                .when()
                .put("/technologies/" + techId + "/versions/" + versionId)
                .then()
                .statusCode(200);

        given()
                .when()
                .delete("/technologies/" + techId + "/versions/" + versionId)
                .then()
                .statusCode(204);
    }

    @Disabled ("This test is disabled to test the deployment")
    @Test
    public void testDuplicateVersionName() {
        int techId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"TechDupVer\",\"description\":\"desc\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"SameVersion\",\"description\":\"v1\"}")
                .when()
                .post("/technologies/" + techId + "/versions")
                .then()
                .statusCode(201);
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"SameVersion\",\"description\":\"v2\"}")
                .when()
                .post("/technologies/" + techId + "/versions")
                .then()
                .statusCode(409);
    }
}

