package in.ankitpati.jfiler.tests.testng;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Rm;
import in.ankitpati.jfiler.commands.Touch;

public class TestRm {
    ArrayList<String> files;

    @BeforeClass
    public void setup() throws IOException {
        files = new ArrayList<>();
        files.add("test/rm0.txt");
        files.add("test/rm1.txt");
        files.add("test/rm2.txt");
        files.add("test/rm/rm3.txt");
        new Touch(files).run();
    }

    @BeforeMethod
    public void initialiseFiles() {
        files = new ArrayList<>();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Rm(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Rm(files);
    }

    @Test
    public void testValidArgument() throws IOException {
        files.add("test/rm0.txt");
        new Rm(files).run();
        Assert.assertEquals(new File(files.get(0)).exists(), false);
    }

    @Test
    public void testMultipleValidArguments() throws IOException {
        files.add("test/rm1.txt");
        files.add("test/rm2.txt");
        new Rm(files).run();
        for (String file : files)
            Assert.assertEquals(new File(file).exists(), false);
    }

    @Test
    public void testDirectoryArgument() throws IOException {
        files.add("test/rm/");
        new Rm(files).run();
        Assert.assertEquals(new File(files.get(0)).exists(), false);
    }

    @AfterMethod
    public void destroyFiles() {
        files = null;
    }

    @AfterClass
    public void teardown() throws IOException {
        files = new ArrayList<>();
        files.add("test/");
        new Rm(files).run();
    }
}
