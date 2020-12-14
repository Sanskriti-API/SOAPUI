@tag
Feature: Verify Course Creation by Admin
  Scenario Outline: To verify whether application allows admin to create course
    Given user should have launched the application
    Given user should have logged in as admin "<username>" and "<password>"
    And user should be present in Home page
    When click on Administration tab
    When click on Create a Course link
    When enter valid credential "<title>" in Title textbox
    When enter valid credential "<code>" in code textbox
    When select Valid credentials category from Category list box
    When select Valid credentials "<teacher>" in Teacher list box
    When select Valid credentials language from Language list box
    And click on Create a course button
    Then course should get created and should get displayed in course list

    Examples:
    |username|password  |title  |code|teacher|
    |admin   |admin@123 |testing|tes |manzoor|