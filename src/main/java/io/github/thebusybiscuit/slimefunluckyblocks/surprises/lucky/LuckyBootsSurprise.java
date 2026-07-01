package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
public final class LuckyBootsSurprise implements Surprise {

    private final ItemStack boots;

    public LuckyBootsSurprise() {
        boots = CustomItemStack.create(LuckyBlockCompat.safe(XMaterial.DIAMOND_BOOTS), "&e&lLucky Boots");
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
        boots.addUnsafeEnchantment(Enchantment.THORNS, 10);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Lucky Boots";
    }

    @Override
    public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
        l.getWorld().dropItemNaturally(l, boots.clone());
    }

    @Nonnull
    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.LUCKY;
    }

}

