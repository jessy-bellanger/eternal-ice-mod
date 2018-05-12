package fr.zeshi.eice.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

abstract class ItemEice extends Item {

    ItemEice(String unlocalizedName, CreativeTabs creativeTab) {
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(creativeTab);
    }

}
