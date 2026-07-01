package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

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
public final class GrootSurprise implements Surprise {
	
	@Nonnull
	@Override
	public String getName() {
		return "I am Groot";
	}

	@Override
	public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
		l.getBlock().getRelative(BlockFace.DOWN).setType(LuckyBlockCompat.safe(XMaterial.PODZOL));
		l.getBlock().setType(LuckyBlockCompat.safe(XMaterial.OAK_SAPLING));
		LuckyBlockCompat.sendTitle(p, "", ChatColor.translateAlternateColorCodes('&', "&6I am Groot!"), 10, 20, 10);
	}

	@Nonnull
	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.NEUTRAL;
	}

}
