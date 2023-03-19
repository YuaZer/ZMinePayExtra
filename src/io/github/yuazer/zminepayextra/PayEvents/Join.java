package io.github.yuazer.zminepayextra.PayEvents;

import io.github.yuazer.zminepayextra.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class Join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            for (String folder : Main.dataName) {
                File file = new File("plugins/ZMinePayExtra/" + folder + "/" + e.getPlayer().getName());
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
