package de.titanium.enterprise;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Game {

    private static final Gson gson = new Gson();
    private static final File path = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Enterprise-Game");

    private static final String currentVersion = "v0.8";

    public static void main(String[] args) throws IOException {

        // Der allgemeine Pfad zu den Spieledatein
        if(!(Game.path.exists())) {
            Game.path.mkdirs();
        }

        // Die Daten von GitHub laden
        try {
            JsonObject githubData = Game.gson.fromJson(new String(IOUtils.toString(new URL("https://api.github.com/repos/Titanium-Team/Enterprise/releases/latest"), StandardCharsets.UTF_8)), JsonObject.class);
            String latestVersion = githubData.get("tag_name").getAsString();

            boolean updateAvailable = false;
            double installed = Double.valueOf(Game.currentVersion.substring(1, Game.currentVersion.length()));
            double latest = Double.valueOf(latestVersion.substring(1, latestVersion.length()));

            if (latest == installed) {
                Game.log(
                        String.format("Du hast bereits die neuste Version %s.", Game.currentVersion),
                        "Viel Spaß beim Spielen."
                );
            } else if(latest > installed) {

                updateAvailable = true;

                JsonArray assets = githubData.get("assets").getAsJsonArray();

                if(assets.size() == 1) {
                    JsonObject asset = assets.get(0).getAsJsonObject();

                    if(asset.get("content_type").getAsString().equalsIgnoreCase("application/java-archive")) {

                        String body = githubData.get("body").getAsString();

                        Game.log(
                                String.format("Du hast aktuell die Version %s installiert, es steht aber bereits Version %s bereit.", Game.currentVersion, latestVersion),
                                "",
                                "Die neue Version kannst du hier downloaden.",
                                String.format("Link: %s", asset.get("browser_download_url").getAsString()),
                                String.format("File-Size: %s", FileUtils.byteCountToDisplaySize(asset.get("size").getAsInt())),
                                String.format("Downloads: %d", asset.get("download_count").getAsInt()),
                                "",
                                "Change-Log: ",
                                body
                        );
                    } else {
                        Game.log(
                                "GitHub stellt aktuell ein Update mit einem falschen Format zur verfuegung. Bitte gedulte dich. Der Fehler sollte bald verbessert werden."
                        );
                    }

                } else {
                    Game.log("GitHub scheint aktuell nicht die neuste Datei fuer Version " + latestVersion + " bereitzustellen.");
                }

            } else {

                Game.log(
                        "Du scheinst eine unveroeffentlichte Version des Spiels zu benutzen. Solltest du Fehler o.ae. im",
                        "Spiel finden, dann wuerden wir uns ueber einen Hinweis freuen."
                );

            }

            // Das Spiel an sich starten
            new Enterprise(latestVersion, updateAvailable);
        } catch (IOException exception) {

            Game.log(
                    "Bei der Abfrage nach einer neuen Version ist ein Fehler aufgetreten.",
                    String.format("Sie verwenden aktuell die Version: %s.", Game.currentVersion),
                    "",
                    "Dieser Fehler kann auftreten, wenn GitHub nicht erreichbar ist oder weil du aktuell nicht mit dem",
                    "Internet verbunden bist, sobald es wieder eine Verbindung gibt, werden wir pruefen, ob eine neue",
                    "Version des Spiel bereit steht und dich dann benachrichtigen."
            );
            new Enterprise(Game.currentVersion, false);

        }

    }
    private static void log(String... lines) {
        System.out.println("-------------------[Enterprise]-----------------------");
        for(String line : lines) System.out.println(line);
        System.out.println("------------------------------------------------------");
    }

}
