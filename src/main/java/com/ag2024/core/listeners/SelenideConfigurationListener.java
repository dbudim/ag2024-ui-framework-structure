package com.ag2024.core.listeners;

import com.ag2024.core.configurations.UiConfigurations;
import com.codeborne.selenide.Configuration;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SelenideConfigurationListener implements ISuiteListener {

    public void onStart(ISuite suite) {
        Configuration.browser = UiConfigurations.getBrowser();
        Configuration.baseUrl = UiConfigurations.getProjectUrl();
        Configuration.timeout = 10000;
    }

}
