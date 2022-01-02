package de.mariocst.mariotimer;

import de.mariocst.mariotimer.commands.TimerCommand;
import de.mariocst.mariotimer.timer.Config;
import de.mariocst.mariotimer.timer.Prefix;
import de.mariocst.mariotimer.timer.Timer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        this.prefixConfig = new Prefix();
        this.timer = new Timer();

        this.register();

        this.log("Timer Plugin von marioCST geladen!");
    }

    @Override
    public void onDisable() {
        this.saveConfigs();
        this.log("Timer Plugin von marioCST entladen!");
    }

    public Config getConfiguration() {
        return this.config;
    }

    public void saveConfigs() {
        this.prefixConfig.save();
        this.timer.save();
        this.config.save();
    }

    public void reloadConfigs() {
        this.config = new Config();
        this.prefixConfig.reload();
        this.timer.reload();
    }

    private void register() {
        Objects.requireNonNull(this.getCommand("timer")).setExecutor(new TimerCommand());
        Objects.requireNonNull(this.getCommand("timer")).setTabCompleter(new TimerCommand());
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        TimerMain.prefix = prefix;
    }

    public void log(String text) {
        this.getServer().getLogger().info(prefix + text);
    }

    public static TimerMain getInstance() {
        return timerMain;
    }

    public Timer getTimer() {
        return this.timer;
    }
}
