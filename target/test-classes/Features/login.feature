Feature: Demo to movement mortgage

  Scenario Outline: Parameterize your script
    Given user launch the <application> in chrome browser
    Then user is on home page
    When user clicks on Sign-up option
    When user input <Personal_Info>
    And user gives <Billing_Address> and <Mobile>
    When user clicks on Generate password
    Then verify Generate password UI
    When user insert passwqord length and click on Generate password
    Then verify generated password
    Then user opts for mailing list and clicks on robot
    Then user clicks on Register
    Then close all

    Examples: 
      | application                  | Personal_Info | Billing_Address                                   | Mobile   |
      | https://phptravels.com/demo/ | Demo ;Test;dummyemail58mm@gmail.com;1234567 | Dummy Street;MM Road;Dreamer;Alaska;T2R0F5 | 85698756 |
