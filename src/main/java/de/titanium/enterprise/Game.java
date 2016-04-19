package de.titanium.enterprise;

import com.github.zafarkhaja.semver.Version;
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

    private final static Version installedVersion = Version.valueOf(Game.class.getPackage().getImplementationVersion());
    private static Version latestVersion;

    public static void main(String[] args) {

        // The path to the Game-Data-Folder
        if(!(Game.path.exists())) {
            Game.path.mkdirs();
        }

        try {

            // Load the stuff from GitHub
            JsonObject githubData = Game.gson.fromJson(new String(IOUtils.toString(new URL("https://api.github.com/repos/Titanium-Team/Enterprise/releases/latest"), StandardCharsets.UTF_8)), JsonObject.class);

            // Well, to support the Version library and to satisfy the latest semantic versioning standards we're going
            // to fix these value to fit the requirements.
            String latestVersionValue = githubData.get("tag_name").getAsString();
            if(latestVersionValue.startsWith("v")) {

                // If its starts with an 'v', than we know it's the old versioning pattern.

                // Remove the 'v' in front of
                latestVersionValue = latestVersionValue.substring(1);

                // Append the required '.0' at the end
                latestVersionValue += ".0";

            }

            Game.latestVersion = Version.valueOf(latestVersionValue);

            boolean updateAvailable = false;

            if (Game.getInstalledVersion().equals(Game.getLatestVersion())) {
                Game.log(
                        String.format("You already have the latest version %s.", Game.getInstalledVersion().toString()),
                        "Enjoy the game."
                );
            } else if(Game.getLatestVersion().greaterThan(Game.getInstalledVersion())) {

                updateAvailable = true;

                JsonArray assets = githubData.get("assets").getAsJsonArray();

                if(assets.size() == 1) {
                    JsonObject asset = assets.get(0).getAsJsonObject();

                    if(asset.get("content_type").getAsString().equalsIgnoreCase("application/java-archive")) {

                        String body = githubData.get("body").getAsString();

                        Game.log(
                                String.format("Your game is outdated. You're using %s please upgrade your game to %s.", Game.getInstalledVersion().toString(), Game.getLatestVersion().toString()),
                                "",
                                "You can download the latest version here:",
                                String.format("Link: %s", asset.get("browser_download_url").getAsString()),
                                String.format("File-Size: %s", FileUtils.byteCountToDisplaySize(asset.get("size").getAsInt())),
                                String.format("Downloads: %d", asset.get("download_count").getAsInt()),
                                "",
                                "Change-Log: ",
                                body
                        );
                    } else {
                        Game.log(
                                "GitHub provides a file with an invalid file type. Please wait until we fixed this issue."
                        );
                    }

                } else {
                    Game.log(
                            String.format("GitHub seems to provide no new file for the latest version %s", Game.getLatestVersion().toString())
                    );
                }

            } else {

                Game.log(
                        "This is a alpha-built."
                );

            }

            // Das Spiel an sich starten
            new Enterprise(Game.getLatestVersion(), updateAvailable);
        } catch (IOException exception) {

            Game.log(
                    String.format("We weren't able to check for a new version. You are using version %s.", Game.getInstalledVersion().toString()),
                    "",
                    "This error can occur when you aren't connected to the internet or the GitHub API is not available. We will start",
                    "the game in a offline-mode."
            );
            new Enterprise(Game.getInstalledVersion(), false);

        }

    }

    public static Version getInstalledVersion() {
        return Game.installedVersion;
    }

    public static Version getLatestVersion() {
        return Game.latestVersion;
    }

    private static void log(String... lines) {
        System.out.println("-------------------[Enterprise]-----------------------");
        for(String line : lines) System.out.println(line);
        System.out.println("------------------------------------------------------");
    }

}
