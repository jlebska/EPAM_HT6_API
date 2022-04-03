package com.epam.apiTest.model.heirs.responses.heirs;

import com.epam.apiTest.model.heirs.responses.RsObject;
import io.restassured.response.Response;

public class DeleteResponseObject extends RsObject {
    public DeleteResponseObject(String rsName, Response response) {
        super(rsName, response);
    }
}
