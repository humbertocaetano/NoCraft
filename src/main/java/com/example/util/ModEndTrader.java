package com.example.util;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static com.example.NoCraft.MOD_ID;
import static com.example.NoCraft.LOGGER;

public class ModEndTrader {
    public static final String POI_ID = "end_trader_poi";
    public static final String PROFESSION_ID = "end_trader";

    public static final Identifier END_TRADER_POI_ID = Identifier.of(MOD_ID, POI_ID);
    public static final Identifier END_TRADER_ID = Identifier.of(MOD_ID, PROFESSION_ID);
    public static final RegistryKey<PointOfInterestType> END_TRADER_POI_KEY = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, END_TRADER_POI_ID);

    public static PointOfInterestType END_TRADER_POI;
    public static VillagerProfession END_TRADER;

    public static void registerEndTrader() {
        try {
            LOGGER.info("Iniciando registro do EndTrader");

            // Registra o POI
            END_TRADER_POI = Registry.register(
                    Registries.POINT_OF_INTEREST_TYPE,
                    END_TRADER_POI_ID,
                    new PointOfInterestType(
                            ImmutableSet.copyOf(Blocks.CRAFTING_TABLE.getStateManager().getStates()),
                            1, // tickets
                            1  // range
                    )
            );

            LOGGER.info("POI registrado: " + END_TRADER_POI);

            // Registra a profissão
            END_TRADER = Registry.register(
                    Registries.VILLAGER_PROFESSION,
                    END_TRADER_ID,
                    new VillagerProfession(
                            END_TRADER_ID.toString(),
                            entry -> entry.matchesKey(END_TRADER_POI_KEY),
                            entry -> entry.matchesKey(END_TRADER_POI_KEY),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH
                    )
            );

            LOGGER.info("Profissão registrada: " + END_TRADER);

            // Registra as trocas
            TradeOfferHelper.registerVillagerOffers(END_TRADER, 1, factories -> {
                factories.add((entity, random) -> new TradeOffer(
                        new TradedItem(Items.NETHER_STAR),
                        new ItemStack(Items.CRAFTING_TABLE),
                        1,
                        30,
                        0.2f
                ));
            });

            LOGGER.info("Trades registrados com sucesso");

        } catch (Exception e) {
            LOGGER.error("Erro ao registrar EndTrader: ", e);
        }
    }
}
