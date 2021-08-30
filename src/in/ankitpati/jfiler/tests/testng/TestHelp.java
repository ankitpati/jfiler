package in.ankitpati.jfiler.tests.testng;

import java.util.ArrayList;

import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Help;

public class TestHelp {
    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Help(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Help(new ArrayList<>());
    }
}
