package org.example.utils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before
    public void setup(Scenario scenario) {
        System.setProperty("file.encoding", "UTF-8");
    }
}