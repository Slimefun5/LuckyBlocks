package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

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
public final class RawFoodSurprise implements Surprise {
	
	@Nonnull
	@Override
	public String getName() {
		return "Raw Food";
	}

	@Override
	public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.BEEF), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.CHICKEN), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.PORKCHOP), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.COD), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.SALMON), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.RABBIT), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.MUTTON), 4));
	}

	@Nonnull
	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
