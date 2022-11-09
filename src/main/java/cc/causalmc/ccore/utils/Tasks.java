package cc.causalmc.ccore.utils;

import cc.causalmc.ccore.CCore;
import org.bukkit.Bukkit;

public class Tasks {

    public static void run(Callable callable) {
        Bukkit.getScheduler().runTask(CCore.getInstance(), callable::call);
    }

    public static void runAsync(Callable callable) {
        Bukkit.getScheduler().runTaskAsynchronously(CCore.getInstance(), callable::call);
    }

    public static void runLater(Callable callable, long delay) {
        Bukkit.getScheduler().runTaskLater(CCore.getInstance(), callable::call, delay);
    }

    public static void runAsyncLater(Callable callable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(CCore.getInstance(), callable::call, delay);
    }

    public static void runTimer(Callable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimer(CCore.getInstance(), callable::call, delay, interval);
    }

    public static void runAsyncTimer(Callable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(CCore.getInstance(), callable::call, delay, interval);
    }

    public interface Callable {
        void call();
    }
}
