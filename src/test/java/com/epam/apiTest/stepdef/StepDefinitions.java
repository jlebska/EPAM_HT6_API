package com.epam.apiTest.stepdef;

import com.epam.apiTest.core.store.RxStore;
import com.epam.apiTest.model.heirs.requests.RqObject;
import com.epam.apiTest.model.heirs.requests.heirs.DeleteRequestObject;
import com.epam.apiTest.model.heirs.requests.heirs.GetRequestObject;
import com.epam.apiTest.model.heirs.requests.heirs.PostRequestObject;
import com.epam.apiTest.model.heirs.responses.RsObject;
import com.epam.apiTest.model.heirs.responses.heirs.DeleteResponseObject;
import com.epam.apiTest.model.heirs.responses.heirs.GetResponseObject;
import com.epam.apiTest.model.heirs.responses.heirs.PostResponseObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StepDefinitions {
    private RxStore rxStore = RxStore.getInstance();

    @When("^user sends \"([^\"]*)\" \"([^\"]*)\" request$")
    public void userSendsRequest(String requestMethod, String rqName) {
        RqObject requestObject = (RqObject) rxStore.getDataFromStore(rqName);
        String rsName = rqName.replace("RQ", "RS");
        RsObject receivedResponse = switch (requestMethod) {
            case "GET" -> new GetResponseObject(rsName, requestObject.sendGetRequest());
            case "POST" -> new PostResponseObject(rsName, requestObject.sendPostRequest());
            case "DELETE" -> new DeleteResponseObject(rsName, requestObject.sendDeleteRequest());
            default -> throw new IllegalStateException("Unexpected value: " + requestMethod);
        };

        rxStore.putDataIntoStore(receivedResponse.getRxName(), receivedResponse);
    }

    @Then("^\"([^\"]*)\" code is \"([^\"]*)\"$")
    public void responseStatusCodeEqualsExpected(String rsName, String statusCode) {
        RsObject actualResponse = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(statusCode, String.valueOf(actualResponse.getStatusCode()),
                "Status code doesn't match expected");
    }

    @Given("user has {string} request with id {string}")
    public void userHasRequestForGettingUserById(String rqName, String petId) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetById(petId);

        rxStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @Given("user has {string} request with status {string}")
    public void userHasRequestWithStatus(String rqName, String petStatus) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetByStatus(petStatus);

        rxStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }


    @Given("user has {string} request with following parameters")
    public void userHasRequestWithFollowingParameters(String rqName, DataTable orderInfo) {
        PostRequestObject postRequestObject = new PostRequestObject(rqName);
        String petId = orderInfo.cell(1, 0);
        String quantity = orderInfo.cell(1, 1);
        String shipDate = orderInfo.cell(1, 2);
        String status = orderInfo.cell(1, 3);
        String complete = orderInfo.cell(1, 4);
        postRequestObject.createRequestForCreatingOrder(petId, quantity, shipDate, status, complete);

        rxStore.putDataIntoStore(postRequestObject.getRxName(), postRequestObject);
    }

    @And("the id of found pet is equal to {string} in {string}")
    public void theIdOfFoundPetIsEqualToIn(String petId, String rqName) {
        GetResponseObject actualResponse = (GetResponseObject) rxStore.getDataFromStore(rqName);
        Assert.assertEquals(actualResponse.getPetId(), Integer.valueOf(petId),
                "Found id is not correct");
    }

    @And("user has {string} request with id from {string} response")
    public void userHasRequestWithIdFromResponse(String rqName, String rsName) {
        PostResponseObject createOrderResponse = (PostResponseObject) rxStore.getDataFromStore(rsName);

        DeleteRequestObject deleteRequestObject = new DeleteRequestObject(rqName);
        deleteRequestObject.createRequestForDeletingOrder(String.valueOf(createOrderResponse.getOrderId()));

        rxStore.putDataIntoStore(deleteRequestObject.getRxName(), deleteRequestObject);

    }

    @And("{string} has correct {string} field with value {string}")
    public void hasField(String rsName, String fieldName, String fieldValue) {
        GetResponseObject actualResponse = (GetResponseObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(actualResponse.checkField(fieldName), fieldValue,
                "Field value is not correct");
    }
}
