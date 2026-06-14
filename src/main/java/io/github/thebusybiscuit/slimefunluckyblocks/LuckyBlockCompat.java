package io.github.thebusybiscuit.slimefunluckyblocks;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Runtime compatibility bridge that keeps SlimefunLuckyBlocks loadable and fully
 * functional on legacy server versions (e.g. 1.8.8) while still compiling against,
 * and running unchanged on, modern Bukkit APIs.
 *
 * <p>Modern APIs that are absent on 1.8 ({@code BlockData}, the entity attribute
 * system, the off-hand equipment methods, {@code Enchantment#getByKey}) are invoked
 * reflectively with a legacy fallback so they never throw at load or enable time.
 */
public final class LuckyBlockCompat {

    private static final boolean BLOCK_DATA_SUPPORTED = methodExists(Material.class, "createBlockData");
    private static final boolean ATTRIBUTES_SUPPORTED = classExists("org.bukkit.attribute.Attribute");
    private static final boolean MAIN_HAND_SUPPORTED = methodExists(EntityEquipment.class, "setItemInMainHand", ItemStack.class);

    private LuckyBlockCompat() {}

    /**
     * Resolves the given {@link XMaterial} for the running server version, falling
     * back to {@link Material#STONE} when no legacy equivalent exists so callers
     * never receive {@code null}.
     */
    @Nonnull
    public static Material safe(@Nonnull XMaterial material) {
        Material resolved = material.parseMaterial();
        return resolved != null ? resolved : Material.STONE;
    }

    /**
     * Spawns a falling block of the given {@link Material}. Uses {@code BlockData}
     * on 1.13+ and the legacy {@code spawnFallingBlock(Location, Material, byte)}
     * signature on older versions.
     */
    public static void spawnFallingBlock(@Nonnull World world, @Nonnull Location location, @Nonnull Material material) {
        if (BLOCK_DATA_SUPPORTED) {
            try {
                Object blockData = Material.class.getMethod("createBlockData").invoke(material);
                Class<?> blockDataClass = Class.forName("org.bukkit.block.data.BlockData");
                World.class.getMethod("spawnFallingBlock", Location.class, blockDataClass).invoke(world, location, blockData);
                return;
            } catch (ReflectiveOperationException ignored) {
                // Fall through to the legacy signature.
            }
        }

        try {
            World.class.getMethod("spawnFallingBlock", Location.class, Material.class, byte.class).invoke(world, location, material, (byte) 0);
        } catch (ReflectiveOperationException ignored) {
            // Nothing we can do on this version; degrade silently.
        }
    }

    /**
     * Places a player-head block at the given {@link Block}, randomly rotated when
     * the running version supports rotatable block data. On 1.8 a plain skull block
     * is placed instead.
     */
    public static void placeRotatableHead(@Nonnull Block block, @Nonnull BlockFace[] rotations) {
        if (BLOCK_DATA_SUPPORTED) {
            Material head = safe(XMaterial.PLAYER_HEAD);

            try {
                Object blockData = Material.class.getMethod("createBlockData").invoke(head);
                Class<?> rotatableClass = Class.forName("org.bukkit.block.data.Rotatable");

                if (rotatableClass.isInstance(blockData)) {
                    BlockFace rotation = rotations[ThreadLocalRandom.current().nextInt(rotations.length)];
                    rotatableClass.getMethod("setRotation", BlockFace.class).invoke(blockData, rotation);
                }

                Class<?> blockDataClass = Class.forName("org.bukkit.block.data.BlockData");
                Block.class.getMethod("setBlockData", blockDataClass).invoke(block, blockData);
                return;
            } catch (ReflectiveOperationException ignored) {
                // Fall through to the legacy placement.
            }
        }

        block.setType(safe(XMaterial.PLAYER_HEAD));
    }

    /**
     * Sets the maximum health of an {@link Entity}. Uses the attribute system on
     * 1.9+ and the deprecated {@code setMaxHealth} method on 1.8.
     */
    public static void setMaxHealth(@Nonnull Entity entity, double health) {
        if (ATTRIBUTES_SUPPORTED) {
            try {
                Class<?> attributeClass = Class.forName("org.bukkit.attribute.Attribute");
                Object maxHealth = attributeClass.getMethod("valueOf", String.class).invoke(null, "GENERIC_MAX_HEALTH");
                Object instance = entity.getClass().getMethod("getAttribute", attributeClass).invoke(entity, maxHealth);

                if (instance != null) {
                    instance.getClass().getMethod("setBaseValue", double.class).invoke(instance, health);
                }
            } catch (ReflectiveOperationException ignored) {
                setMaxHealthLegacy(entity, health);
            }
        } else {
            setMaxHealthLegacy(entity, health);
        }

        try {
            entity.getClass().getMethod("setHealth", double.class).invoke(entity, health);
        } catch (ReflectiveOperationException ignored) {
            // setHealth exists on every supported version; ignore defensively.
        }
    }

    private static void setMaxHealthLegacy(@Nonnull Entity entity, double health) {
        try {
            entity.getClass().getMethod("setMaxHealth", double.class).invoke(entity, health);
        } catch (ReflectiveOperationException ignored) {
            // Degrade silently if unavailable.
        }
    }

    /**
     * Equips the given {@link ItemStack} in the entity's main hand with no drop
     * chance. Uses the dual-wield methods on 1.9+ and the single-hand methods on 1.8.
     */
    public static void setMainHandItem(@Nonnull EntityEquipment equipment, @Nonnull ItemStack item) {
        if (MAIN_HAND_SUPPORTED) {
            try {
                EntityEquipment.class.getMethod("setItemInMainHand", ItemStack.class).invoke(equipment, item);
                EntityEquipment.class.getMethod("setItemInMainHandDropChance", float.class).invoke(equipment, 0F);
                return;
            } catch (ReflectiveOperationException ignored) {
                // Fall through to the legacy methods.
            }
        }

        try {
            EntityEquipment.class.getMethod("setItemInHand", ItemStack.class).invoke(equipment, item);
            EntityEquipment.class.getMethod("setItemInHandDropChance", float.class).invoke(equipment, 0F);
        } catch (ReflectiveOperationException ignored) {
            // Degrade silently if unavailable.
        }
    }

    /**
     * Resolves an {@link Enchantment} by its (lowercase) Minecraft key in a
     * version-safe way. Uses {@code getByKey} on 1.13+ and the deprecated
     * {@code getByName} lookup on older versions.
     */
    @Nullable
    public static Enchantment getEnchantment(@Nonnull String key) {
        if (classExists("org.bukkit.NamespacedKey")) {
            try {
                Class<?> keyClass = Class.forName("org.bukkit.NamespacedKey");
                Object namespacedKey = keyClass.getMethod("minecraft", String.class).invoke(null, key);
                return (Enchantment) Enchantment.class.getMethod("getByKey", keyClass).invoke(null, namespacedKey);
            } catch (ReflectiveOperationException ignored) {
                // Fall through to the legacy lookup.
            }
        }

        return Enchantment.getByName(key.toUpperCase(java.util.Locale.ROOT));
    }

    /**
     * Sends a title to a {@link Player} when the running version supports the
     * timed {@code sendTitle} signature (1.11+). Older versions silently ignore
     * the call, since the API does not exist there.
     */
    public static void sendTitle(@Nonnull Player player, @Nonnull String title, @Nonnull String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            Player.class.getMethod("sendTitle", String.class, String.class, int.class, int.class, int.class)
                .invoke(player, title, subtitle, fadeIn, stay, fadeOut);
        } catch (ReflectiveOperationException ignored) {
            // Titles are unavailable on this version; degrade silently.
        }
    }

    private static boolean classExists(@Nonnull String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean methodExists(@Nonnull Class<?> owner, @Nonnull String name, @Nonnull Class<?>... params) {
        try {
            owner.getMethod(name, params);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
