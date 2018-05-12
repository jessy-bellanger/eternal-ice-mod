package fr.zeshi.eice.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class BlockEiceStainedEternalIce extends BlockEiceEternalIce {

    private static final IProperty<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    public BlockEiceStainedEternalIce() {
        super("stained_eternal_ice");

        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockEiceStainedEternalIce.COLOR, EnumDyeColor.WHITE));
    }

    // ===VARIANTS PART===

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumDyeColor enumDyeColor : EnumDyeColor.values()) {
            items.add(new ItemStack(this, 1, enumDyeColor.getMetadata()));
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockEiceStainedEternalIce.COLOR);
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockEiceStainedEternalIce.COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockEiceStainedEternalIce.COLOR).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    public static String getColorName(ItemStack stack) {
        int metadata = stack.getMetadata();

        return EnumDyeColor.byMetadata(metadata).getName();
    }

}
