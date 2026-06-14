package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
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
public final class WalshrusSurprise implements Surprise {

    private final ItemStack sword;

    public WalshrusSurprise() {
        sword = CustomItemStack.create(LuckyBlockCompat.safe(XMaterial.GOLDEN_SWORD), "&e&lLucky Sword");
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        sword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
        sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Walshrus";
    }

    @Override
    public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
        Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
        LuckyBlockCompat.setMaxHealth(zombie, 40D);

        zombie.getEquipment().setHelmet(new ItemStack(LuckyBlockCompat.safe(XMaterial.PLAYER_HEAD)));
        zombie.getEquipment().setHelmetDropChance(0F);

        LuckyBlockCompat.setMainHandItem(zombie.getEquipment(), sword.clone());
        zombie.setCanPickupItems(false);
        zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&4Walshrus"));
        zombie.setCustomNameVisible(true);
    }

    @Nonnull
    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }

}

