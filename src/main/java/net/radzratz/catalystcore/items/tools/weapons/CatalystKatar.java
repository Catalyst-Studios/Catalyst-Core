package net.radzratz.catalystcore.items.tools.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalystKatar extends SwordItem
{
    public CatalystKatar(Tier tier,
                         Properties properties,
                         Tool toolComponentData,
                         ItemAttributeModifiers attributes)
    {
        super(tier, properties.component(DataComponents.TOOL, toolComponentData)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, attributes));
    }

    @Override
    public void postHurtEnemy(@NotNull ItemStack stack,
                              @NotNull LivingEntity target,
                              @NotNull LivingEntity attacker)
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
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack)
    {
        return UseAnim.SPEAR;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world,
                                                           Player player,
                                                           @NotNull InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        if(hand == InteractionHand.OFF_HAND)
        {
            player.swing(hand);

            if(!world.isClientSide)
            {
                AABB attackRange = new AABB(player.getX() - 2.5, player.getY() - 1, player.getZ() - 2.5,
                        player.getX() + 2.5, player.getY() + 2, player.getZ() + 2.5);

                List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, attackRange,
                        e -> e != player && player.hasLineOfSight(e));

                for(LivingEntity target : targets)
                {
                    float damage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);

                    target.hurt(player.damageSources().playerAttack(player), damage);
                }
            }
            return InteractionResultHolder.success(stack);
        }
        return super.use(world, player, hand);
    }



    @Override
    public boolean canAttackBlock(@NotNull BlockState state,
                                  @NotNull Level level,
                                  @NotNull BlockPos pos,
                                  Player player)
    {
        return !player.isCreative();
    }

    public void appendHoverText(@NotNull ItemStack stack,
                                @NotNull TooltipContext context,
                                List<Component> tooltip,
                                @NotNull TooltipFlag flag)
    {
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_katar.desc").withStyle(ChatFormatting.DARK_BLUE));
        tooltip.add(Component.translatable("item.catalystcore.cataclystic_katar.desc2").withStyle(ChatFormatting.WHITE));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
