package com.cucumberApi.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class CreateNewGameStepDef {

    RequestSpecification httpRequest;
    private Response response;

    @Given("Catalogue")
    public void catalogue() {
        RestAssured.baseURI = "http://localhost:8080/app";
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json");
    }

    @When("Add new game with id {int}")
    public void addNewGameWithId(int id) {
        String gameBodyJson = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"name\": \"Estafet\",\n" +
                "  \"releaseDate\": \"2022-05-20T06:10:42.994Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"Test\",\n" +
                "  \"rating\": \"Stars\"\n" +
                "}";
        httpRequest.given().body(gameBodyJson);
        response = httpRequest.post("/videogames");
    }

    @Then("status {int}")
    public void status(int statusCode) {
        Assert.assertTrue("Record is not added!", response.prettyPrint().contains("Record Added Successfully"));
        Assert.assertEquals("Response message is not 200", statusCode, response.getStatusCode());
    }
}
