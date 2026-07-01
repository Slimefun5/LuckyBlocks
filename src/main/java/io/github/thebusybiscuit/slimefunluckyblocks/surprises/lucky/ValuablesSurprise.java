package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.utils.FireworkUtils;
import io.github.thebusybiscuit.slimefunluckyblocks.LuckyBlockCompat;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

/**
 * A {@link Surprise} implementation.
 *
 * @author TheBusyBiscuit
 */
public final class ValuablesSurprise implements Surprise {
	
	@Nonnull
	@Override
	public String getName() {
		return "Valuables";
	}

	@Override
	public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
	    FireworkUtils.launchRandom(p, 3);
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.EMERALD), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.DIAMOND), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.GOLD_INGOT), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.IRON_INGOT), 4));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.LAPIS_LAZULI), 8));
	}

	@Nonnull
	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}

