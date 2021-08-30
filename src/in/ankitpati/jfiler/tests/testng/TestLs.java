package in.ankitpati.jfiler.tests.testng;

import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Ls;

public class TestLs {
    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Ls(null);
    }
}
