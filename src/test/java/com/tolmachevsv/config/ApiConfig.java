package com.tolmachevsv.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/api.properties"})
public interface ApiConfig extends Config {
    @Config.Key("petstore.api.base.url")
    String baseURL();
    @Config.Key("petstore.api.base.path")
    String basePath();
}
