package fr.zeshi.eice.init;

import fr.zeshi.eice.blocks.BlockEiceEternalIce;
import fr.zeshi.eice.blocks.BlockEiceStainedEternalIce;
import fr.zeshi.eice.util.References;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = References.MOD_ID)
public abstract class EiceBlocks {

    private static Block[]     eiceBlocks;
    private static ItemBlock[] eiceItemBlocks;

    public static Block eternalIce;
    public static Block stainedEternalIce;

    private static void registerModel(ItemBlock itemBlock) {
        Block block = itemBlock.getBlock();

        ResourceLocation      myResourceLocation      = new ResourceLocation(References.MOD_ID, block.getUnlocalizedName().substring(5));
        ModelResourceLocation myModelResourceLocation = new ModelResourceLocation(myResourceLocation, "inventory");

        NonNullList<ItemStack> myItemStackList = NonNullList.create();
        block.getSubBlocks(itemBlock.getCreativeTab(), myItemStackList);
        for (ItemStack currentItemStack : myItemStackList) {
            ModelLoader.setCustomModelResourceLocation(itemBlock, currentItemStack.getMetadata(), myModelResourceLocation);
        }
    }

    public static Block[] getEiceBlocks() {
        return EiceBlocks.eiceBlocks;
    }

    public static ItemBlock[] getEiceItemBlocks() {
        return EiceBlocks.eiceItemBlocks;
    }

    public static void init() {
        try {
            EiceBlocks.eternalIce = new BlockEiceEternalIce();
        } catch (Exception ignored) {}
        EiceBlocks.stainedEternalIce = new BlockEiceStainedEternalIce();

        EiceBlocks.eiceBlocks = new Block[]{
                EiceBlocks.eternalIce,
                EiceBlocks.stainedEternalIce
        };

        EiceBlocks.eiceItemBlocks = new ItemBlock[] {
                new ItemBlock(EiceBlocks.eternalIce),
                new ItemMultiTexture(EiceBlocks.stainedEternalIce, EiceBlocks.stainedEternalIce, BlockEiceStainedEternalIce::getColorName)
        };
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        for (ItemBlock modItemBlock : EiceBlocks.eiceItemBlocks) {
            EiceBlocks.registerModel(modItemBlock);
        }
    }
}
