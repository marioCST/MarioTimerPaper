package de.mariocst.mariotimer.commands;

import de.mariocst.mariotimer.TimerMain;
import de.mariocst.mariotimer.timer.Prefix;
import de.mariocst.mariotimer.timer.Timer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        StringBuilder msg = new StringBuilder();

        if (!(sender instanceof Player player)) {
            if (args.length == 0) {
                sender.sendMessage(TimerMain.getPrefix() + "/timer <start|resume>, /timer <stop|pause>, /timer time <Seconds> <Minutes> <Hours>, /timer reset, /timer save, /timer reload, /timer <setprefix> <Prefix>");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "start", "resume" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    if (timer.isRunning()) {
                        sender.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft bereits!");
                        break;
                    }

                    timer.setRunning(true);
                    sender.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestartet!");
                }
                case "stop", "pause" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    if (!timer.isRunning()) {
                        sender.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft nicht.");
                        break;
                    }

                    timer.setRunning(false);
                    sender.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestoppt!");
                }
                case "time" -> {
                    if (args.length != 4) {
                        sender.sendMessage(TimerMain.getPrefix() + "/timer time <Seconds> <Minutes> <Hours>");
                        return true;
                    }

                    try {
                        Timer timer = TimerMain.getInstance().getTimer();

                        timer.setRunning(false);
                        timer.setSeconds(Integer.parseInt(args[1]));
                        timer.setMinutes(Integer.parseInt(args[2]));
                        timer.setHours(Integer.parseInt(args[3]));
                        sender.sendMessage(TimerMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage(TimerMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                    }
                }
                case "reset" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    timer.setRunning(false);
                    timer.setSeconds(0);
                    timer.setMinutes(0);
                    timer.setHours(0);
                    sender.sendMessage(TimerMain.getPrefix() + "Der Timer wurde resettet!");
                }
                case "save" -> {
                    TimerMain.getInstance().saveConfigs();

                    sender.sendMessage(TimerMain.getPrefix() + "Die Timer Configs wurden gespeichert!");
                }
                case "reload" -> {
                    TimerMain.getInstance().reloadConfigs();

                    sender.sendMessage(TimerMain.getPrefix() + "Die Timer Configs wurden neu geladen!");
                }
                case "prefix", "setprefix" -> {
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    sender.sendMessage(TimerMain.getPrefix() + "Der Prefix ist nun: " + msg);
                    TimerMain.setPrefix(msg.toString().replaceAll("&", "§"));
                    Prefix.getPrefixClass().setPrefix(msg.toString().replaceAll("&", "§"));
                    TimerMain.getInstance().saveConfigs();
                }
                default -> sender.sendMessage(TimerMain.getPrefix() + "/timer <start|resume>, /timer <stop|pause>, /timer time <Seconds> <Minutes> <Hours>, /timer reset, /timer save, /timer reload, /timer <setprefix> <Prefix>");
            }
            return true;
        }

        if (player.hasPermission("mario.timer") || player.hasPermission("mario.*") || player.hasPermission("*") ||  player.isOp()) {
            if (args.length == 0) {
                player.sendMessage(TimerMain.getPrefix() + "/timer <start|resume>, /timer <stop|pause>, /timer time <Seconds> <Minutes> <Hours>, /timer reset, /timer save, /timer reload, /timer <setprefix> <Prefix>");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "start", "resume" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    if (timer.isRunning()) {
                        player.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft bereits!");
                        break;
                    }

                    timer.setRunning(true);
                    player.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestartet!");
                }
                case "stop", "pause" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    if (!timer.isRunning()) {
                        player.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft nicht.");
                        break;
                    }

                    timer.setRunning(false);
                    player.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestoppt!");
                }
                case "time" -> {
                    if (args.length != 4) {
                        player.sendMessage(TimerMain.getPrefix() + "/timer time <Seconds> <Minutes> <Hours>");
                        return true;
                    }

                    try {
                        Timer timer = TimerMain.getInstance().getTimer();

                        timer.setRunning(false);
                        timer.setSeconds(Integer.parseInt(args[1]));
                        timer.setMinutes(Integer.parseInt(args[2]));
                        timer.setHours(Integer.parseInt(args[3]));
                        player.sendMessage(TimerMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                    }
                    catch (NumberFormatException e) {
                        player.sendMessage(TimerMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                    }
                }
                case "reset" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    timer.setRunning(false);
                    timer.setSeconds(0);
                    timer.setMinutes(0);
                    timer.setHours(0);
                    player.sendMessage(TimerMain.getPrefix() + "Der Timer wurde resettet!");
                }
                case "save" -> {
                    TimerMain.getInstance().saveConfigs();

                    player.sendMessage(TimerMain.getPrefix() + "Die Timer Configs wurden gespeichert!");
                }
                case "reload" -> {
                    TimerMain.getInstance().reloadConfigs();

                    player.sendMessage(TimerMain.getPrefix() + "Die Timer Configs wurden neu geladen!");
                }
                case "setprefix" -> {
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    player.sendMessage(TimerMain.getPrefix() + "Der Prefix ist nun: " + msg);
                    TimerMain.setPrefix(msg.toString().replaceAll("&", "§"));
                    Prefix.getPrefixClass().setPrefix(msg.toString().replaceAll("&", "§"));
                    TimerMain.getInstance().saveConfigs();
                }
                default -> player.sendMessage(TimerMain.getPrefix() + "/timer <start|resume>, /timer <stop|pause>, /timer time <Seconds> <Minutes> <Hours>, /timer reset, /timer save, /timer reload, /timer <setprefix> <Prefix>");
            }
        }
        else {
            player.sendMessage(TimerMain.getPrefix() + "Keine Rechte!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.2f, 1.2f);
        }

        return false;
    }

    private final String[] MODES = { "start", "resume", "stop", "pause", "reset", "save", "reload", "setprefix", String.valueOf(TimerMain.getInstance().getTimer().getSeconds()) };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(MODES), completions);
            Collections.sort(completions);
        }
        else if (args.length == 2 && !args[0].equalsIgnoreCase("setprefix")) {
            StringUtil.copyPartialMatches(args[1], Collections.singleton(String.valueOf(TimerMain.getInstance().getTimer().getMinutes())), completions);
        }
        else if (args.length == 3 && !args[0].equalsIgnoreCase("setprefix")) {
            StringUtil.copyPartialMatches(args[2], Collections.singleton(String.valueOf(TimerMain.getInstance().getTimer().getHours())), completions);
        }
        return completions;
    }
}
