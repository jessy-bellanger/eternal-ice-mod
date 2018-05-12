package fr.zeshi.eice.util.crafting;

import com.google.gson.JsonObject;
import fr.zeshi.eice.init.EiceItems;
import net.minecraft.inventory.InventoryCrafting;
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

public class ShapedFreezingRecipe extends ShapedRecipes {

    private static int CRYSTAL_SLOT = 4;

    private ShapedFreezingRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(group, width, height, ingredients, result);
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> myRemainingItems = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        ItemStack myCrystalItemStack = inv.getStackInSlot(ShapedFreezingRecipe.CRYSTAL_SLOT).copy();

        myCrystalItemStack.setItemDamage(myCrystalItemStack.getItemDamage() + 1);

        myRemainingItems.set(ShapedFreezingRecipe.CRYSTAL_SLOT, myCrystalItemStack);

        return myRemainingItems;
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack myCrystalItemStack = inv.getStackInSlot(ShapedFreezingRecipe.CRYSTAL_SLOT);

        if (myCrystalItemStack.getItemDamage() == myCrystalItemStack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        return super.getCraftingResult(inv);
    }

    public static class Factory implements IRecipeFactory {

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            Ingredient myIngredient = CraftingHelper.getIngredient(json.get("item"), context);
            ItemStack  myResult     = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

            NonNullList<Ingredient> myIngredientList = NonNullList.withSize(9, myIngredient);

            myIngredientList.set(ShapedFreezingRecipe.CRYSTAL_SLOT, Ingredient.fromItem(EiceItems.frozenCrystal));

            return new ShapedFreezingRecipe("", 3, 3, myIngredientList, myResult);
        }

    }

}
