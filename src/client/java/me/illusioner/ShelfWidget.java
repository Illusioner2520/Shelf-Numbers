package me.illusioner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.ShelfRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShelfWidget extends AbstractWidget {
    private final BlockState shelfState;
    private final ShelfBlockEntity shelfBlockEntity;

    @SuppressWarnings("deprecation")
    public ShelfWidget(int x, int y) {
        super(x, y, 64, 64, Component.empty());
        this.shelfState = Blocks.OAK_SHELF.defaultBlockState();
        this.shelfBlockEntity = new ShelfBlockEntity(BlockPos.ZERO, shelfState);
        Reference<Item> ref1 = Items.GRASS_BLOCK.asItem().builtInRegistryHolder();
        Reference<Item> ref2 = Items.OAK_PLANKS.asItem().builtInRegistryHolder();
        // ref1.bindComponents(DataComponentMap.EMPTY);
        // ref2.bindComponents(DataComponentMap.EMPTY);
        ItemStack item1 = new ItemStack((Holder<Item>) ref1, 64);
        ItemStack item2 = new ItemStack((Holder<Item>) ref2, 1);
        this.shelfBlockEntity.setItem(0, item1);
        this.shelfBlockEntity.setItem(2, item2);
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        float centerX = getX() + getWidth() / 2.0f;
        float centerY = getY() + getHeight() / 2.0f;
        float xAngle = (float) Math.atan((centerX - mouseX) / 40.0F);
        float yAngle = (float) Math.atan((centerY - mouseY) / 40.0F);
        Quaternionf rotation = new Quaternionf().rotateZ((float) Math.toRadians(180));
        Quaternionf xRotation = new Quaternionf().rotateX(yAngle * 10.0F * (float) (Math.PI / 180.0)).rotateY(-xAngle * 10.0f * (float) (Math.PI / 180.0));
        rotation.mul(xRotation);
        BlockEntityRenderDispatcher blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        BlockEntityRenderer<ShelfBlockEntity, ShelfRenderState> shelfRenderer = blockEntityRenderDispatcher.getRenderer(this.shelfBlockEntity);
        ShelfRenderState shelfRenderState = shelfRenderer.createRenderState();
        shelfRenderState.lightCoords = 15728880;
        shelfRenderer.extractRenderState(this.shelfBlockEntity, shelfRenderState, delta, null, null);
        Vector3f translation = new Vector3f(-0.5F, -0.5F, 0.0F);
        graphics.guiRenderState.addPicturesInPictureState(new GuiBlockEntityRenderState(shelfRenderState, translation, rotation, xRotation, getX(), getY(), getX() + getWidth(), getY() + getHeight(), getWidth() * 0.8f, graphics.scissorStack.peek()));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {}
}
