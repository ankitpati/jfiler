package in.ankitpati.jfiler.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Append {
    ArrayList<String> files;

    public Append(ArrayList<String> files) throws IllegalArgumentException {
        if (files.size() != 1)
            throw new IllegalArgumentException("Usage:\n\tappend <target>");

        this.files = files;
    }

    public void run() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                    new FileOutputStream(files.get(0), true)));
        BufferedReader br = new BufferedReader(
                                            new InputStreamReader(System.in));

        System.out.println("Enter text to append, terminate with EOF.");
        for (String str = br.readLine(); str != null; str = br.readLine()) {
            bw.append(str);
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
