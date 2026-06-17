package me.illusioner;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class ShelfNumbersFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ShelfNumbersMain.initialize();
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            ShelfNumbersMain.loaded();
        });
	}
}