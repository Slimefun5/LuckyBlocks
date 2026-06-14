package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

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
public final class FishSurprise implements Surprise {
	
	@Nonnull
	@Override
	public String getName() {
		return "Fish";
	}

	@Override
	public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
	    FireworkUtils.launchRandom(p, 3);
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.COD)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.SALMON)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.PUFFERFISH)));
		l.getWorld().dropItemNaturally(l, new ItemStack(LuckyBlockCompat.safe(XMaterial.TROPICAL_FISH)));
	}

	@Nonnull
	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}

