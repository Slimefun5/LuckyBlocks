package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;
import org.bukkit.potion.PotionEffect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun5.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun5.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiText;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiTopic;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.CustomItemSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.CakeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.CookedFoodSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.DiamondBlockPillarSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.DiamondBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.EmeraldBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.GoldenAppleSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.IronBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyAxeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyBootsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyChestplateSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyHelmetSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyLeggingsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyPickaxeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyPotionsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckySwordSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.TamedCatsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.TamedDogsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.UnluckyPotionsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.ValuablesSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.XPRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.ChickenRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.CookieSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.DyeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.FishSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.GrootSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.HaySurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.JebSheepSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.JerrySlimeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.PotatOSSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.RainbowSheepSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.RawFoodSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.VillagersSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.WanderingTraderSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora.IronGolemsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora.ReapersSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.AnvilRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.BryanZombieSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ChargedCreeperSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.CobwebSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.EnclosedWaterSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ExplosionSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FakeDiamondBlock;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FlyingCreeperSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FlyingTNTSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.GiantSlimeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.HighJumpSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.TNTRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.VoidHoleSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.WalshrusSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.WitchSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ZombiePigmenSurprise;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

/**
 * The main plugin class for SlimefunLuckyBlocks.
 * This addon adds Lucky Blocks to Slimefun.
 *
 * @author TheBusyBiscuit
 */
public class SlimefunLuckyBlocks extends JavaPlugin implements SlimefunAddon {

    private static final String TEXTURE = "b3b710b08b523bba7efba07c629ba0895ad61126d26c86beb3845603a97426c";

