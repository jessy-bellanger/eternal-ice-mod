package fr.zeshi.eice.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

abstract class BlockEice extends Block {

    BlockEice(String unlocalizedName, Material materialIn) {
        super(materialIn);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
    }

}
