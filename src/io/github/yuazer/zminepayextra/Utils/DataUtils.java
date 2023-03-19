package io.github.yuazer.zminepayextra.Utils;

import io.github.yuazer.zminepayextra.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class DataUtils {
    public static int getYamlInt(File file, String path) {
        if (!file.exists()) {
            return 0;
        }
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml.getInt(path);
    }
    public static void addPlayerDay(Player player, int price) {
        try {
            File file = new File(Main.pluginPath + "day/" + player.getName());
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.set("day", getYamlInt(file, "day") + price);
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerWeek(Player player, int price) {
        try {
            File file = new File(Main.pluginPath + "week/" + player.getName());
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.set("week", getYamlInt(file, "week") + price);
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerMonth(Player player, int price) {
        try {
            File file = new File(Main.pluginPath + "month/" + player.getName());
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.set("month", getYamlInt(file, "month") + price);
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPlayerDay(Player player) {
        File file = new File(Main.pluginPath + "day/" + player.getName());
        return getYamlInt(file, "day");
    }

    public static int getPlayerWeek(Player player) {
        File file = new File(Main.pluginPath + "week/" + player.getName());
        return getYamlInt(file, "week");
    }

    public static int getPlayerMonth(Player player) {
        File file = new File(Main.pluginPath + "month/" + player.getName());
        return getYamlInt(file, "month");
    }
}
