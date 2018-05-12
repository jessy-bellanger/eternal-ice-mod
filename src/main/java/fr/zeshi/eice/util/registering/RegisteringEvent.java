package fr.zeshi.eice.util.registering;

import fr.zeshi.eice.init.EiceBlocks;
import fr.zeshi.eice.init.EiceItems;
import fr.zeshi.eice.util.References;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MOD_ID)
public class RegisteringEvent {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> e) {
        EiceItems.init();
        e.getRegistry().registerAll(EiceItems.getModItems());

        for (ItemBlock itemBlock : EiceBlocks.getEiceItemBlocks()) {
            itemBlock.setRegistryName(Objects.requireNonNull(itemBlock.getBlock().getRegistryName()));
        }

        e.getRegistry().registerAll(EiceBlocks.getEiceItemBlocks());
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> e) {
        EiceBlocks.init();
        e.getRegistry().registerAll(EiceBlocks.getEiceBlocks());
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void freezeBlock(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer myHarvester = event.getHarvester();

        if (myHarvester == null) {
            return;
        }

        ItemStack myHarvesterItemStack = myHarvester.getHeldItem(myHarvester.getActiveHand());

        if (myHarvesterItemStack.getItem() != EiceItems.frozenPickaxe) {
            return;
        }

        List<ItemStack> myDropsItemStacks = event.getDrops();
        List<ItemStack> myReplacementDropsItemStacks = new ArrayList<>();

        for (ItemStack currentDropItemStack : myDropsItemStacks) {
            Item currentDropItem = currentDropItemStack.getItem();
            if (currentDropItem == Item.getItemFromBlock(Blocks.ICE)) {
                myReplacementDropsItemStacks.add(new ItemStack(EiceBlocks.eternalIce));
            }
            else {
                myReplacementDropsItemStacks.add(currentDropItemStack);
            }
        }

        myDropsItemStacks.clear();
        myDropsItemStacks.addAll(myReplacementDropsItemStacks);
    }

}
