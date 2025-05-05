package net.radzratz.catalystcore.recipes.pentagram;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PentagramContainer implements RecipeInput
{
    private final List<ItemEntity> entities;

    public PentagramContainer(List<ItemEntity> entities)
    {
        this.entities = entities.stream()
                .filter(e -> !e.isRemoved() && !e.getItem().isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public @NotNull ItemStack getItem(int index)
    {
        return entities.get(index).getItem();
    }

    @Override
    public int size() {
        return entities.size();
    }
}