    private Config cfg;
    private final List<Surprise> surprises = new LinkedList<>();
    private final BlockFace[] blockfaces = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    @Override
    public void onEnable() {
        new Metrics(this, 31438);

        cfg = new Config(this);

        // Setting up bStats
        
        ItemGroup itemGroup = new ItemGroup(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey("slimefunluckyblocks", "lucky_blocks"), new SlimefunItemStack("_LUCKYBLOCKS_GROUP_ICON", TEXTURE, "&rLucky Blocks").item()).setTheme("misc");

        SlimefunItemStack luckyBlock = new SlimefunItemStack("LUCKY_BLOCK", TEXTURE, "&fLucky Block", "&7Luck: &f0");
        SlimefunItemStack veryLuckyBlock = new SlimefunItemStack("LUCKY_BLOCK_LUCKY", TEXTURE, "&fVery lucky Block", "&7Luck: &a+80");
        SlimefunItemStack veryUnluckyBlock = new SlimefunItemStack("LUCKY_BLOCK_UNLUCKY", TEXTURE, "&fVery unlucky Block", "&7Luck: &c-80");
        SlimefunItemStack pandorasBox = new SlimefunItemStack("PANDORAS_BOX", "86c7dde512871bd607b77e6635ad39f44f2d5b4729e60273f1b14fba9a86a", "&5Pandora\"s Box", "&7Luck: &c&oERROR");

        // @formatter:off
        new LuckyBlock(itemGroup, luckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), new ItemStack(LuckyBlockCompat.safe(XMaterial.DISPENSER)), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item() }).register(this, surprises, s -> s.getLuckLevel() != LuckLevel.PANDORA);

        new LuckyBlock(itemGroup, veryLuckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { null, SlimefunItems.GOLD_12K.item(), null, SlimefunItems.GOLD_12K.item(), luckyBlock.item(), SlimefunItems.GOLD_12K.item(), null, SlimefunItems.GOLD_12K.item(), null }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.LUCKY);

        new LuckyBlock(itemGroup, veryUnluckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { null, new ItemStack(LuckyBlockCompat.safe(XMaterial.SPIDER_EYE)), null, new ItemStack(LuckyBlockCompat.safe(XMaterial.SPIDER_EYE)), luckyBlock.item(), new ItemStack(LuckyBlockCompat.safe(XMaterial.SPIDER_EYE)), null, new ItemStack(LuckyBlockCompat.safe(XMaterial.SPIDER_EYE)), null }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.UNLUCKY);

        new LuckyBlock(itemGroup, pandorasBox, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { new ItemStack(LuckyBlockCompat.safe(XMaterial.OAK_PLANKS)), new ItemStack(LuckyBlockCompat.safe(XMaterial.LAPIS_BLOCK)), new ItemStack(LuckyBlockCompat.safe(XMaterial.OAK_PLANKS)), new ItemStack(LuckyBlockCompat.safe(XMaterial.LAPIS_BLOCK)), luckyBlock.item(), new ItemStack(LuckyBlockCompat.safe(XMaterial.LAPIS_BLOCK)), new ItemStack(LuckyBlockCompat.safe(XMaterial.OAK_PLANKS)), new ItemStack(LuckyBlockCompat.safe(XMaterial.LAPIS_BLOCK)), new ItemStack(LuckyBlockCompat.safe(XMaterial.OAK_PLANKS)) }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.PANDORA);
        // @formatter:on

        new WorldGenerator(this);

        registerDefaultSurprises();
        registerCustomSurprises();

        getLogger().log(Level.INFO, "Loaded {0} different Surprises!", surprises.size());

        Slimefun.getItemTranslationService().registerTranslations(this);
        registerWiki();
    }

    private void registerWiki() {
        WikiText wiki = Slimefun.getWikiText();

        for (Map.Entry<ItemGroup, List<String>> entry : groupOwnItemsByItemGroup().entrySet()) {
            ItemGroup group = entry.getKey();
            String topicId = "addon_luckyblocks_" + group.getKey().getKey();

            wiki.registerTopic(new WikiTopic(topicId, "Lucky Blocks", XMaterial.SPONGE, "&7Place or break it and pray to RNGesus"));
            wiki.setMechanic(topicId, getLuckyBlocksMechanic());
            wiki.setTopicItems(topicId, entry.getValue());
        }

        registerItemPages(wiki);
    }

    /**
     * Buckets every {@link SlimefunItem} owned by this addon under its
     * {@link ItemGroup}. The list of ids is built dynamically from the registry
     * so it never has to be maintained by hand.
     *
     * @return a {@link LinkedHashMap} of {@link ItemGroup} to its owned item ids
     */
    @Nonnull
    private Map<ItemGroup, List<String>> groupOwnItemsByItemGroup() {
        Map<ItemGroup, List<String>> buckets = new LinkedHashMap<>();

        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            try {
                if (item.getAddon() == this) {
                    buckets.computeIfAbsent(item.getItemGroup(), key -> new ArrayList<>()).add(item.getId());
                }
            } catch (Exception | LinkageError ignored) {
                // Skip items that fail to resolve their addon or group.
            }
        }

        return buckets;
    }

    @Nonnull
    private List<String> getLuckyBlocksMechanic() {
        return Arrays.asList(
            "&7Risk it for a surprise.", "",
            "&7Craft a &fLucky Block&7, drop it into the world", "&7and break it - one of dozens of random",
            "&7&oSurprises&7 fires the moment it shatters.", "",
            "&aLucky&7 rolls shower you with diamonds, golden", "&7apples, enchanted gear, tamed pets and XP.",
            "&cUnlucky&7 rolls answer with TNT rain, charged", "&7creepers, anvils, cobwebs and the dreaded void hole.", "",
            "&7Higher tiers tilt the odds: the &aVery lucky Block&7", "&7(&a+80&7) only rolls good outcomes, the &cVery unlucky",
            "&7Block&7 (&c-80&7) only bad ones, while &5Pandora\"s Box&7", "&7unleashes the rarest, most chaotic events of all.", "",
            "&7Surprises are toggled in the config under &fevents&7,", "&7and you can add your own under &fcustom&7.", "",
            "&7Click an item below for its recipe.");
    }

    private void registerItemPages(@Nonnull WikiText wiki) {
        wiki.set("_LUCKYBLOCKS_GROUP_ICON", Arrays.asList(
            "&7The &fLucky Blocks&7 category.", "",
            "&7Every block in here gambles a &oSurprise&7", "&7against you when broken - reward or ruin."));

        wiki.set("LUCKY_BLOCK", Arrays.asList(
            "&7A &fLucky Block&7 with a luck level of &f0&7.", "",
            "&7Break it for a fair coin-flip between a", "&agenerous reward&7 and a &cnasty trick&7.", "",
            "&7Crafted from &68 Gold (12-Karat)&7 around a", "&7Dispenser, it is also the core of every other tier."));

        wiki.set("LUCKY_BLOCK_LUCKY", Arrays.asList(
            "&7A &aVery lucky Block&7 (&a+80&7 luck).", "",
            "&7Only &arewarding Surprises&7 can roll here:", "&7diamonds, emeralds, golden apples,",
            "&7enchanted gear, tamed pets and XP rain.", "",
            "&7Craft it by surrounding a &fLucky Block&7 with", "&6Gold (12-Karat)&7 on its sides."));

        wiki.set("LUCKY_BLOCK_UNLUCKY", Arrays.asList(
            "&7A &cVery unlucky Block&7 (&c-80&7 luck).", "",
            "&7Only &cpunishing Surprises&7 can roll here:", "&7TNT rain, charged creepers, anvils,",
            "&7explosions and the bottomless void hole.", "",
            "&7Craft it by surrounding a &fLucky Block&7 with", "&aSpider Eyes&7 - place it far from anything you value."));

        wiki.set("PANDORAS_BOX", Arrays.asList(
            "&5Pandora\"s Box&7 - luck level &c&oERROR&7.", "",
            "&7Reserved for the rarest, most chaotic events,", "&7like swarms of Reapers and rampaging Iron Golems.", "",
            "&7Craft it from &fLapis&7 and &6Planks&7 around a", "&fLucky Block&7. You have been warned."));
    }

    private void registerDefaultSurprises() {
        // Lucky Surprises
        registerSurprise(new CookedFoodSurprise());
        registerSurprise(new GoldenAppleSurprise());
        registerSurprise(new DiamondBlockSurprise());
        registerSurprise(new DiamondBlockPillarSurprise());
        registerSurprise(new EmeraldBlockSurprise());
        registerSurprise(new IronBlockSurprise());
        registerSurprise(new TamedDogsSurprise());
        registerSurprise(new TamedCatsSurprise());
        registerSurprise(new ValuablesSurprise());
        registerSurprise(new LuckySwordSurprise());
        registerSurprise(new LuckyPickaxeSurprise());
        registerSurprise(new LuckyAxeSurprise());
        registerSurprise(new XPRainSurprise());
        registerSurprise(new LuckyHelmetSurprise());
        registerSurprise(new LuckyChestplateSurprise());
        registerSurprise(new LuckyLeggingsSurprise());
        registerSurprise(new LuckyBootsSurprise());
        registerSurprise(new LuckyPotionsSurprise());
        registerSurprise(new UnluckyPotionsSurprise());
        registerSurprise(new CakeSurprise());

        // Neutral Surprises
        registerSurprise(new GrootSurprise());
        registerSurprise(new RawFoodSurprise());
        registerSurprise(new FishSurprise());
        registerSurprise(new WanderingTraderSurprise());
        registerSurprise(new RainbowSheepSurprise());
        registerSurprise(new ChickenRainSurprise());
        registerSurprise(new DyeSurprise());
        registerSurprise(new HaySurprise());
        registerSurprise(new CookieSurprise());
        registerSurprise(new JebSheepSurprise());
        registerSurprise(new VillagersSurprise());
        registerSurprise(new PotatOSSurprise());
        registerSurprise(new JerrySlimeSurprise());

        // Unlucky Surprises
        registerSurprise(new ChargedCreeperSurprise());
        registerSurprise(new WitchSurprise());
        registerSurprise(new ExplosionSurprise());
        registerSurprise(new VoidHoleSurprise());
        registerSurprise(new AnvilRainSurprise());
        registerSurprise(new EnclosedWaterSurprise());
        registerSurprise(new TNTRainSurprise());
        registerSurprise(new FlyingCreeperSurprise());
        registerSurprise(new FlyingTNTSurprise());
        registerSurprise(new FakeDiamondBlock());
        registerSurprise(new BryanZombieSurprise());
        registerSurprise(new WalshrusSurprise());
        registerSurprise(new HighJumpSurprise());
        registerSurprise(new CobwebSurprise());
        registerSurprise(new GiantSlimeSurprise());
        registerSurprise(new ZombiePigmenSurprise());

        // Pandora Box Surprises
        registerSurprise(new ReapersSurprise());
        registerSurprise(new IronGolemsSurprise());
    }

    private void registerCustomSurprises() {
        // CustomItem Surprises
        if (cfg.getValue("custom") != null && !cfg.getKeys("custom").isEmpty()) {
            for (String name : cfg.getKeys("custom")) {
                LuckLevel luckLevel = LuckLevel.NEUTRAL;
                List<ItemStack> items = new ArrayList<>();
                List<String> commands = new ArrayList<>();

                if (cfg.getString("custom." + name + ".lucklevel") != null) {
                    try {
                        luckLevel = LuckLevel.valueOf(cfg.getString("custom." + name + ".lucklevel").toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        getLogger().log(Level.WARNING, "Couldn\"t load lucklevel of CustomItem Surprise \"{0}\", now using NEUTRAL (default)", name);
                        getLogger().log(Level.WARNING, "Valid lucklevel types: LUCKY, NEUTRAL, UNLUCKY, PANDORA");
                    }
                }

                if (cfg.getValue("custom." + name + ".commands") != null && !cfg.getStringList("custom." + name + ".commands").isEmpty()) {
                    commands.addAll(cfg.getStringList("custom." + name + ".commands"));
                }

                if (cfg.getValue("custom." + name + ".items") != null && !cfg.getKeys("custom." + name + ".items").isEmpty()) {
                    for (String itemID : cfg.getKeys("custom." + name + ".items")) {
                        ItemStack item = null;
                        String itemPath = "custom." + name + ".items." + itemID;

                        if (cfg.getString(itemPath + ".slimefun_item") != null) {
                            String id = cfg.getString(itemPath + ".slimefun_item").toUpperCase(Locale.ROOT);
                            SlimefunItem sfItem = SlimefunItem.getById(id);

                            if (sfItem != null) {
                                item = sfItem.getItem();

                                if (cfg.getInt(itemPath + ".amount") > 1) {
                                    item.setAmount(cfg.getInt(itemPath + ".amount"));
                                }
                            } else {
                                getLogger().log(Level.WARNING, "Could not load SlimefunItem \"{0}\" to custom surprise \"{1}\"", new Object[] { id, name });
                            }
                        } else if (cfg.getString(itemPath + ".type") != null && Material.getMaterial(cfg.getString(itemPath + ".type")) != null) {
                            item = new ItemStack(Material.getMaterial(cfg.getString(itemPath + ".type")));
                            ItemMeta itemMeta = item.getItemMeta();

                            if (cfg.getInt(itemPath + ".amount") > 1) {
                                item.setAmount(cfg.getInt(itemPath + ".amount"));
                            }

                            if (cfg.getString(itemPath + ".displayname") != null) {
                                itemMeta.setDisplayName(ChatColors.color(cfg.getString(itemPath + ".displayname")));
                            }

                            if (!cfg.getStringList(itemPath + ".lore").isEmpty()) {
                                List<String> lore = new ArrayList<>();
                                for (String l : cfg.getStringList(itemPath + ".lore")) {
                                    lore.add(ChatColors.color(l));
                                }
                                itemMeta.setLore(lore);
                            }

                            if (!cfg.getStringList(itemPath + ".enchants").isEmpty()) {
                                for (String ench : cfg.getStringList(itemPath + ".enchants")) {
                                    String[] split = ench.split(":");
                                    String enchName = split[0];
                                    Enchantment enchantment = LuckyBlockCompat.getEnchantment(enchName.toLowerCase(Locale.ROOT));
                                    int level = 1;

                                    if (enchantment != null) {
                                        if (split.length == 2) {
                                            if (!CommonPatterns.NUMERIC.matcher(split[1]).matches()) {
                                                getLogger().log(Level.WARNING, "Could not set \"{0}\" enchant with level \"{1}\" for custom surprise \"{2}\"", new Object[] { enchName, split[1], name });
                                                continue;
                                            }

                                            level = Integer.parseInt(split[1]);
                                        }

                                        itemMeta.addEnchant(enchantment, level, true);
                                    } else {
                                        getLogger().log(Level.WARNING, "Could not set \"{0}\" enchant for custom surprise \"{1}\"", new Object[] { enchName, name });
                                    }
                                }
                            }

                            item.setItemMeta(itemMeta);
                        }

                        if (item != null) {
                            items.add(item);
                        }
                    }
                }
                if (!items.isEmpty() || !commands.isEmpty()) {
                    registerSurprise(new CustomItemSurprise(name, items, commands, luckLevel));
                }
            }
        }
    }

    @Nonnull
    public static ItemStack createPotion(@Nonnull Color color, @Nonnull PotionEffect effect, boolean lucky) {
        ItemStack potion = new ItemStack(LuckyBlockCompat.safe(lucky ? XMaterial.POTION : XMaterial.SPLASH_POTION));
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setDisplayName(ChatColors.color((lucky ? "&6Lucky" : "&cUnlucky") + " potion"));
        pm.setColor(color);
        pm.addCustomEffect(effect, false);
        potion.setItemMeta(pm);
        return potion;
    }

    public void registerSurprise(@Nonnull Surprise surprise) {
        if (surprise instanceof CustomItemSurprise) {
            if (cfg.getBoolean("custom." + surprise.getName() + ".enabled")) {
                surprises.add(surprise);
            }

            return;
        }

        if (cfg.contains("events." + surprise.getName())) {
            if (cfg.getBoolean("events." + surprise.getName())) {
                surprises.add(surprise);
            }
        } else {
            cfg.setValue("events." + surprise.getName(), true);
            cfg.save();
            surprises.add(surprise);
        }
    }

    public void spawnLuckyBlock(@Nonnull Block b) {
        LuckyBlockCompat.placeRotatableHead(b, blockfaces);
        BlockStorage.store(b, "LUCKY_BLOCK");

        if (getCfg().getBoolean("debug")) {
            getLogger().log(Level.INFO, "spawned lucky block at {0} {1} {2} - {3}", new Object[] { b.getX(), b.getY(), b.getZ(), b.getWorld().getName() });
        }
    }

    @Nonnull
    public Config getCfg() {
        return cfg;
    }

    @Nonnull


    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull


    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Slimefun5/LuckyBlocks/issues";
    }

}
