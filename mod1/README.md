# mod1

This is part of the Home Grown Modding Proposal and is NOT part of the game or a real mod for the game.

## Building

In order to build this project, you first need to build the `game` Project.

- Run the Maven Lifecycle `game/package`
- Run the Maven Lifecycle `mod1/package`

This will automatically create a zip file with the proposed mod format for Home Grown.

Inside the zip are the following files:

- mod1.zip
  - mod1-1.0-SNAPSHOT.jar
  - ModInfo.cba
  - Translations.cba
  - icon.png
  - entities/
    - Objects/
      - sprinkler/
        - EntityInfo_sprinkler.cba
        - sprinkler0.cba
        - sprinkler1.cba
    - Plants/
      - pumpkinPlant/
        - EntityInfo_pumpkinPlant.cba
        - pumpkin0.cba
        - pumpkin1.cba
        - pumpkin2.cba
        - pumpkin3.cba
        - pumpkinFruit.cba
      - strawberryPlant/
        - EntityInfo_strawberryPlant.cba
        - strawberry0.cba
        - strawberry1.cba
        - strawberry2.cba
        - strawberry3.cba
        - strawberryFruit.cba
  - items/
    - Objects/
      - sprinkler/
        - icon.png
        - ItemInfo_sprinkler.cba
    - Produce/
      - pumpkin/
        - icon.png
        - ItemInfo_pumpkin.cba
      - strawberry/
        - icon.png
        - ItemInfo_strawberry.cba
    - Seeds/
      - pumpkinSeed/
        - icon.png
        - ItemInfo_pumpkinSeed.cba
      - strawberrySeed/
        - icon.png
        - ItemInfo_strawberrySeed.cba
