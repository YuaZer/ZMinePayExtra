package io.github.yuazer.zminepayextra;

import io.github.yuazer.zminepayextra.Commands.MainCommands;
import io.github.yuazer.zminepayextra.Hook.DataHook;
import io.github.yuazer.zminepayextra.PayEvents.Join;
import io.github.yuazer.zminepayextra.PayEvents.PayData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Main extends JavaPlugin {
    public static List<String> dataName = Arrays.asList("day", "week", "month");
    private static Main instance;
    public static String pluginPath = "plugins/ZMinePayExtra/";

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        logLoaded(this);
        initDataFolder();
        saveDefaultConfig();
        Bukkit.getPluginCommand("zminepayextra").setExecutor(new MainCommands());
        Bukkit.getPluginManager().registerEvents(new Join(),this);
        Bukkit.getPluginManager().registerEvents(new PayData(),this);
        runTask();
        DataHook hook = new DataHook();
        if (hook.canRegister()){
            hook.register();
            Bukkit.getLogger().info("§a变量注册成功!");
        }
    }

    public void onDisable() {
        logDisable(this);
    }

    public void initDataFolder() {
        for (String n : dataName) {
            File file = new File(pluginPath + n);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    public static void logLoaded(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §f已加载", plugin.getName()));
        Bukkit.getLogger().info("§b作者:§eZ菌");
        Bukkit.getLogger().info("§b版本:§e" + plugin.getDescription().getVersion());
    }

    public static void logDisable(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §c已卸载", plugin.getName()));
    }

    public void runTask() {
        // 启动定时任务，每天凌晨2点清除一次pluginPath+"day"文件夹
        LocalTime cleanTime = LocalTime.of(2, 0);
        LocalDateTime nextClean = LocalDateTime.now().with(cleanTime);
        if (nextClean.isBefore(LocalDateTime.now())) {
            nextClean = nextClean.plusDays(1);
        }
        long delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), nextClean);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            cleanDirectory(pluginPath + "day");
        }, delay, 24L * 60L * 60L * 20L);

        // 启动定时任务，每周清除一次pluginPath+"week"文件夹
        String[] args = getConfig().getString("DataClear.week").split("//");
        LocalTime cleanTime2 = LocalTime.of(Integer.parseInt(args[1]), 0);
        LocalDateTime nextClean2 = LocalDateTime.now().with(cleanTime2);
        if (nextClean2.isBefore(LocalDateTime.now())) {
            nextClean2 = nextClean2.plusWeeks(1);
        }
        delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), nextClean2);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (LocalDateTime.now().getDayOfWeek().getValue() == Integer.parseInt(args[0])) {
                cleanDirectory(pluginPath + "week");
            }
        }, delay, 7L * 24L * 60L * 60L * 20L);

        // 启动定时任务，每月清除一次pluginPath+"month"文件夹
        String[] args1 = getConfig().getString("DataClear.month").split("//");
        LocalTime cleanTime3 = LocalTime.of(Integer.parseInt(args1[1]), 0);
        LocalDateTime nextClean3 = LocalDateTime.now().with(cleanTime3);
        if (nextClean3.isBefore(LocalDateTime.now())) {
            nextClean3 = nextClean3.plusMonths(1);
        }
        delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), nextClean3);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (LocalDateTime.now().getDayOfMonth() == Integer.parseInt(args1[0])) {
                cleanDirectory(pluginPath + "month");
            }
        }, delay, 30L * 24L * 60L * 60L * 20L);
    }

    private void cleanDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            // 遍历目录下的所有文件并删除
            File[] files = directory.listFiles();
            for (File file : files) {
                file.delete();
            }
            getLogger().info("Cleaned directory " + directoryPath + " on " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
    }
}
