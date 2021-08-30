package in.ankitpati.jfiler.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Mv extends Cp {
    public Mv(ArrayList<String> files) throws IllegalArgumentException {
        super(files);
    }

    protected void incorrectArguments() throws IllegalArgumentException {
        throw new IllegalArgumentException("Usage:\n\tmv <source> <target>");
    }

    protected void action(Path source, Path target) throws IOException {
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
}
