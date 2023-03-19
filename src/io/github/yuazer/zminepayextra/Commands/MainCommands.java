package io.github.yuazer.zminepayextra.Commands;

import io.github.yuazer.zminepayextra.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("zminepayextra")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§b/zminepayextra §a简写-> §b/zme");
                sender.sendMessage("§b/zminepayextra reload §a重载配置文件");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload") && sender.isOp()) {
                Main.getInstance().reloadConfig();
                sender.sendMessage(Main.getInstance().getConfig().getString("Message.reload").replace("&", "§"));
                return true;
            }
        }
        return false;
    }
}
