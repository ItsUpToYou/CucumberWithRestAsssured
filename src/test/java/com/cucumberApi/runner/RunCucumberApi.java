package com.cucumberApi.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/"},
        glue = {"com.cucumberApi.stepDefs"},
        tags = "@test",
        plugin = {"pretty"})
public class RunCucumberApi {
}
