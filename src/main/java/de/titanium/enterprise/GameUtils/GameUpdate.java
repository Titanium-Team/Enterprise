package de.titanium.enterprise.GameUtils;

import de.titanium.enterprise.Game;
import de.titanium.enterprise.Loading.Loadable;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by Yonas on 19.04.2016.
 */
public class GameUpdate implements Loadable {

    private final URL latestVersion;

    public GameUpdate(URL latestVersion) {
        this.latestVersion = latestVersion;
    }

    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void load() {

        // If for some reasons we get valid data we won't even trie to update it.
        if (Game.getGameMode() == GameMode.OFFLINE) {
            return;
        }

        // If no update is available, then...
        if (!(Game.isUpdateAvailable())) {
            return;
        }

        // If for some reason the update url is null then hell yea just don't give a duck...
        if (this.latestVersion == null) {
            return;
        }

        try {

            final File currentJar = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

            this.name = "Downloading Update";
            FileUtils.copyURLToFile(
                    this.latestVersion,
                    currentJar
            );

            System.exit(0);

        } catch (Exception e) {
            this.name = String.format("Updated failed - %s", e.getClass().getSimpleName());
        }

    }

}

