package de.mariocst.mariotimer.timer;

import de.mariocst.mariotimer.TimerMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private boolean running;
    private int seconds;
    private int minutes;
    private int hours;

    public Timer() {
        Config config = TimerMain.getInstance().getConfiguration();

        this.running = false;

        if (config.getConfig().contains("timer.seconds")) {
            this.seconds = config.getConfig().getInt("timer.seconds");
        } else {
            this.seconds = 0;
        }

        if (config.getConfig().contains("timer.minutes")) {
            this.minutes = config.getConfig().getInt("timer.minutes");
        }
        else {
            this.minutes = 0;
        }

        if (config.getConfig().contains("timer.hours")) {
            this.hours = config.getConfig().getInt("timer.hours");
        }
        else {
            this.hours = 0;
        }

        run();
    }

    public void reload() {
        Config config = TimerMain.getInstance().getConfiguration();

        this.running = false;

        if (config.getConfig().contains("timer.seconds")) {
            this.seconds = config.getConfig().getInt("timer.seconds");
        } else {
            this.seconds = 0;
        }

        if (config.getConfig().contains("timer.minutes")) {
            this.minutes = config.getConfig().getInt("timer.minutes");
        }
        else {
            this.minutes = 0;
        }

        if (config.getConfig().contains("timer.hours")) {
            this.hours = config.getConfig().getInt("timer.hours");
        }
        else {
            this.hours = 0;
        }

        run();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int time) {
        this.seconds = time;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void sendActionBar() {

        for (Player player: Bukkit.getOnlinePlayers()) {

            if (!isRunning()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Timer ist pausiert"));
                continue;
            }

            String secondsString = String.valueOf(getSeconds());
            String minutesString = String.valueOf(getMinutes());
            String hoursString = String.valueOf(getHours());

            if (getSeconds() < 10) {
                secondsString = "0" + getSeconds();
            }

            if (minutes < 10) {
                minutesString = "0" + getMinutes();
            }

            if (hours < 10) {
                hoursString = "0" + getHours();
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + hoursString + ":" + minutesString + ":" + secondsString));
        }
    }

    public void save() {
        Config config = TimerMain.getInstance().getConfiguration();

        config.getConfig().set("timer.seconds", seconds);
        config.getConfig().set("timer.minutes", minutes);
        config.getConfig().set("timer.hours", hours);
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if (!isRunning()) {
                    return;
                }

                setSeconds(getSeconds() + 1);

                if (getSeconds() == 60) {
                    setSeconds(0);
                    setMinutes(getMinutes() + 1);
                }

                if (getMinutes() == 60) {
                    setMinutes(0);
                    setHours(getHours() + 1);
                }

                if (getSeconds() > 60) {
                    setMinutes(getMinutes() + getSeconds() / 60);
                    setSeconds(getSeconds() % 60);
                }

                if (getMinutes() > 60) {
                    setHours(getHours() + getMinutes() / 60);
                    setMinutes(getMinutes() % 60);
                }
            }
        }.runTaskTimer(TimerMain.getInstance(), 20, 20);
    }
}
