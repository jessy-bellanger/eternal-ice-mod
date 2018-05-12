package fr.zeshi.eice.util.crafting;

import com.google.gson.JsonObject;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;

public class ShapedRecoloringRecipe extends ShapedRecipes {

    private static int DYE_SLOT = 4;

    private ShapedRecoloringRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(group, width, height, ingredients, result);
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack myDyeItemStack = inv.getStackInSlot(ShapedRecoloringRecipe.DYE_SLOT);
        int       myDyeMetaData  = EnumDyeColor.byDyeDamage(myDyeItemStack.getItemDamage()).getMetadata();

        boolean myHasDifferentMeta = false;

        int myinvSize = inv.getSizeInventory();
        for (int i = 0; i < myinvSize; i++) {
            if (i == ShapedRecoloringRecipe.DYE_SLOT) {
                continue;
            }

            /*
            The purpose of this verification is to determine if the player tries
            to color a set of blocks having the same color as the dye currently used.
            Note: If there is at least one different block in the set, we let the player
            recolor the blocks (eg. the player has only 7 blocks of a different color and 1
            block of the same color as the dye; it would be frustrating to block the recipe).
             */
            ItemStack myCurrentIngredientItemStack = inv.getStackInSlot(i);
            if (myCurrentIngredientItemStack.getHasSubtypes()) {
                if (myDyeMetaData != myCurrentIngredientItemStack.getMetadata()) {
                    myHasDifferentMeta = true;
                }
            }
            else {
                myHasDifferentMeta = true;
            }
        }


        if ( !myHasDifferentMeta ) {
            return ItemStack.EMPTY; // Why would you color this block with a dye having the same color?
        }

        ItemStack myNormallyReturnedItemStack = super.getCraftingResult(inv);

        return new ItemStack(myNormallyReturnedItemStack.getItem(), myNormallyReturnedItemStack.getCount(), myDyeMetaData);
    }

    public static class Factory implements IRecipeFactory {

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            Ingredient myPossibleIngredients = CraftingHelper.getIngredient(json.get("ingredients"), context);
            ItemStack  myResult     = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

            NonNullList<Ingredient> myIngredientList = NonNullList.withSize(9, myPossibleIngredients);

            myIngredientList.set(ShapedRecoloringRecipe.DYE_SLOT, Ingredient.fromItem(Items.DYE));

            // TODO implement the items groups
            return new ShapedRecoloringRecipe("", 3, 3, myIngredientList, myResult);
        }

    }

}
