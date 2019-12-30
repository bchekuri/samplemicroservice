@test
Feature: Health test

  Background: Set up feature
    * url baseURL
    * path healthPath

  Scenario: Validate health check
    * def healthCheckStatus = {status: "UP"}
    When method GET
    Then status 200
    And match response contains healthCheckStatus
