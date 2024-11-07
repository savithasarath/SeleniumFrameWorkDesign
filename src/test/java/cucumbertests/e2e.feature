Feature: Purchase Order Tests

  Background:
  Given I landed on Ecommerce page

  Scenario Outline: Place an order
    Given Logged in with userName <name> and Password <password>
    When user select an item <product name>> and click on add to cart
    Then verify that product is displayed in cart
    And click on continue checkout
    And provide payment details
    When user click on submit button
    Then "THANK YOU FOR THE ORDER." message is displayed
    Examples:
      | name               | password  | product name |
      | Hohoihey@gmail.com | Test1234! | ZARA COAT 3  |
      | heymr@gmail.com    | Abcd123! | cocoa        |
