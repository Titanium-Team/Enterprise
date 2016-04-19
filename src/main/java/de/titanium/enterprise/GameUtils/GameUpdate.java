package de.titanium.enterprise.GameUtils;

import de.titanium.enterprise.Game;
import de.titanium.enterprise.GameUtils.GameMode;
import de.titanium.enterprise.Loading.Loadable;

import java.net.URL;

/**
 * Created by Yonas on 19.04.2016.
 */
public class GameUpdate implements Loadable {

    private final URL latestVersion;

    public GameUpdate(URL latestVersion) {
        this.latestVersion = latestVersion;
    }

    @Override
    public String getName() {
        return "Updating";
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

        DownloadThread downloadThread = new DownloadThread(this.latestVersion);

        while (!(downloadThread.isDone())) {
            downloadThread.interrupt();
        }

        /*FileUtils.copyURLToFile(
            this.latestVersion,
            new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
        );*/

    }

}

