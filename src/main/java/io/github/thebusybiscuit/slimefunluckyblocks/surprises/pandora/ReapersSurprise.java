package io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
public final class ReapersSurprise implements Surprise {

    private final ItemStack hoe;

    public ReapersSurprise() {
        hoe = CustomItemStack.create(LuckyBlockCompat.safe(XMaterial.GOLDEN_HOE), "&e&lLucky Hoe");
        hoe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        hoe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
        hoe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
        hoe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Reapers";
    }

    @Override
    public void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l) {
        for (int i = 0; i < 4; i++) {
            Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
            LuckyBlockCompat.setMaxHealth(zombie, 120D);

            zombie.getEquipment().setHelmet(LuckyBlockCompat.stack(XMaterial.PLAYER_HEAD));
            zombie.getEquipment().setHelmetDropChance(0F);

            LuckyBlockCompat.setMainHandItem(zombie.getEquipment(), hoe.clone());
            zombie.setCanPickupItems(false);

            zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 1));
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
        }
    }

    @Nonnull
    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.PANDORA;
    }

}

