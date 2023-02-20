package com.dawnflyc.begalms.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IronBowl extends Item {
    public IronBowl() {
        super(new Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level leve, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(new TranslatableComponent("text.begalms.iron_bowl"));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if(player.isLocalPlayer()) return InteractionResult.SUCCESS;
        Iterable<ItemStack> handSlots = livingEntity.getHandSlots();
        for (ItemStack handSlot : handSlots) {
            player.addItem(handSlot.copy());
            handSlot.setCount(0);
        }
        return InteractionResult.SUCCESS;
    }
}
