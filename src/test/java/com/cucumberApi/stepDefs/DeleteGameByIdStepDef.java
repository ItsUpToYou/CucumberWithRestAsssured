package com.cucumberApi.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class DeleteGameByIdStepDef {
    RequestSpecification httpRequest;
    private Response response;

    @Given("Catalogue with all video games")
    public void catalogueWithAllVideoGames() {
        RestAssured.baseURI = "http://localhost:8080/app";
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json");
    }

    @When("Delete video game by id {int}")
    public void deleteVideoGameById(int id) {

        response = httpRequest.delete("/videogames/" + id);
    }

    @Then("return status {int}")
    public void returnStatus(int statusCode) {
         Assert.assertTrue("Record is not deleted!", response.prettyPrint().contains("Record Deleted Successfully"));
        Assert.assertEquals("Response message is not 200", statusCode, response.getStatusCode());
    }
}
