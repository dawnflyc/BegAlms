package com.dawnflyc.begalms.item;

import com.google.errorprone.annotations.Var;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldBowl extends Item {
    public GoldBowl() {
        super(new Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level leve, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(new TranslatableComponent("text.begalms.gold_bowl"));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if(player.isLocalPlayer()) return InteractionResult.SUCCESS;
        if(livingEntity instanceof Player other){
            int slot = other.getRandom().nextInt(36+1+4+(1));
            if(slot<=36){
                ItemStack itemStack1 = other.getSlot(slot).get();
                player.addItem(itemStack1.copy());
                itemStack1.setCount(0);
            }else {
                ItemStack itemStack1 = ItemStack.EMPTY;
                //副手
                if(slot == 37){
                    itemStack1 = other.getItemInHand(InteractionHand.OFF_HAND);
                }
                if(slot == 38){
                    itemStack1 =other.getItemBySlot(EquipmentSlot.HEAD);
                }
                if(slot == 39){
                    itemStack1 =other.getItemBySlot(EquipmentSlot.CHEST);
                }
                if(slot == 40){
                    itemStack1 =other.getItemBySlot(EquipmentSlot.LEGS);
                }
                if(slot == 41){
                    itemStack1 =other.getItemBySlot(EquipmentSlot.FEET);
                }
                if(!itemStack.isEmpty()){
                    player.addItem(itemStack1.copy());
                    itemStack1.setCount(0);
                }
            }
        }else if (livingEntity instanceof Mob mob){
            ResourceLocation resourcelocation = mob.getLootTable();
            LootTable loottable = mob.level.getServer().getLootTables().get(resourcelocation);
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel)player.getLevel())).withRandom(player.getRandom()).withParameter(LootContextParams.THIS_ENTITY, mob).withParameter(LootContextParams.ORIGIN, mob.position()).withParameter(LootContextParams.DAMAGE_SOURCE, DamageSource.playerAttack(player)).withOptionalParameter(LootContextParams.KILLER_ENTITY, player).withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, player);
            lootcontext$builder = lootcontext$builder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, player).withLuck(player.getLuck());
            List<ItemStack> randomItems = loottable.getRandomItems(lootcontext$builder.create(LootContextParamSets.ENTITY));
            for (ItemStack randomItem : randomItems) {
                player.addItem(randomItem);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
