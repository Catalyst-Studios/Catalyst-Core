package net.radzratz.catalystcore.common.recipes.pentagram;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PentagramContainer implements RecipeInput
{
    private final List<ItemEntity> entities;
    private List<ItemStack> itemsCache;

    public PentagramContainer(List<ItemEntity> entities)
    {
        this.entities = entities.stream()
                .filter(e -> !e.isRemoved() && !e.getItem().isEmpty())
                .collect(Collectors.toList());
    }

    public List<ItemStack> getItems()
    {
        if(itemsCache == null)
        {
            itemsCache = entities.stream()
                    .map(ItemEntity::getItem)
                    .collect(Collectors.toList());
        }
        return itemsCache;
    }

    @Override
    public @NotNull ItemStack getItem(int index)
    {
        return getItems().get(index);
    }

    @Override
    public int size()
    {
        return getItems().size();
    }

    public List<ItemEntity> getEntities()
    {
        return Collections.unmodifiableList(entities);
    }
}
