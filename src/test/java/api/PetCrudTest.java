package api;

import io.restassured.RestAssured;
import io.restassured.internal.http.HttpResponseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class PetCrudTest {

    static int petId;

    @BeforeClass
    void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        petId = 123456;
    }


    private int safeStatusCode(String method, String path, String body) {
        try {
            var req = given();
            if (body != null) {
                req.header("Content-Type", "application/json").body(body);
            }
            return req.when().request(method, path).then().extract().statusCode();
        } catch (Throwable e) {
            Throwable t = e;
            while (t != null) {
                if (t instanceof HttpResponseException hre) {
                    return hre.getStatusCode();
                }
                t = t.getCause();
            }

            throw new RuntimeException(e);
        }
    }

    // ================== CREATE ==================

    @Test(priority = 1)
    void createPetPositive() {
        String requestBody = """
                {
                  "id": %d,
                  "name": "Pera",
                  "status": "available"
                }
                """.formatted(petId);

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo("Pera"))
                .body("status", equalTo("available"));
    }

    @Test(priority = 2)
    void createPetNegative() {
        int code = safeStatusCode("POST", "/pet", "{");
        assertTrue(code == 400 || code == 500, "Expected 400/500 but was " + code);
    }

    // ================== READ ==================

    @Test(priority = 3)
    void getPetPositive() {
        given()
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId));
    }

    @Test(priority = 4)
    void getPetNegative() {
        int code = safeStatusCode("GET", "/pet/not-a-number", null);
        assertTrue(code == 400 || code == 404, "Expected 400/404 but was " + code);
    }

    // ================== UPDATE ==================

    @Test(priority = 5)
    void updatePetPositive() {
        String requestBody = """
                {
                  "id": %d,
                  "name": "Pera Updated",
                  "status": "sold"
                }
                """.formatted(petId);

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Pera Updated"))
                .body("status", equalTo("sold"));
    }

    @Test(priority = 6)
    void updatePetNegative() {
        int code = safeStatusCode("PUT", "/pet", "{");
        assertTrue(code == 400 || code == 500, "Expected 400/500 but was " + code);
    }

    // ================== DELETE ==================

    @Test(priority = 7)
    void deletePetPositive() {
        given()
                .when()
                .delete("/pet/" + petId)
                .then()
                .statusCode(200);
    }

    @Test(priority = 8)
    void deletePetNegative() {
        int code = safeStatusCode("DELETE", "/pet/not-a-number", null);
        assertTrue(code == 400 || code == 404, "Expected 400/404 but was " + code);
    }
}
