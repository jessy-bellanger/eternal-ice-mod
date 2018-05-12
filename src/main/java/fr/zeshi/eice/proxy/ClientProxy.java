package fr.zeshi.eice.proxy;

import fr.zeshi.eice.init.EiceBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumDyeColor;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();

        Minecraft myMinecraft = Minecraft.getMinecraft();

        myMinecraft.getBlockColors().registerBlockColorHandler(
                (state, worldIn, pos, tintIndex) -> EnumDyeColor.byMetadata(EiceBlocks.stainedEternalIce.getMetaFromState(state)).getColorValue(),
                EiceBlocks.stainedEternalIce
        );

        myMinecraft.getItemColors().registerItemColorHandler(
                (stack, tintIndex) -> EnumDyeColor.byMetadata(stack.getMetadata()).getColorValue(),
                EiceBlocks.stainedEternalIce
        );
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
