Feature: As an online shopper, I want to view the size guide for the item I am looking at so that I can see if it will fit me properly.


    @kids
    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category of the Kids BU.

        Given I visit the <item> PLP page using <url> and view the top 10 best sellers
        When I visit the PDP page of each product and click the size guide button
        Then the size guide button should be visible and the size guide view should appear.

        Examples:
          | item                       | url                                                     |
          | "All Boys"                 | "l/kids/all-boys"                                       |
          | "All Girls"                | "l/kids/all-girls"                                      |
          | "Boys School Uniform"      | "l/kids/school-uniform/all-boys-school-uniform"         |
          | "Girls School Uniform"     | "l/kids/school-uniform/all-girls-school-uniform"        |
          | "All Baby Boys"            | "l/kids/baby-up-to-2-yrs/all-baby-boys"                 |
          | "All Baby Girls"           | "l/kids/baby-up-to-2-yrs/all-baby-girls"                |
          | "Newborn"                  | "l/kids/baby-up-to-2-yrs/newborn"                       |
          | "Unisex"                   | "l/kids/baby-up-to-2-yrs/unisex-up-to-2-yrs"            |
          | "Baby Gifts"               | "l/kids/baby-up-to-2-yrs/baby-gifts"                    |
          | "Bath"                     | "l/kids/baby-up-to-2-yrs/bath"                          |
          | "Feed"                     | "l/kids/baby-up-to-2-yrs/feed"                          |
          | "Mum To Be"                | "l/kids/baby-up-to-2-yrs/mumtobe"                       |
          | "Play"                     | "l/kids/baby-up-to-2-yrs/play"                          |
          | "Sleep"                    | "l/kids/baby-up-to-2-yrs/sleep"                         |
          | "Travel"                   | "l/kids/baby-up-to-2-yrs/travel"


    @women
    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category of the Womenswear BU.

        Given I visit the <item> PLP page using <url> and view the top 10 best sellers
        When I visit the PDP page of each product and click the size guide button
        Then the size guide button should be visible and the size guide view should appear.

        Examples:
          | item                        | url                                                    |
          | "Cardigans"                 | "l/women/cardigans"                                      |
          | "Coats & Jackets"           | "l/women/coats-and-jackets"                              |
          | "Dresses"                   | "l/women/dresses"                                        |
          | "Jeans & Jeggings"          | "l/women/jeans-and-jeggings"                             |
          | "Jumpers"                   | "l/women/jumpers"                                        |
          | "Jumpsuits & Playsuits"     | "l/women/dresses/jumpsuits-and-playsuits"                |
          | "Linen"                     | "l/women/linen"                                          |
          | "Party & Occasionwear"      | "l/women/partywear"                                      |
          | "Petite"                    | "l/women/petite"                                         |
          | "Plus"                      | "l/women/plus-size-clothing"                             |
          | "Shirts & Blouses"          | "l/women/shirts-and-blouses"                             |
          | "Shorts"                    | "l/women/shorts"                                         |
          | "Skirts"                    | "l/women/skirts"                                         |
          | "Sportswear"                | "l/women/sportswear"                                     |
          | "Suits & Work Dresses"      | "l/women/suits-and-work-dresses"                         |
          | "Swim & Beachwear"          | "l/women/swimwear-and-beachwear"                         |
          | "Tights"                    | "l/lingerie/tights"                                      |
          | "Tops & T-Shirts"           | "l/women/tops-and-tshirts"                               |
          | "Trousers & Chinos"         | "l/women/trousers-and-chinos"                            |
          | "Tunics"                    | "l/women/tunics"                                         |
          | "All Accessories"           | "l/women/accessories"                                    |
          | "Jewellery"                 | "l/women/jewellery"                                      |
          | "Watches"                   | "l/women/jewellery/watches"                              |
          | "All Shoes & Sandals"       | "l/women/shoes-and-boots"                                |
          | "Autograph"                 | "l/women/autograph"                                      |
          | "Best Of British"           | "l/women/best-of-british/best-of-british"                |
          | "Classic"                   | "l/women/classic"                                        |
          | "Indigo"                    | "l/women/indigo-collection"                              |
          | "Limited Edition"           | "l/women/limited-edition"                                |
          | "M&S Collection"            | "l/women/mands-collection"                               |
          | "Per Una"                   | "l/women/per-una"                                        |
          | "Speziale"                  | "l/women/per-una/speziale"                               |

    @men
    Scenario Outline: Size guide visibility on 100 bestselling products for each top level category of the Menswear BU.

      Given I visit the <item> PLP page using <url> and view the top 10 best sellers
      When I visit the PDP page of each product and click the size guide button
      Then the size guide button should be visible and the size guide view should appear.

      Examples:
        | item                        | url                                                      |
        | "Cardigans"                 | "l/women/cardigans"                                      |
        | "Coats and Jackets"         | "l/women/coats-and-jackets"                              |

