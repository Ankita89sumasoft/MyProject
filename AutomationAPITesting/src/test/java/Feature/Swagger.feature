Feature: Verify the user module in petstore swagger
  @user_API_Test
  Scenario: check whether the user module API in petstore swagger webpage
    Given set up the required data for the petstore swagger page
    When verify the post API on petstore swagger page
    Then Verify the get API on petstore swagger page
    When Verify the update API on petstored swagger page
    Then Verify the delete API on petstored swagger page

    @Pet_API_Test
  Scenario: check whether the pet module API in petstored swagger webpage
    Given set up the required data for the pet module in petstore swagger page
    When Verify the Post API of pet module on petstore swagger page
    Then Verify the get API of pet module on petstore swagger page
    When verify the put API of pet module on petstore swagger page
    Then Verify the delete API of pet module on petstore swagger page

    @Travel_API_Test_signal_file_upload_Multiple_Fileuploads
    Scenario: Travel agent API Test
      When I test the travel agent get API
      When verify the single upload file on spring boot website
      Then Verify the multiple upload file on spring boot website
      And verify the file is downloaded or not

