package de.mariocst.mariotimer.commands;

import de.mariocst.mariotimer.TimerMain;
import de.mariocst.mariotimer.timer.Prefix;
import de.mariocst.mariotimer.timer.Timer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {
    private String prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String msg = "";

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mario.timer") || player.hasPermission("mario.*") || player.hasPermission("*") ||  player.isOp()) {
                if (args.length == 0) {
                    sendUsage(sender);
                    return true;
                }

                switch (args[0].toLowerCase()) {
                    case "resume" -> {
                        Timer timer = TimerMain.getInstance().getTimer();

                        if (timer.isRunning()) {
                            sender.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft bereits!");
                            break;
                        }

                        timer.setRunning(true);
                        sender.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestartet!");
                    }
                    case "pause" -> {
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
                            sender.sendMessage("§cUsage: §6/timer time <Seconds> <Minutes> <Hours>");
                            return true;
                        }

                        try {
                            Timer timer = TimerMain.getInstance().getTimer();

                            timer.setRunning(false);
                            timer.setSeconds(Integer.parseInt(args[1]));
                            timer.setMinutes(Integer.parseInt(args[2]));
                            timer.setHours(Integer.parseInt(args[3]));
                            sender.sendMessage(TimerMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                        } catch (NumberFormatException e) {
                            sender.sendMessage(TimerMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                            e.printStackTrace();
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
                        for(int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }
                        prefix = msg;

                        sender.sendMessage(TimerMain.getPrefix() + "Der Prefix ist nun: " + prefix);
                        TimerMain.setPrefix(prefix.replaceAll("&", "§"));
                        Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                        TimerMain.getInstance().saveConfigs();
                    }
                    default -> sendUsage(sender);
                }
            } else {
                player.sendMessage(TimerMain.getPrefix() + "§4Keine Rechte!");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.2f, 1.2f);
            }
        } else {
            if(args.length == 0) {
                sendUsage(sender);
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "resume" -> {
                    Timer timer = TimerMain.getInstance().getTimer();

                    if (timer.isRunning()) {
                        sender.sendMessage(TimerMain.getPrefix() + "§cDer Timer läuft bereits!");
                        break;
                    }

                    timer.setRunning(true);
                    sender.sendMessage(TimerMain.getPrefix() + "Der Timer wurde gestartet!");
                }
                case "pause" -> {
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
                        sender.sendMessage("§cUsage: §6/timer time <Seconds> <Minutes> <Hours>");
                        return true;
                    }

                    try {
                        Timer timer = TimerMain.getInstance().getTimer();

                        timer.setRunning(false);
                        timer.setSeconds(Integer.parseInt(args[1]));
                        timer.setMinutes(Integer.parseInt(args[2]));
                        timer.setHours(Integer.parseInt(args[3]));
                        sender.sendMessage(TimerMain.getPrefix() + "Die Zeit wurde auf " + args[1] + " Sekunden, " + args[2] + " Minuten und " + args[3] + " Stunden gesetzt!");
                    } catch (NumberFormatException e) {
                        sender.sendMessage(TimerMain.getPrefix() + "§4Deine Parameter 2, 3 und 4 müssen eine ganze Zahl sein!");
                        e.printStackTrace();
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
                    for(int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }
                    prefix = msg;

                    sender.sendMessage(TimerMain.getPrefix() + "Der Prefix ist nun: " + prefix);
                    TimerMain.setPrefix(prefix.replaceAll("&", "§"));
                    Prefix.getPrefixClass().setPrefix(prefix.replaceAll("&", "§"));
                    TimerMain.getInstance().saveConfigs();
                }
                default -> sendUsage(sender);
            }
        }

        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage("§cUsage: §6/timer resume, /timer pause, /timer time <Seconds> <Minutes> <Hours>, /timer reset, /timer save, /timer reload, /timer prefix|setprefix <Prefix>");
    }
}
