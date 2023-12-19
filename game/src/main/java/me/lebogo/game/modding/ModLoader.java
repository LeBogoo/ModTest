package me.lebogo.game.modding;

import me.lebogo.game.Game;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModLoader {

    private final File modsDirectory;
    private final Game game;
    private final List<GameMod> loadedMods = new ArrayList<>();

    public ModLoader(String modsDirectory, Game game) {
        this.modsDirectory = new File(modsDirectory);
        this.game = game;
    }

    public void load() {
        // Load jar files in mods Directory
        if (!modsDirectory.exists()) {
            modsDirectory.mkdirs();
        }

        for (File file : Objects.requireNonNull(modsDirectory.listFiles())) {
            try {
                URL url = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
                String className = "me.lebogo.game.Mod";
                Class<?> loadedClass = classLoader.loadClass(className);
                classLoader.close();

                Object instance = loadedClass.newInstance();

                GameMod mod = (GameMod) instance;
                mod.init(game);

                loadedMods.add(mod);
            } catch (Exception e) {
                System.out.println("Cannot load Mod \"" + file.toString() + "\". Reason: " + e);
                e.printStackTrace();
            }
        }
    }

    public void update() {
        for (GameMod loadedMod : loadedMods) {
            loadedMod.update();
        }
    }
}
