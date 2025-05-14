package net.radzratz.catalystcore.items.tools.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystPickaxe extends PickaxeItem
{
    public CatalystPickaxe(Tier tier, Item.Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes)
    {
        super(tier, properties.component(DataComponents.TOOL, toolComponentData)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, attributes));
    }

    @Override
    public void postHurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker)
    {
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean isDamageable(@NotNull ItemStack stack)
    {
        return false;
    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_pickaxe.desc").withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
