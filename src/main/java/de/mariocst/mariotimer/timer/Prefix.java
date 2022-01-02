package de.mariocst.mariotimer.timer;

import de.mariocst.mariotimer.TimerMain;

public class Prefix {
    private String prefix;

    private static Prefix prefixClass;

    public Prefix() {
        prefixClass = this;

        Config config = TimerMain.getInstance().getConfiguration();

        if (config.getConfig().contains("prefix")) {
            this.prefix = config.getConfig().getString("prefix");
        }
        else {
            this.prefix = "§8[§6Timer§8] §b";
        }

        TimerMain.setPrefix(this.prefix);
    }

    public void reload() {
        Config config = TimerMain.getInstance().getConfiguration();

        if (config.getConfig().contains("prefix")) {
            this.prefix = config.getConfig().getString("prefix");
        }
        else {
            this.prefix = "§8[§6Timer§8] §b";
        }

        TimerMain.setPrefix(this.prefix);
    }

    public static Prefix getPrefixClass() {
        return prefixClass;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void save() {
        Config config = TimerMain.getInstance().getConfiguration();

        config.getConfig().set("prefix", this.prefix);
    }
}
