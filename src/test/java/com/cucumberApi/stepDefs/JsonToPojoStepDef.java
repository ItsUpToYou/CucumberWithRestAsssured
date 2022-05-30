package com.cucumberApi.stepDefs;

import com.cucumberApi.catalogue.VideoGames;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;


public class JsonToPojoStepDef extends VideoGames {
    RequestSpecification httpRequest;
    private Response response;

    VideoGames videoGame = new VideoGames("0", "2022-05-20", "LAST ChangedGame", "Stars", "13", "Test");
    VideoGames videoGamefromJson;
    String json;
    List<VideoGames> items;
    ObjectMapper objectMapper = new ObjectMapper();

    @Given("Read the video games")
    public void readTheVideoGames() {
        RestAssured.baseURI = "http://localhost:8080/app";
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json");
        response = httpRequest.get("/videogames");
        json = response.body().asPrettyString();

    }

    @When("Take video game with id {int}")
    public void videoGameIdInt(int id) {
        httpRequest.given().pathParam("videoGameId", id + "");
        response = httpRequest.get("/videogames/{videoGameId}");
        videoGamefromJson = response.getBody().as(VideoGames.class);
    }


    @Then("Compare the name with Pojo")
    public void theNamesMustBeEquals() {
        System.out.println(response.body().asPrettyString());
        System.out.println(response.getStatusCode());
        Assert.assertEquals("The names are different, please check the details of JSon.", videoGame.getName(), videoGamefromJson.getName());

    }

    @When("Convert Json objects to Java")
    public void convertJsonObjectsToJava() throws JsonProcessingException {
        items = objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(List.class, VideoGames.class));
    }

    @Then("Video game with name {word} exist")
    public void compareVideoGameWithNameMario(String word) {
        for (VideoGames game : items) {
            if (game.getName().contains(word)) {
                System.out.println("Video Game with name: " + word + " exist!");
            }
        }
    }
}
