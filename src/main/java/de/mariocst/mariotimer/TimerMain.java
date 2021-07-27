package de.mariocst.mariotimer;

import de.mariocst.mariotimer.commands.TimerCommand;
import de.mariocst.mariotimer.timer.Config;
import de.mariocst.mariotimer.timer.Prefix;
import de.mariocst.mariotimer.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimerMain extends JavaPlugin {
    private static String prefix;
    private static TimerMain timerMain;

    private Config config;
    private Timer timer;
    private Prefix prefixConfig;

    @Override
    public void onLoad() {
        timerMain = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        register();

        prefixConfig = new Prefix();
        timer = new Timer();

        log("Timer Plugin von marioCST geladen!");
    }

    @Override
    public void onDisable() {
        saveConfigs();
        log("Timer Plugin von marioCST entladen!");
    }

    public Config getConfiguration() {
        return config;
    }

    public void saveConfigs() {
        prefixConfig.save();
        timer.save();
        config.save();
    }

    public void reloadConfigs() {
        config = new Config();
        prefixConfig.reload();
        timer.reload();
    }

    private void register() {
        Bukkit.getPluginCommand("timer").setExecutor(new TimerCommand());
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        TimerMain.prefix = prefix;
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + text);
    }

    public void warning(String text) {
        getLogger().warning(getPrefix() + text);
    }

    public static TimerMain getInstance() {
        return timerMain;
    }

    public Timer getTimer() {
        return timer;
    }

    public Prefix getPrefixConfig() {
        return prefixConfig;
    }
}
