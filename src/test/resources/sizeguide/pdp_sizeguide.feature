Feature: As an online shopper, I want to view the size guide for the item I am looking at so that I can see if it will fit me properly.

    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category.

        Given I visit the <item> PLP page using <url> and view the top 20 best sellers
        When I visit the PDP page of each product and click the size guide button
        Then the size guide button should be visible and the size guide view should appear.

        Examples:
            | item                       | url                                                     |
            #| "All Boys"                 | "l/kids/all-boys"                                       |
            #| "All Girls"                | "l/kids/all-girls"                                      |
            #| "Boys School Uniform"      | "l/kids/school-uniform/all-boys-school-uniform"         |
            #| "Girls School Uniform"     | "l/kids/school-uniform/all-girls-school-uniform"        |
            | "All Baby Boys"            | "l/kids/baby-up-to-2-yrs/all-baby-boys"                 |
            #| "All Baby Girls"           | "l/kids/baby-up-to-2-yrs/all-baby-girls"                |
            #| "Newborn"                  | "l/kids/baby-up-to-2-yrs/newborn"                       |
           # | "Unisex"                   | "l/kids/baby-up-to-2-yrs/unisex-up-to-2-yrs"            |
            #| "Baby Gifts"               | "l/kids/baby-up-to-2-yrs/baby-gifts"                    |
            #| "Bath"                     | "l/kids/baby-up-to-2-yrs/bath"                          |
            #| "Feed"                     | "l/kids/baby-up-to-2-yrs/feed"                          |
            #| "Mum To Be"                | "l/kids/baby-up-to-2-yrs/mumtobe"                       |
            #| "Play"                     | "l/kids/baby-up-to-2-yrs/play"                          |
            #| "Sleep"                    | "l/kids/baby-up-to-2-yrs/sleep"                         |
            #| "Travel"                   | "l/kids/baby-up-to-2-yrs/travel"                        |

