package io.github.yuazer.zminepayextra.Hook;

import io.github.yuazer.zminepayextra.Main;
import io.github.yuazer.zminepayextra.Utils.DataUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class DataHook extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "zminepayextra";
    }

    @Override
    public String getAuthor() {
        return "Z菌";
    }

    @Override
    public String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("day")) {
            return String.valueOf(DataUtils.getPlayerDay(player));
        }
        if (identifier.equalsIgnoreCase("week")) {
            return String.valueOf(DataUtils.getPlayerWeek(player));
        }
        if (identifier.equalsIgnoreCase("month")) {
            return String.valueOf(DataUtils.getPlayerMonth(player));
        }
        return "error";
    }
}
