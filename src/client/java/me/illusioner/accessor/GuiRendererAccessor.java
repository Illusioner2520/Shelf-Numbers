package me.illusioner.accessor;

import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;

public interface GuiRendererAccessor {
    void addPictureInPictureRenderer(PictureInPictureRenderer<?> pipRenderer);
}
