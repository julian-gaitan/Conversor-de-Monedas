package org.challengeone.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record API_Response(
        String result,
        @SerializedName("error-type") String errorType,
        String base_code, Map<String, Double> conversion_rates) {
}
