package de.titanium.enterprise;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Game {

    private static final Gson gson = new Gson();
    private static final File path = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Enterprise-Game");

    private static final String currentVersion = "v0.6";

    public static void main(String[] args) throws IOException {

        // Der allgemeine Pfad zu den Spieledatein
        if(!(Game.path.exists())) {
            Game.path.mkdirs();
        }

        // Die Daten von GitHub laden
        JsonObject githubData = Game.gson.fromJson(new String(IOUtils.toString(new URL("https://api.github.com/repos/Titanium-Team/Enterprise/releases/latest"), StandardCharsets.UTF_8)), JsonObject.class);
        String latestVersion = githubData.get("tag_name").getAsString();

        boolean updateAvailable = false;
        double installed = Double.valueOf(Game.currentVersion.substring(1, Game.currentVersion.length()));
        double latest = Double.valueOf(latestVersion.substring(1, latestVersion.length()));

        if(latest == installed) {
            System.out.println("[Enterprise] Du hast bereits die neuste Version.");
        } else {

            updateAvailable = true;

            System.out.println("-------------------[Enterprise]-----------------------");
            System.out.println("Du hast aktuell die Version " + Game.currentVersion + " installiert, es steht aber bereits Version " + latestVersion + " bereit.");
            System.out.println("Die neue Version kannst du hier downloaden.");
            System.out.println(githubData.get("assets").getAsJsonArray().get(0).getAsJsonObject().get("browser_download_url").getAsString());
            System.out.println("------------------------------------------------------");

        }

        // Das Spiel an sich starten
        new Enterprise(latestVersion, updateAvailable);

    }

}
