package org.black_ixx.bossshop.pointsystem;

import me.justeli.coins.main.Coins;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

public class BSPointsPluginCoins extends BSPointsPlugin {
    public BSPointsPluginCoins() {
        super("Coins");
    }


    @Override
    public double getPoints(OfflinePlayer player) {
        return Coins.getEconomy().getBalance(player);
    }

    @Override
    public double setPoints(OfflinePlayer player, double points) {
        double current = Coins.getEconomy().getBalance(player);
        if (current > points) {
            Coins.getEconomy().withdrawPlayer(player, current - points);
        } else {
            Coins.getEconomy().depositPlayer(player, points - current);
        }
        return points;
    }

    @Override
    public double takePoints(OfflinePlayer player, double points) {
        EconomyResponse response = Coins.getEconomy().withdrawPlayer(player, points);
        return response.balance;
    }

    @Override
    public double givePoints(OfflinePlayer player, double points) {
        EconomyResponse response = Coins.getEconomy().depositPlayer(player, points);
        return response.balance;
    }

    @Override
    public boolean usesDoubleValues() {
        return false;
    }

}
