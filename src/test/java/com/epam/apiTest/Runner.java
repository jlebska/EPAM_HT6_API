package com.epam.apiTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.apiTest.stepdef"})
public class Runner extends AbstractTestNGCucumberTests {

}
