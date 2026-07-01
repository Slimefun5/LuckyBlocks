package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefunluckyblocks.LuckyBlockCompat;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

/**
 * A {@link Surprise} implementation.
 *
 * @author TheBusyBiscuit
 */
public final class DyeSurprise implements Surprise {
	
	@Nonnull
	@Override
	public String getName() {
		return "Dyes";
	}

	@Override
	public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.CYAN_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.LIGHT_BLUE_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.LIME_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.MAGENTA_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.ORANGE_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.PINK_DYE)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.PURPLE_DYE)));
		LuckyBlockCompat.sendTitle(p, p.getName(), ChatColor.translateAlternateColorCodes('&', " has almost &b&odyed"), 10, 20, 10);
	}

	@Nonnull
	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
