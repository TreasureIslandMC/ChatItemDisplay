package me.bingorufus.chatitemdisplay.util.iteminfo;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

public class ItemStackStuff {
	public ItemStack deserialize(String serialized) {

		return new ItemStack(Material.AIR);
	}

	public String serialize(ItemStack item) {
		return "";
	}

	public String makeStringPretty(String s) {
		switch (s) {
		default:
			String out = null;
			String[] Nameparts = s.toLowerCase().split("_");
			for (String part : Nameparts) {
				part = part.substring(0, 1).toUpperCase() + part.substring(1);
				if (out == null) {
					out = part;
					continue;
				}

				out += " ";
				out += part;
			}

			return out;
		}
	}

	public String ItemName(ItemStack item) {

		String out = "";
		if (item.getType().equals(Material.DRAGON_EGG) || item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE))
			out = "§d";

		if (item.getItemMeta().hasEnchants())
			out = ChatColor.AQUA + "";

		if (item.getItemMeta().hasDisplayName()) {
			out += item.getItemMeta().getDisplayName();
			return out;

		}
		return out + makeStringPretty(item.getType().name()) + ChatColor.RESET;

	}

	public BaseComponent getName(ItemStack item, String color, boolean forceColor) {
		ItemRarity rarity = ItemRarity.getRarity(item);
		String colorPrefix = color + rarity.getColor();

		if (item.getItemMeta().hasDisplayName()) {
			colorPrefix += ChatColor.ITALIC;
			TextComponent itemName = new TextComponent(colorPrefix + item.getItemMeta().getDisplayName());
			if (forceColor) {
				itemName = new TextComponent(color + ChatColor.stripColor(item.getItemMeta().getDisplayName()));
				itemName.setColor(TextComponent.fromLegacyText(color)[0].getColor());

			}
			return itemName;
		}
		if (item.getItemMeta() instanceof SkullMeta) {
			SkullMeta sm = (SkullMeta) item.getItemMeta();
			TranslatableComponent translatable = new TranslatableComponent("block.minecraft.player_head.named");
			translatable.addWith(new TextComponent(sm.getOwningPlayer().getName()));
			translatable.setColor(TextComponent.fromLegacyText(colorPrefix)[0].getColor());
			if (forceColor) {
				translatable.setColor(TextComponent.fromLegacyText(color)[0].getColor());

			}
			return translatable;
		}
		if (item.getItemMeta() instanceof BookMeta) {
			BookMeta bm = (BookMeta) item.getItemMeta();
			if (bm.hasTitle()) {
				TextComponent book = new TextComponent(colorPrefix + bm.getTitle());
				if (forceColor) {
					book.setColor(TextComponent.fromLegacyText(color)[0].getColor());
				}
				return book;
			}
		}

		TranslatableComponent tr = new TranslatableComponent(new ItemStackReflection().translateItemStack(item));
		tr.setColor(TextComponent.fromLegacyText(colorPrefix)[0].getColor());
		if (forceColor) {
			tr.setColor(TextComponent.fromLegacyText(color)[0].getColor());
		}
		return tr;

	}








}
