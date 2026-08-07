package net.radzratz.catalystcore.common.datagen.langs;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.radzratz.catalystcore.common.datagen.CTCEDataProvider;
import net.radzratz.catalystcore.common.datagen.langs.en.CTCELocalizationEN_US;

import java.util.ArrayList;
import java.util.List;

public class CTCELangProvider {
    private static final List<LanguageProvider> langs = new ArrayList<>();

    public static void registerLangs(PackOutput packOutput) {
        if (!langs.isEmpty()) return;

        langs.add(new CTCELocalizationEN_US(packOutput));
    }

    public static void addLangs(CTCEDataProvider provider, boolean includeClient) {
        for (LanguageProvider lang : langs) {
            provider.addSubProvider(includeClient, lang);
        }
    }
}
