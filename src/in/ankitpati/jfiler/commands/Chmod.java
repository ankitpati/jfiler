package in.ankitpati.jfiler.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Set;

public class Chmod {
    ArrayList<String> files;
    Set<PosixFilePermission> perms;

    public Chmod(ArrayList<String> files) throws IllegalArgumentException {
        if (files.size() < 2)
            throw new IllegalArgumentException(
                                "Usage:\n\tchmod <permissions> <target>...");

        if (!files.get(0).matches("^[0-7]{3}$|^[r|w|x|-]{9}$"))
            throw new IllegalArgumentException("Invalid mode");

        this.files = new ArrayList<>(files);

        String permsString = this.files.remove(0);

        if (permsString.length() == 9)
            perms = PosixFilePermissions.fromString(permsString);
        else {
            String permsStringFromOctal = "";
            for (int i = 0; i < 3; ++i) {
                switch (permsString.charAt(i)) {
                case '0':
                    permsStringFromOctal += "---";
                    break;
                case '1':
                    permsStringFromOctal += "--x";
                    break;
                case '2':
                    permsStringFromOctal += "-w-";
                    break;
                case '3':
                    permsStringFromOctal += "-wx";
                    break;
                case '4':
                    permsStringFromOctal += "r--";
                    break;
                case '5':
                    permsStringFromOctal += "r-x";
                    break;
                case '6':
                    permsStringFromOctal += "rw-";
                    break;
                case '7':
                    permsStringFromOctal += "rwx";
                    break;
                }
            }
            perms = PosixFilePermissions.fromString(permsStringFromOctal);
        }
    }

    public void run() throws UnsupportedOperationException, IOException {
        for (String file : files) {
            Path target = Paths.get(file);

            if (!Files.exists(target, LinkOption.NOFOLLOW_LINKS))
                System.err.println(file + " (No such file or directory)");

            else Files.setPosixFilePermissions(target, perms);
        }
    }
}
