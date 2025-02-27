package org.example.runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.example.config.CucumberReportGenerator;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example.steps",
        plugin = {
                "pretty",
                "json:target/cucumber-reports.json"
        },
        monochrome = true
)
public class TestRunner {
        @AfterClass
        public static void gerarRelatorio() {
                CucumberReportGenerator.generateReport();
        }
}
