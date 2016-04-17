package de.titanium.enterprise;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game {

    private static final Gson gson = new Gson();
    private static final File path = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Enterprise-Game");

    public static void main(String[] args) throws IOException {

        // Der allgemeine Pfad zu den Spieledatein
        if(!(Game.path.exists())) {
            Game.path.mkdirs();
        }

        // Die Daten von GitHub laden
        JsonObject githubData = Game.gson.fromJson(new String(IOUtils.toString(new URL("https://api.github.com/repos/Titanium-Team/Enterprise/releases/latest"), StandardCharsets.UTF_8)), JsonObject.class);
        String latestVersion = githubData.get("tag_name").getAsString();

        Game.writeVersionToFile(null);

        // Die aktuelle Version auslesen
        String readableVersion = Game.getCurrentVersion();

        boolean updateAvailable = false;

        if(readableVersion.equalsIgnoreCase("vunknown")) {

            Game.writeVersionToFile(latestVersion);

        } else {

            double installed = Double.valueOf(readableVersion.substring(1, readableVersion.length()));
            double latest = Double.valueOf(latestVersion.substring(1, latestVersion.length()));

            if(latest == installed) {
                System.out.println("[Enterprise] Du hast bereits die neuste Version.");
            } else {

                updateAvailable = true;

                System.out.println("-------------------[Enterprise]-----------------------");
                System.out.println("Du hast aktuelle die Version " + readableVersion + " installiert, es steht aber bereits Version " + latestVersion + " bereit.");
                System.out.println("Die neue Version kannst du hier downloaden.");
                System.out.println(githubData.get("assets").getAsJsonArray().get(0).getAsJsonObject().get("browser_download_url").getAsString());
                System.out.println("");
                System.out.println("1. Ersetze die aktuelle \"Enterprise.jar\" mit der neusten \"Enterprise.jar\".");
                System.out.println("2. Entferne die \"version.json\" Datei.");
                System.out.println("3. Bei Fehlern muss ggf. der komplette Enterprise-Game Ordner auf deinem Desktop geloescht werden.");
                System.out.println("------------------------------------------------------");

            }

        }

        // Das Spiel an sich starten
        new Enterprise(latestVersion, updateAvailable);

    }

    /**
     * Gibt die Version zurück, die sich aktuelle auf der Festplatte befindet.
     * @return
     * @throws IOException
     */
    private static String getCurrentVersion() throws IOException {

        File file = new File(Game.path.getPath() + "/version.json");

        JsonObject version = Game.gson.fromJson(new String(Files.readAllBytes(Paths.get(file.getPath()))), JsonObject.class);
        return version.get("version").getAsString();

    }

    /**
     * Schreibt die übergebene Version in die Datei.
     * @param value
     * @throws IOException
     */
    private static void writeVersionToFile(String value) throws IOException {

        // Version
        File file = new File(Game.path.getPath() + "/version.json");
        if(!(file.exists())) {

            file.createNewFile();
            JsonObject version = new JsonObject();
            version.addProperty("version", "vunknown");
            Files.write(Paths.get(file.getPath()), Game.gson.toJson(version).getBytes(StandardCharsets.UTF_8));

        } else if(!(value == null)) {

            JsonObject version = new JsonObject();
            version.addProperty("version", value);
            Files.write(Paths.get(file.getPath()), Game.gson.toJson(version).getBytes(StandardCharsets.UTF_8));

        }


    }


}
