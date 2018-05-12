package fr.zeshi.eice.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

abstract class ItemPickaxeEice extends ItemPickaxe {

    ItemPickaxeEice(String unlocalizedName, CreativeTabs creativeTab, ToolMaterial material) {
        super(material);

        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(creativeTab);
    }

}
