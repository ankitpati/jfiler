package in.ankitpati.jfiler.tests.testng;

import java.util.ArrayList;

import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Append;

public class TestAppend {
    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Append(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Append(new ArrayList<>());
    }
}
