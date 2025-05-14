package net.radzratz.catalystcore.items.tools.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystHalberd extends SwordItem
{
    public CatalystHalberd(Tier tier, Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes)
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
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_halberd.desc").withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
