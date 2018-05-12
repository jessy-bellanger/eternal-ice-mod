package fr.zeshi.eice.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockEiceEternalIce extends BlockEice {

    public BlockEiceEternalIce() throws Exception {
        this("eternal_ice");

        if (this.getClass() != BlockEiceEternalIce.class) {
            throw new Exception("This constructor shouldn't be called by a daughter class");
        }
    }

    BlockEiceEternalIce(String unlocalizedName) {
        super(unlocalizedName, Material.ICE);

        this.initBlockProperties();
    }

    private void initBlockProperties() {
        this.setDefaultSlipperiness(.98F);  // Taken from BlockIce
        this.setHardness(0.5F);             // Taken from BlockIce
        this.setLightOpacity(3);            // Taken from BlockIce
        this.setSoundType(SoundType.GLASS); // Taken from BlockIce

        this.setHarvestLevel("pickaxe", 0);

        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS); // TODO creative tab
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void harvestBlock(@Nonnull World worldIn, EntityPlayer harvestingPlayer, @Nonnull BlockPos blockPos, @Nonnull IBlockState blockState, @Nullable TileEntity te, @Nonnull ItemStack harvestingItemStack) {
        boolean myIsPlayerAbleToSilkHarvest = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, harvestingItemStack) > 0;

        if (myIsPlayerAbleToSilkHarvest) {
            Block.spawnAsEntity(worldIn, blockPos, this.getSilkTouchDrop(blockState));
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, EnumFacing side) {
        Block blockNextToMe = blockAccess.getBlockState(pos.offset(side)).getBlock();
        return !(blockNextToMe instanceof BlockEiceEternalIce); // This includes BlockEiceStainedEternalIce as it extends BlockEiceEternalIce
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
