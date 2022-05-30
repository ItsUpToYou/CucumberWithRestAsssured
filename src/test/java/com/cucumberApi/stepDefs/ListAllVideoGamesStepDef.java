package com.cucumberApi.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class ListAllVideoGamesStepDef {

    RequestSpecification httpRequest;
    private Response response;

    @Given("URL")
    public void url() {
        RestAssured.baseURI = "http://localhost:8080/app";
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json");

    }

    @Given("Catalogue with video games")
    public void catalogueWithVideoGames() {

    }

    @When("Request all video games")
    public void requestAllVideoGames() {
        response = httpRequest.get("/videogames");
    }

    @Then("Successful status {int}")
    public void successfulStatus(int statusCode) {
        System.out.println(response.asPrettyString());
        System.out.println("Status code is : " + response.statusCode());
        Assert.assertEquals("Response message is not 200", statusCode,response.getStatusCode());
    }



    @Given("Video game with id {int}")
    public void videoGameIdIs(int id) {
         httpRequest.given().pathParam("videoGameId", id+"");
    }

    @When("Read the game")
    public void lookingForTheGame() {
        response = httpRequest.get("/videogames/{videoGameId}");
    }


}
