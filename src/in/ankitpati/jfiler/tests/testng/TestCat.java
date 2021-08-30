package in.ankitpati.jfiler.tests.testng;

import java.util.ArrayList;

import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Cat;

public class TestCat {
    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Cat(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Cat(new ArrayList<>());
    }
}
