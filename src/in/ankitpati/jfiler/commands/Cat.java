package in.ankitpati.jfiler.commands;

import java.io.*;
import java.util.*;

public class Cat {
    ArrayList<String> files;

    public Cat(ArrayList<String> files) throws IllegalArgumentException {
        if (files.size() == 0)
            throw new IllegalArgumentException("Usage:\n\tcat <source>...");

        this.files = files;
    }

    public void run() throws IOException {
        ArrayList<BufferedReader> brList = new ArrayList<BufferedReader>();

        for (String file : files) {
            if (new File(file).isDirectory()) {
                System.err.println(file + ": Is a directory.");
                continue;
            }

            try {
                BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file)));
                brList.add(br);
            }
            catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
            }
        }

        BufferedWriter bw = new BufferedWriter(
                                            new OutputStreamWriter(System.out));

        for (BufferedReader br : brList) {
            for (String str = br.readLine(); str != null; str = br.readLine()) {
                bw.append(str);
                bw.newLine();
            }

            br.close();
        }

        bw.close();
    }
};
