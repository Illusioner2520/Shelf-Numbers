package me.illusioner;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class ShelfNumbersModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("shelf_numbers.config"));
            ConfigCategory general = builder.getOrCreateCategory(Component.translatable("shelf_numbers.config.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startAlphaColorField(Component.translatable("shelf_numbers.config.text_color"), ShelfNumbersConfig.textColor)
                .setDefaultValue(0xffffffff)
                .setTooltip(Component.translatable("shelf_numbers.config.text_color.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.textColor = newValue)
                .build());
            general.addEntry(entryBuilder.startAlphaColorField(Component.translatable("shelf_numbers.config.outline_color"), ShelfNumbersConfig.outlineColor)
                .setDefaultValue(0x00000000)
                .setTooltip(Component.translatable("shelf_numbers.config.outline_color.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.outlineColor = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.show_shadow"), ShelfNumbersConfig.showShadow)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.show_shadow.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.showShadow = newValue)
                .build());
            general.addEntry(entryBuilder.startIntField(Component.translatable("shelf_numbers.config.font_size"), ShelfNumbersConfig.fontSize)
                .setDefaultValue(30)
                .setTooltip(Component.translatable("shelf_numbers.config.font_size.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.fontSize = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.display_without_items"), ShelfNumbersConfig.displayWithoutItems)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.display_without_items.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.displayWithoutItems = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.display_with_single_item"), ShelfNumbersConfig.displayWithSingleItem)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("shelf_numbers.config.display_with_single_item.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.displayWithSingleItem = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.display_with_full_stack"), ShelfNumbersConfig.displayWithFullStack)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("shelf_numbers.config.display_with_full_stack.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.displayWithFullStack = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.display_on_top"), ShelfNumbersConfig.displayOnTop)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.display_on_top.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.displayOnTop = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.bold"), ShelfNumbersConfig.bold)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.bold.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.bold = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.italics"), ShelfNumbersConfig.italics)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.italics.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.italics = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.underline"), ShelfNumbersConfig.underline)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.underline.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.underline = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.strikethrough"), ShelfNumbersConfig.strikethrough)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.strikethrough.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.strikethrough = newValue)
                .build());
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("shelf_numbers.config.obfuscated"), ShelfNumbersConfig.obfuscated)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("shelf_numbers.config.obfuscated.tooltip"))
                .setSaveConsumer(newValue -> ShelfNumbersConfig.obfuscated = newValue)
                .build());
            builder.setSavingRunnable(() -> {
                GsonBuilder gsonBuilder  = new GsonBuilder();
                gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                try (FileWriter writer = new FileWriter("config/shelf_numbers.json")) {
                    gson.toJson(new ShelfNumbersConfig(), writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return builder.build();
        };
    }
}