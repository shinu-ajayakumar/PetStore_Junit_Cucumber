Feature: Launch OpenStore and Perform Multiple Searches

  Scenario Outline: Shop Lowest Price food and Highest Rated Bed for Dogs Supplies
    Given Initialize Chrome driver and launch the application
    #When Provide "<searchText>" in Search Box and Click Search button
    #And Click <category> under Shop by Category and Select <sortOption> from Sort by DDL
    #And After Clicking first product enter quantity <qtyCount> and Click Add to Cart button
    #Then Cart Count <cartCount> will be displayed

    #Examples: 
      #| searchText | category     | sortOption    | qtyCount | cartCount |
     # | Food       | Dog Supplies | Lowest Price  |        5 |         5 |
     # | Bed        | Dog Supplies | Highest Rated |        2 |         2 |
