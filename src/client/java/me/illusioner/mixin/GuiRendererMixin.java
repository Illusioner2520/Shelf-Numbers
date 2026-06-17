package me.illusioner.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import me.illusioner.accessor.GuiRendererAccessor;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;

@Mixin(GuiRenderer.class)
public class GuiRendererMixin implements GuiRendererAccessor {
    @Shadow private Map<Class<? extends PictureInPictureRenderState>, PictureInPictureRenderer<?>> pictureInPictureRenderers;
    public void addPictureInPictureRenderer(PictureInPictureRenderer<?> pipRenderer) {
        Builder<Class<? extends PictureInPictureRenderState>, PictureInPictureRenderer<?>> builder = ImmutableMap.builder();
        for (PictureInPictureRenderer<?> pictureInPictureRenderer : this.pictureInPictureRenderers.values()) {
            builder.put(pictureInPictureRenderer.getRenderStateClass(), pictureInPictureRenderer);
        }
        builder.put(pipRenderer.getRenderStateClass(), pipRenderer);
        this.pictureInPictureRenderers = builder.buildOrThrow();
    }
}
