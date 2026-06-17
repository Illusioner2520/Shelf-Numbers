package me.illusioner.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.illusioner.accessor.GameRendererAccessor;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin implements GameRendererAccessor {
    @Shadow private GuiRenderer guiRenderer;

    public GuiRenderer getGuiRenderer() {
        return this.guiRenderer;
    }
}
