package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

/**
 * Resolves this addon's title text for one viewer, from {@code languages/<lang>/messages.yml}.
 *
 * @implNote The {@link ChatColor} deliberately stays at the call site rather than moving into the
 *           translated value: a translator never edits a colour code they cannot see the effect of.
 *           Where a surprise highlights one pun word, that word is a key of its own and the phrase
 *           carries a {@code %word%} placeholder, so a translation may move the punchline or choose
 *           a different one without losing the highlight.
 */
public final class Messages {

    private Messages() {}

    @Nonnull
    private static SlimefunLuckyBlocks addon() {
        return JavaPlugin.getPlugin(SlimefunLuckyBlocks.class);
    }

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english) {
        return Slimefun.getAddonMessageService().getMessage(player, addon(), key, english);
    }

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english,
                             @Nonnull UnaryOperator<String> placeholders) {
        return placeholders.apply(get(player, key, english));
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull String key, @Nonnull String english) {
        Slimefun.getAddonMessageService().sendMessage(recipient, addon(), key, english);
    }
}
