package com.epam.apiTest.model.heirs.requests.heirs;

import com.epam.apiTest.model.heirs.requests.RqObject;
import org.json.JSONException;
import org.json.JSONObject;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForCreatingOrder(String petId, String quantity, String shipDate, String status, String complete) throws JSONException {
        setBaseUri("https://petstore.swagger.io/v2/store/order");
        setCommonParams();
        requestSpecification.body(createOrderAsJsonObject(petId, quantity, shipDate, status, complete).toString());
    }

    private JSONObject createOrderAsJsonObject(String petId, String quantity, String shipDate, String status, String complete) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("petId", petId);
        requestBody.put("quantity", quantity);
        requestBody.put("shipDate", shipDate);
        requestBody.put("status", status);
        requestBody.put("complete", complete);
        return requestBody;
    }
}
