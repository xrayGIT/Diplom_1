package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class IngredientTest {
    Ingredient ingredient;
    Float price;
    String ingredientName;
    IngredientType ingredientType;

    public IngredientTest(Float price, String ingredientName, IngredientType ingredientType) {
        this.price = price;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] testData(){
        return new Object[][]{
                {10.0F, "testName1", IngredientType.SAUCE},
                {11.21F, "testName2", IngredientType.FILLING},
                {0f, "testName3", IngredientType.SAUCE}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, ingredientName, price);
    }


    @Test
    public void getPrice() {
        assertEquals("Price is " + price, price, ingredient.getPrice(), 0.0);
    }

    @Test
    public void getName() {
        assertEquals("Ingredient name is " + ingredientName, ingredientName, ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals("Ingredient type is " + ingredientType, ingredientType, ingredient.getType());
    }
}