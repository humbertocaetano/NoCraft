package com.example.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.concurrent.CompletableFuture;

import static com.example.NoCraft.MOD_ID;

public class ModPoiTagProvider extends FabricTagProvider<PointOfInterestType> {

    public static final TagKey<PointOfInterestType> END_TRADER_POI_TAG = TagKey.of(
            RegistryKeys.POINT_OF_INTEREST_TYPE,
            Identifier.of(MOD_ID, "end_trader_poi")
    );

    public ModPoiTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        getOrCreateTagBuilder(END_TRADER_POI_TAG)
                .addOptional(Identifier.of(MOD_ID, "end_trader_poi"));
    }
}
