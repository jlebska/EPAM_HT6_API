package com.epam.apiTest.model.heirs.requests.heirs;

import com.epam.apiTest.model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingUsers() {
        setBaseUri();
        setCommonParams();
    }

    public void createRequestForGettingPetById(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);

        setCommonParams();
    }

    public void createRequestForGettingPetByStatus(String petStatus) {
        setBaseUri("https://petstore.swagger.io/v2/pet/findByStatus?status=" + petStatus);

        setCommonParams();
    }
}
