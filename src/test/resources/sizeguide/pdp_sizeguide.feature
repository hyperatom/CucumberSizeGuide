Feature: As an online shopper, I want to view the size guide for the item I am looking at so that I can see if it will fit me properly.


    @kids
    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category of the Kids BU.

        Given I visit the <item> PLP page using <url> and view the top 3 best sellers
        When I visit the PDP page of each product and click the size guide button
        Then the size guide button should be visible and the size guide view should appear.

        Examples:
            | item                       | url                                                     |
            | "All Boys"                 | "l/kids/all-boys"                                       |
            | "Boys School Uniform"      | "l/kids/school-uniform/all-boys-school-uniform"         |


    @women
    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category of the Womenswear BU.

        Given I visit the <item> PLP page using <url> and view the top 3 best sellers
        When I visit the PDP page of each product and click the size guide button
        Then the size guide button should be visible and the size guide view should appear.

        Examples:
          | item                        | url                                                       |
          | "Cardigans"                 | "l/women/cardigans"                                       |
          | "Coats and Jackets"         | "l/women/coats-and-jackets"                               |

