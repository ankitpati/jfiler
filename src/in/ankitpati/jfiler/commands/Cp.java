package in.ankitpati.jfiler.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Cp {
    ArrayList<String> files;

    public Cp(ArrayList<String> files) throws IllegalArgumentException {
        if (files.size() != 2) incorrectArguments();
        this.files = files;
    }

    protected void incorrectArguments() throws IllegalArgumentException {
        throw new IllegalArgumentException("Usage:\n\tcp <source> <target>");
    }

    protected void action(Path source, Path target) throws IOException {
        if (Files.isDirectory(source, LinkOption.NOFOLLOW_LINKS)) {
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                                BasicFileAttributes attr) throws IOException {
                    Files.copy(dir, target.resolve(source.relativize(dir)),
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES,
                        LinkOption.NOFOLLOW_LINKS);

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file,
                                BasicFileAttributes attr) throws IOException {
                    Files.copy(file, target.resolve(source.relativize(file)),
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES,
                        LinkOption.NOFOLLOW_LINKS);

                    return FileVisitResult.CONTINUE;
                }
            });
        }

        else
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES, LinkOption.NOFOLLOW_LINKS);
    }

    public void run() throws IOException {
        Path source = Paths.get(files.get(0)).toAbsolutePath(),
             target = Paths.get(files.get(1)).toAbsolutePath();

        if (!Files.exists(source, LinkOption.NOFOLLOW_LINKS))
            throw new FileNotFoundException(files.get(0) +
                                                " (No such file or directory)");

        if (target.getParent() != null && !Files.exists(target.getParent(),
                                                    LinkOption.NOFOLLOW_LINKS))
            Files.createDirectories(target.getParent());

        if (Files.isDirectory(target, LinkOption.NOFOLLOW_LINKS))
            target = Paths.get(target.toString(),
                                            source.getFileName().toString());

        action(source, target);
    }
}
