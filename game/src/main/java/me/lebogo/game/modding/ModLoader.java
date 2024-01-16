package me.lebogo.game.modding;

import me.lebogo.game.Game;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
                System.out.println("Loading mod jar " + file);

                // Create new JarFile object to interact with the jar itself
                JarFile jarFile = new JarFile(file);

                // Enumerate over all entries from the jar file
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String resourceName = entry.getName();

                    // Pick out resources, that you're interested in. In this example, I've picked my ModInfo and sprinkler ItemInfo files.
                    if (resourceName.contains("ModInfo") || resourceName.contains("ItemInfo_sprinkler")) {
                        // In order to read the resource, you'll need to get the InputStream of that resource.
                        InputStream inputStream = jarFile.getInputStream(entry);

                        if (inputStream != null) {
                            // Use a Scanner to read the content from the InputStream
                            Scanner scanner = new Scanner(inputStream);
                            StringBuilder content = new StringBuilder();

                            while (scanner.hasNextLine()) {
                                content.append(scanner.nextLine()).append("\n");
                            }

                            // Close the InputStream and the Scanner
                            scanner.close();
                            inputStream.close();

                            // TODO - parse contents using cba parser.

                            System.out.println("Content of " + resourceName + ":");
                            System.out.println(content);
                        }
                    }
                }

                // Close jar file to release system resources
                jarFile.close();



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
