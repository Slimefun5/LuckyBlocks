package io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefunluckyblocks.LuckyBlockCompat;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

/**
 * A {@link Surprise} implementation.
 *
 * @author TheBusyBiscuit
 */
public final class PotatOSSurprise implements Surprise {

    @Nonnull
    @Override
    public String getName() {
        return "PotatOS";
    }

    @Override
    public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
        l.getWorld().dropItemNaturally(l, CustomItemStack.create(LuckyBlockCompat.safe(XMaterial.POTATO), "&e&lPotatOS"));
    }

    @Nonnull
    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.NEUTRAL;
    }

}

