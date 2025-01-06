package com.example.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.text.Text;
import net.minecraft.item.ItemStack;

public class NoCraftHandler {

    // Bloquear o uso da Crafting Table
    public static void registerBlockCraftingTable() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (hitResult.getBlockPos() != null && world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.CRAFTING_TABLE) {
                player.sendMessage(Text.literal("⚠️ O uso da Crafting Table está desabilitado!"), true);
                return ActionResult.FAIL; // Impede a interação
            }
            return ActionResult.PASS;
        });
    }

    // Bloquear Crafting 2x2 no inventário
    public static void registerInventoryCrafting() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ScreenHandler screenHandler = player.currentScreenHandler;

                // Verifica se é uma CraftingScreenHandler (mesa de trabalho 3x3)
                if (screenHandler instanceof CraftingScreenHandler) {
                    player.closeHandledScreen();
                    player.sendMessage(Text.literal("⚠️ O Crafting na mesa de trabalho está desabilitado!"), true);
                }

                // Verifica se é o inventário do jogador e bloqueia o resultado do craft
                if (screenHandler instanceof PlayerScreenHandler) {
                    // Limpa o slot de resultado do craft 2x2
                    screenHandler.slots.get(0).setStack(ItemStack.EMPTY);

                    // Opcional: enviar mensagem quando tentar craftar
                    if (!screenHandler.slots.get(0).getStack().isEmpty()) {
                        player.sendMessage(Text.literal("⚠️ O Crafting no inventário está desabilitado!"), true);
                    }
                }
            }
        });
    }
}
