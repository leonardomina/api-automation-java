package org.example.config;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;

public class CucumberReportGenerator {
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");

        Configuration config = new Configuration(reportOutputDirectory, "Projeto API Testes");
        config.addClassifications("Ambiente", "QA");
        config.addClassifications("Plataforma", "Windows 11");
        config.addClassifications("Java", "11");

        ReportBuilder reportBuilder = new ReportBuilder(
                Collections.singletonList("target/cucumber-reports.json"),
                config
        );
        reportBuilder.generateReports();
    }
}
