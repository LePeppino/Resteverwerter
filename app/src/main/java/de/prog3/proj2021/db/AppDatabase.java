package de.prog3.proj2021.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Recipe.class, Ingredient.class, ShoppingList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract ShoppingListDao shoppingListDao();
}
