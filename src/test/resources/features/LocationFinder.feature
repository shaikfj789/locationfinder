Feature: Cost Cutter Location Finder

   Scenario Outline: Location Finder and Store Count
    Given I am on the Location Finder page
    When I search for "<Location>"
    Then the number of Stores <Store Count> is displayed
    And the search result should have "<Location>"
    Examples:
      | Location | Store Count |
      | York   | 14 |
      | London  | 90 |
      | India   | 0 |
       
     
      
   Scenario: Find my store  
    Given I am on the Location Finder page
    When I search for "York"
    Then the search result displays a map
    And location results are displayed
   