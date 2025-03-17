package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
public class BunTest {
    Bun bun;
    String testName;
    Float price;

    @Before
    public void setUp() {
        Faker faker = new Faker();
        testName = faker.funnyName().name();
        Random r = new Random();
        price = r.nextFloat() * 30.0f;
        bun = new Bun(testName, price);
    }

    @Test
    public void getName() {
        Assert.assertEquals("Name of bun is equal the same as used in constructor", bun.getName(), testName);
    }

    @Test
    public void getPrice() {
        assertEquals("Price of bun is equal the same as used in constructor", price, bun.getPrice(), 0.0);
    }
}