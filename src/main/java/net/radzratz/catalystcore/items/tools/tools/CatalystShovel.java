package net.radzratz.catalystcore.items.tools.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.block.state.BlockState;
import net.radzratz.catalystcore.util.config.CatalystConfig;
import net.radzratz.catalystcore.util.config.CatalystItemInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystShovel extends ShovelItem implements CatalystItemInterface
{
    public CatalystShovel(Tier tier, Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes)
    {
        super(tier, properties.component(DataComponents.TOOL, toolComponentData)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, attributes)
                .component(DataComponents.UNBREAKABLE, new Unbreakable(true)));
    }

    @Override
    public boolean shouldAppear()
    {
        return CatalystConfig.CONFIG.modules.toolsModule.get();
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures)
    {
        return this.shouldAppear();
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, @NotNull BlockState state)
    {
        return true;
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

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack)
    {
        return true;
    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_shovel.desc").withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
