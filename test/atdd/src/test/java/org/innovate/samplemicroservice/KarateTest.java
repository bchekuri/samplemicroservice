package org.innovate.samplemicroservice;

import com.intuit.karate.testng.KarateRunner;
import cucumber.api.CucumberOptions;

@CucumberOptions(features = "classpath:", tags = {"@test"}, plugin = {"pretty", "html:target/cucumber"})
public class KarateTest extends KarateRunner {

}