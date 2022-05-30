package com.cucumberApi.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class UpdateGameByIdStepDef {
    RequestSpecification httpRequest;
    private Response response;

    @Given("Video games catalogue")
    public void videoGamesCatalogue() {
        RestAssured.baseURI = "http://localhost:8080/app";
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json");
    }

    @When("update game by id {int}")
    public void updateGameById(int id) {
        String updateBodyJson = "{\n" +
                "  \"id\": 13,\n" +
                "  \"name\": \"LAST ChangedGame\",\n" +
                "  \"releaseDate\": \"2022-05-20T10:07:07.132Z\",\n" +
                "  \"reviewScore\": 6,\n" +
                "  \"category\": \"ChangedCategory\",\n" +
                "  \"rating\": \"17\"\n" +
                "}";
        httpRequest.given().body(updateBodyJson);
        response = httpRequest.put("/videogames/" + id);
    }

    @Then("status is {int}")
    public void statusIs(int statusCode) {
        Assert.assertTrue("Record is not deleted!", response.prettyPrint().contains("LAST ChangedGame"));
        Assert.assertEquals("Response message is not 200", statusCode, response.getStatusCode());
    }
}
