package fr.zeshi.eice.items;

import net.minecraft.creativetab.CreativeTabs;

public class ItemEiceFrozenCrystal extends ItemEice {

    public ItemEiceFrozenCrystal() {
        super("frozen_crystal", CreativeTabs.MATERIALS);

        this.setMaxDamage(64);
        this.setMaxStackSize(1);
    }

}
