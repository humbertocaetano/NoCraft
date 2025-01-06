package com.example.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ModCustomCleric {
    public static void registerCustomCleric() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.BLAZE_ROD, 1),
                            Optional.of(new TradedItem(Items.ENDER_PEARL, 1)),
                            new ItemStack(Items.ENDER_EYE,1),
                            6, 5, 0));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.NETHER_STAR, 1),
                            Optional.of(new TradedItem(Items.DRAGON_HEAD, 1)),
                            new ItemStack(Items.CRAFTER,1),
                            6, 5, 0));

                });

    }
}
