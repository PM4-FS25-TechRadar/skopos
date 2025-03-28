package ch.sobrado.radar;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TechnologiesResourceTest {

    @Test
    public void testCreateAndGetTechnology() {
        int techId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"TestTech\",\"description\":\"test desc\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given()
                .when()
                .get("/technologies/" + techId)
                .then()
                .statusCode(200)
                .body("name", equalTo("TestTech"));
    }

    @Test
    public void testDuplicateTechnologyName() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"DuplicateTech\",\"description\":\"one\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"DuplicateTech\",\"description\":\"two\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(409);
    }

    @Test
    public void testUpdateTechnology() {
        int techId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"UpdatableTech\",\"description\":\"before update\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"UpdatableTech\",\"description\":\"after update\"}")
                .when()
                .put("/technologies/" + techId)
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/technologies/" + techId)
                .then()
                .statusCode(200)
                .body("description", equalTo("after update"));
    }

    @Test
    public void testDeleteTechnology() {
        int techId = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"DeleteMe\",\"description\":\"to be deleted\"}")
                .when()
                .post("/technologies")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .delete("/technologies/" + techId)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/technologies/" + techId)
                .then()
                .statusCode(404);
    }
}
