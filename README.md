# Modding/Plugin System in Java

## Introduction

This is a simple modding/plugin system in Java. It is a proof of concept how it could be done.

This was originally created as a Demo for Home Grown - a Farming Game made by Thinmatrix.

## How to use

### Implementing the Mod System

Inside the `game.modding` folder you can see a few files.
The `ModLoader` will load all mods that are inside the `mods` folder and create, instantiate and initialize them with a reference to the `Game` object.
The `GameMod` is an interface that every mod has to implement.

Inside the `Game` class a new `ModLoader` is created and updated every game cycle.

### Creating a Mod

To create a mod you have to create a new class that implements the `GameMod` interface.
Inside the `GameMod` interface you can see a few methods that you have to implement.

```java
public interface GameMod {
    void init(Game game);
    void update();
}
```

The `init` method is called when the mod is loaded and the `update` method is called every game cycle.

### Setting up this project inside your IDE

Since this project uses Maven it already comes with the correct configuration.

The Modders usually don't have access to the source code of the game, so they have to use the Game JAR itself as a dependency.

If they got the JAR they can modify the `pom.xml` file and change the `systemPath` of the dependency to the path of the JAR.

```xml
    <dependencies>
        <dependency>
            <groupId>me.lebogo</groupId>
            <artifactId>game</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <!-- <systemPath>${project.basedir}/../game/target/game-1.0-SNAPSHOT.jar</systemPath> -->
            <systemPath>JAR PATH HERE</systemPath>
        </dependency>
    </dependencies>
```

### Creating a Mod JAR

Creating a Mod JAR is very simple. If you have Maven installed you can just run `mvn clean package` inside the mod folder and it will create a JAR inside the `target` folder.

This jar can be copied into the `mods` folder of the game and it will be loaded automatically.
