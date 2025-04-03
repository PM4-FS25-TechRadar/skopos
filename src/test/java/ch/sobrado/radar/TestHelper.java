package ch.sobrado.radar;

import jakarta.ws.rs.core.MediaType;


import static io.restassured.RestAssured.given;


public class TestHelper {

    public static int createTestRadar(String name) {
        return given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"" + name + "\"}")
                .when().post("/radars")
                .then().statusCode(201)
                .extract().path("id");
    }

    public static int createTestQuadrant(String name, int radarId) {
        return given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"" + name + "\"}")
                .when().post("/radars/" + radarId + "/quadrants")
                .then().statusCode(201)
                .extract().path("id");
    }

    public static int createTestRing(String name, int radarId) {
        return given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"name\":\"" + name + "\"}")
                .when().post("/radars/" + radarId + "/rings")
                .then().statusCode(201)
                .extract().path("id");
    }
}