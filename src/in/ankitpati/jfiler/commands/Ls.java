package in.ankitpati.jfiler.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Ls {
    ArrayList<String> files;

    public Ls(ArrayList<String> files) {
        this.files = files;

        if (this.files.size() == 0) this.files.add(".");
    }

    public void run() throws IOException {
        for (String file : files) {
            File f = new File(file);

            if (!f.exists()) {
                System.err.println(file + " (No such file or directory)\n");
                continue;
            }

            String listing[] = f.list();

            if (listing == null) System.out.println(file + '\n');
            else {
                Arrays.sort(listing);
                System.out.println(file + ':');
                for (String entry : listing) System.out.print(entry + "  ");
                System.out.println('\n');
            }
        }
    }
}
