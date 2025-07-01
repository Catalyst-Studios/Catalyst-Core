package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import net.radzratz.catalystcore.common.util.config.CTCEConfig;
import net.radzratz.catalystcore.common.util.config.ICTCEItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ICTCEHalberd extends SwordItem implements ICTCEItem
{
    public ICTCEHalberd(Tier tier, Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes)
    {
        super(tier, properties.component(DataComponents.TOOL, toolComponentData)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, attributes)
                .component(DataComponents.UNBREAKABLE, new Unbreakable(true)));
    }

    @Override
    public boolean shouldAppear()
    {
        return CTCEConfig.CONFIG.modules.weaponsModule.get();
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures)
    {
        return this.shouldAppear();
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
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_halberd.desc").withStyle(ChatFormatting.DARK_BLUE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
