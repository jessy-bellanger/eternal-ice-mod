package fr.zeshi.eice.init;

import fr.zeshi.eice.items.ItemEiceFrozenCrystal;
import fr.zeshi.eice.items.ItemPickaxeEiceFrozen;
import fr.zeshi.eice.util.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = References.MOD_ID)
public abstract class EiceItems {

    public static Item frozenCrystal;
    public static Item frozenPickaxe;

    private static Item[] modItems;

    public static void init() {
        EiceItems.frozenCrystal = new ItemEiceFrozenCrystal();
        EiceItems.frozenPickaxe = new ItemPickaxeEiceFrozen();

        EiceItems.modItems = new Item[] {
                EiceItems.frozenCrystal,
                EiceItems.frozenPickaxe,
        };
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        for (Item eiceItem : EiceItems.modItems) {
            EiceItems.registerModel(eiceItem);
        }
    }

    private static void registerModel(Item eiceItem) {
        ResourceLocation      myResourceLocation      = new ResourceLocation(References.MOD_ID, eiceItem.getUnlocalizedName().substring(5));
        ModelResourceLocation myModelResourceLocation = new ModelResourceLocation(myResourceLocation, "inventory");

        ModelLoader.setCustomModelResourceLocation(eiceItem, 0, myModelResourceLocation);
    }

    public static Item[] getModItems() {
        return EiceItems.modItems;
    }
}


