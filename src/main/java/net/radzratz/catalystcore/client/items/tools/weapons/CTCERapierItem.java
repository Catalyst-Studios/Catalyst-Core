package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.radzratz.catalystcore.client.items.tools.CTCESwordItem;

import java.util.function.BooleanSupplier;

public class CTCERapierItem extends CTCESwordItem {
    public CTCERapierItem(Tier tier, boolean noBarDamage, Properties properties, Tool toolComponentData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, noBarDamage, properties, toolComponentData, attributes, module, type);
    }
}
