package in.ankitpati.jfiler.tests.testng;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.ankitpati.jfiler.commands.Chmod;
import in.ankitpati.jfiler.commands.Rm;
import in.ankitpati.jfiler.commands.Touch;

public class TestChmod {
    ArrayList<String> files;

    @BeforeClass
    public void setup() throws IOException {
        files = new ArrayList<>();
        files.add("test/chmod.txt");
        new Touch(files).run();
    }

    @BeforeMethod
    public void initialiseFiles() {
        files = new ArrayList<>();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullArgument() {
        new Chmod(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyArgument() {
        new Chmod(files);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidMode() {
        files.add("chmod");
        files.add("test/chmod.txt");
        new Chmod(files);
    }

    @Test
    public void testValidStringMode() throws IOException {
        files.add("rwxrwxrwx");
        files.add("test/chmod.txt");

        try {
            new Chmod(files).run();
        }
        catch (UnsupportedOperationException uoe) {
            System.err.println(
                        "This operation cannot be tested on this platform.");
            return;
        }

        Assert.assertEquals(PosixFilePermissions.toString(
            Files.getPosixFilePermissions(Paths.get(files.get(1)),
            LinkOption.NOFOLLOW_LINKS)), "rwxrwxrwx");
    }

    @Test
    public void testValidNumericMode() throws IOException {
        files.add("644");
        files.add("test/chmod.txt");

        try {
            new Chmod(files).run();
        }
        catch (UnsupportedOperationException uoe) {
            System.err.println(
                        "This operation cannot be tested on this platform.");
            return;
        }

        Assert.assertEquals(PosixFilePermissions.toString(
            Files.getPosixFilePermissions(Paths.get(files.get(1)),
            LinkOption.NOFOLLOW_LINKS)), "rw-r--r--");
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
