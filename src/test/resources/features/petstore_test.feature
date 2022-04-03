Feature: Pet store test
#  https://petstore.swagger.io/v2/

  Scenario: 1 - Getting pet by id

    Given user has "getPetByIdRQ" request with id "12"

    When user sends "GET" "getPetByIdRQ" request

    Then "getPetByIdRS" code is "200"
    And the id of found pet is equal to "12" in "getPetByIdRS"

  Scenario: 2 - Checking if selected pet has correct status field
    Given user has "getPetByIdRQ" request with id "12"

    When user sends "GET" "getPetByIdRQ" request

    Then "getPetByIdRS" code is "200"
    And "getPetByIdRS" has correct "status" field with value "sold"


  Scenario: 3 - Creating order

    Given user has "createOrderRQ" request with following parameters
      | petId | quantity | shipDate                 | status    | complete |
      | 15    | 1        | 2022-04-02T17:40:04.980Z | available | false    |

    When user sends "POST" "createOrderRQ" request
    And user has "deleteOrderRQ" request with id from "createOrderRS" response
    And user sends "DELETE" "deleteOrderRQ" request

    Then "deleteOrderRS" code is "200"


