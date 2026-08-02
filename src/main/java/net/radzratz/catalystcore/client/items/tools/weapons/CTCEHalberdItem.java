package net.radzratz.catalystcore.client.items.tools.weapons;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.radzratz.catalystcore.client.items.tools.CTCESwordItem;

import java.util.function.BooleanSupplier;

public class CTCEHalberdItem extends CTCESwordItem {
    public CTCEHalberdItem(Tier tier, boolean noBarDamage, Properties properties, Tool toolData, ItemAttributeModifiers attributes, BooleanSupplier module, BooleanSupplier type) {
        super(tier, noBarDamage, properties, toolData, attributes, module, type);
    }
}
