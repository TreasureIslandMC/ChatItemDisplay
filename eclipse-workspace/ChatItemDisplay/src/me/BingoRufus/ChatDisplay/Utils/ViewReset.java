package me.BingoRufus.ChatDisplay.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.BingoRufus.ChatDisplay.ListenersAndExecutors.ChatDisplayListener;

public class ViewReset {
	public static void reset(Player player, Inventory DisplayGUI) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (ChatDisplayListener.DisplayedItem.values().contains(p.getOpenInventory().getTopInventory())) {
				if (player.equals((Player) p.getOpenInventory().getTopInventory().getHolder())) {
					p.openInventory(DisplayGUI);

				}
				if (ChatDisplayListener.DisplayedShulkerBox.values().contains(p.getOpenInventory().getTopInventory())) {
					if (player.equals((Player) p.getOpenInventory().getTopInventory().getHolder())) {
						p.openInventory(DisplayGUI);

					}
				}
			}
		}
	}
}
