package fr.zeshi.eice.items;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPickaxeEiceFrozen extends ItemPickaxeEice {

    public ItemPickaxeEiceFrozen() {
        super("frozen_pickaxe", CreativeTabs.TOOLS, ToolMaterial.DIAMOND);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.isItemEnchanted()) {
            int silkTouchIndex = this.getSilkTouchIndex(stack);

            if (silkTouchIndex != -1) {
                stack.getEnchantmentTagList().removeTag(silkTouchIndex);
            }
        }

        return super.hasEffect(stack);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        Material myBlockMaterial = player.world.getBlockState(pos).getMaterial();

        int silkTouchIndex = this.getSilkTouchIndex(itemstack);

        if (myBlockMaterial == Material.ICE) {
            if (silkTouchIndex == -1) {
                itemstack.addEnchantment(Enchantments.SILK_TOUCH, 1);
            }
        }
        else {
            if (silkTouchIndex != -1) {
                itemstack.getEnchantmentTagList().removeTag(silkTouchIndex);
            }
        }

        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        int silkTouchIndex = this.getSilkTouchIndex(stack);

        if (silkTouchIndex != -1) {
            stack.getEnchantmentTagList().removeTag(silkTouchIndex);
        }
    }

    private int getSilkTouchIndex(ItemStack itemStack) {
        NBTTagList myEnchantmentTagList = itemStack.getEnchantmentTagList();
        int myTagCount = myEnchantmentTagList.tagCount();

        for (int i = 0; i < myTagCount; i++) {
            if (myEnchantmentTagList.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.SILK_TOUCH)) {
                return i;
            }
        }

        return -1;
    }

}
