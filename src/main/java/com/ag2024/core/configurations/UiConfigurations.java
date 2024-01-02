package com.ag2024.core.configurations;

import com.dbudim.ag2024.configuration.Configuration;

import static com.ag2024.core.configurations.UiParameters.BROWSER;
import static com.dbudim.ag2024.configuration.Parameters.PROJECT_URL;


/**
 * Created by dbudim on 17.12.2023
 */

public class UiConfigurations extends Configuration {

    public static String getProjectUrl() {
        return getParameter(PROJECT_URL);
    }

    public static String getBrowser() {
        return getParameter(BROWSER);
    }

}
