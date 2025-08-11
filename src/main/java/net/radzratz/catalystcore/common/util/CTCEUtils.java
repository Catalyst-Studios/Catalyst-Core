package net.radzratz.catalystcore.common.util;

import net.minecraft.resources.ResourceLocation;
import net.radzratz.catalystcore.CatalystCore;

public class CTCEUtils
{
    public static ResourceLocation CTCE(String path) { return ResourceLocation.fromNamespaceAndPath(CatalystCore.id, path); }
}
