Feature: This feature file contains test scenarios for the Central Product


 @CP2
    Scenario: Find and Count Videos Feeds
        Given User navigates to News & Features section
        Then the user should be able to count the number of video feeds
        And the user should be able to count the number of video feeds present in the page >= 3d
        
 @CP1
    Scenario: Extract and Store Jackets Data
    Given User navigates to Men's Jackets section
    Then User extracts all jackets data and stores in a file 
     