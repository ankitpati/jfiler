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

import in.ankitpati.jfiler.commands.Mkdir;
import in.ankitpati.jfiler.commands.Rm;
import in.ankitpati.jfiler.commands.Touch;

public class TestTouch {
    ArrayList<String> files;

    @BeforeClass
    public void setup() throws IOException {
        files = new ArrayList<>();
        files.add("test");
        new Mkdir(files).run();
    }

    @BeforeMethod
    public void initialiseFiles() {
        files = new ArrayList<>();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Touch(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Touch(files);
    }

    @Test
    public void testValidArgument() throws IOException {
        files.add("test/touch0.txt");
        new Touch(files).run();
        Assert.assertEquals(new File(files.get(0)).exists(), true);
    }

    @Test
    public void testMultipleValidArguments() throws IOException {
        files.add("test/touch1.txt");
        files.add("test/touch2.txt");
        new Touch(files).run();
        for (String file : files)
            Assert.assertEquals(new File(file).exists(), true);
    }

    @Test
    public void testNonExistentDirectoryArgument() throws IOException {
        files.add("test/touch/a.txt");
        new Touch(files).run();
        Assert.assertEquals(new File(files.get(0)).exists(), true);
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
