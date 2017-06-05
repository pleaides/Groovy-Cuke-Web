Feature: New customer item search. Ensure ebay sorting and filtering functionality works

  Scenario: Filter by all listings
    Given A new customer is on the ebay main page
    When the customer searches for a new item e.g. "Android"
    And the customer selects the time ending soonest sort
    Then the first result should have free postage
    And the given price is shown

  Scenario: Filter by Auction
    Given A new customer is on the ebay main page
    When the customer searches for a new item e.g. "Android"
    And the customer clicks on the Auction filter
    Then the first result should have free postage
    And the result should show the number of bids
    And the result should not show the buy it now option
    And the given price is shown

  Scenario: Filter by Buy it now
    Given A new customer is on the ebay main page
    When the customer searches for a new item e.g. "iPhone"
    And the customer clicks on the Buy it now filter
    Then the first result should have free postage
    And the result should show the buy it now option
    And the given price is shown

  Scenario: Select Category
    Given A new customer is on the ebay main page
    When the customer searches for a new item e.g. "iPhone"
    And the customer clicks on the Buy it now filter
    And the customer selects category "Cell Phone Accessories"
    Then the first result should have free postage
    And the result should show the buy it now option
    And the given price is shown

  Scenario: Page next
    Given A new customer is on the ebay main page
    When the customer searches for a new item e.g. "Razer"
    And the customer selects the time ending soonest sort
    And clicks on the next page button
    Then the first result should have free postage
    And the given price is shown
