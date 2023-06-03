package com.tolmachevsv.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getBaseURL() {
        return getConfig().baseURL();
    }

    public static String getBasePath() {
        return getConfig().basePath();
    }

    private static ApiConfig getConfig() {
        return ConfigFactory.newInstance().create(ApiConfig.class);
    }
}
