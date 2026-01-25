package me.illusioner;

import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ShelfNumbersClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("shelf-numbers");

	@Override
	public void onInitializeClient() {
		BlockEntityRenderers.register(BlockEntityType.SHELF, context -> {
			return new ShelfNumbersRenderer(context);
		});
		GsonBuilder gsonBuilder  = new GsonBuilder();
		gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
		Gson gson = gsonBuilder.create();
		try (FileReader reader = new FileReader("config/shelf_numbers.json")) {
            gson.fromJson(reader, ShelfNumbersConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}