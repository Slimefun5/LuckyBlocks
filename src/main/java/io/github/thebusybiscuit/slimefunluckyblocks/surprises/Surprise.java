package io.github.thebusybiscuit.slimefunluckyblocks.surprises;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a surprise event that is triggered when a Lucky Block is broken.
 *
 * @author TheBusyBiscuit
 */
public interface Surprise {

    /**
     * Returns this surprise's identifier.
     *
     * @implNote It is the key an operator enables or disables the surprise under in
     *           {@code config.yml} ({@code events.<name>}, {@code custom.<name>.enabled}), never
     *           player-facing text, so it must stay in English.
     *
     * @return the name of this surprise
     */
    @Nonnull
    String getName();

    /**
     * Returns the luck level category for this surprise.
     *
     * @return the {@link LuckLevel} of this surprise
     */
    @Nonnull
    LuckLevel getLuckLevel();

    /**
     * Activates this surprise at the given location for the given player.
     *
     * @param random the {@link Random} instance to use
     * @param p      the {@link Player} who triggered the surprise
     * @param l      the {@link Location} where the surprise occurs
     */
    void activate(@Nonnull Random random, @Nonnull Player p, @Nonnull Location l);

}
