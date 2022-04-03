package com.epam.apiTest.model.heirs.responses.heirs;

import com.epam.apiTest.model.heirs.responses.RsObject;
import io.restassured.response.Response;

public class GetResponseObject extends RsObject {
    private static final String RESULT_ID_LOCATOR = "id";
    private static final String RESULT_STATUS_LOCATOR = "status";

    public GetResponseObject(String rsName, Response response) {
        super(rsName, response);
    }


    public Integer getPetId() {
        return response.jsonPath().get(RESULT_ID_LOCATOR);
    }

    public String checkField(String fieldName) {
        return response.jsonPath().get(RESULT_STATUS_LOCATOR);
    }
}
