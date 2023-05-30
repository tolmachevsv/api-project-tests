package com.tolmachevsv.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceData {
    @JsonProperty("category")
    private Category category;
    private int id;
    private String name;
    private String status;
}
