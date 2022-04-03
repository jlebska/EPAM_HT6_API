package com.epam.apiTest.model.heirs.requests.heirs;

import com.epam.apiTest.model.heirs.requests.RqObject;

public class DeleteRequestObject extends RqObject {
    public DeleteRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForDeletingOrder(String orderId) {
        setBaseUri("https://petstore.swagger.io/v2/store/order/" + orderId);
        setCommonParams();
    }
}
