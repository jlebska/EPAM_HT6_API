package com.epam.apiTest.model.heirs.responses.heirs;

import com.epam.apiTest.model.heirs.responses.RsObject;
import io.restassured.response.Response;

public class PostResponseObject extends RsObject {
    private static final String RESULT_ID_LOCATOR = "id";

    public PostResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public Long getOrderId() {
        return response.jsonPath().get(RESULT_ID_LOCATOR);
    }
}
