package praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

//в этих тестах нужен будет мок
// стаба на ингридиенты?
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Before
    public void setUp() throws Exception {
        burger = new Burger();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun); // используем мок чтобы не прописывать параметры в конструктор булки.
        assertEquals("Bun has ben set for burger", bun, burger.bun); // сравниваем с public переменной в бургере. Если был бы не public - применили бы reflectionAPI
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals("Ingredient has been added", burger.ingredients.get(0), ingredient1);
    }

    @Test
    public void removeIngredient() {
        Mockito.when(ingredient2.getName()).thenReturn("element2");
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2); // добавляем ингридеенты напрямую в переменную классы, чтобы убрать зависимость от метода addIngredient
        burger.removeIngredient(0);
        assertEquals("Only one ingredient left in burger after removal operation", 1, burger.ingredients.size());
        assertEquals("Ingredient2 presented in the burger", "element2", burger.ingredients.get(0).getName()); // так как hash не переопредеоен на ингридиенте, можно сравнить два объекта напрямую, но чтобы избежать ложного успешного прохождения в будущем, добавил стабирование, чтобы явно различать объекты по именам
    }

    @Test
    public void moveIngredient() {
        Mockito.when(ingredient2.getName()).thenReturn("element2");
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        burger.moveIngredient(1, 0);
        assertEquals("Ingredient2 moved to 1st position in the burger", "element2", burger.ingredients.get(0).getName());
        assertNull("Ingredient1 moved to 2nd position in the burger", burger.ingredients.get(1).getName());
        // как улучшение можно сделать три ингридента, чторбы более полно проверять перемещение.
    }

    @Test
    public void getPrice() {
        Mockito.when(ingredient1.getPrice()).thenReturn(20.2f);
        Mockito.when(ingredient2.getPrice()).thenReturn(10.1f);
        Mockito.when(bun.getPrice()).thenReturn(5.5f);
        burger.bun = bun;
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        assertEquals("Total burger price is equal to 41.3", 41.3f, burger.getPrice(), 0.00001); // как правило точности до 5 знака после запятой достаточно для таких целей.
    }

    @Test
    public void getReceipt() {
        Mockito.when(ingredient1.getPrice()).thenReturn(20.2f);
        Mockito.when(ingredient2.getPrice()).thenReturn(10.1f);
        Mockito.when(ingredient1.getName()).thenReturn("element1");
        Mockito.when(ingredient2.getName()).thenReturn("element2");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(bun.getPrice()).thenReturn(5.5f);
        Mockito.when(bun.getName()).thenReturn("Bun1");
        burger.bun = bun;
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        assertEquals("Receipt contain info about all ingredients", "(==== Bun1 ====)\n" +
                "= filling element1 =\n" +
                "= sauce element2 =\n" +
                "(==== Bun1 ====)\n" +
                "\n" +
                "Price: 41,300003\n",
                burger.getReceipt());
        System.out.println(burger.getReceipt());
    }
}