package in.ankitpati.jfiler.commands;

import java.util.ArrayList;

public class Help {
    ArrayList<String> commands;

    public Help(ArrayList<String> commands) throws IllegalArgumentException {
        if (commands.size() == 0)
            throw new IllegalArgumentException("Usage:\n\thelp <command>...");

        this.commands = commands;
    }

    public void run() {
        for (String command : commands) {
            switch (command.toLowerCase()) {
            case "cat":
                System.out.println("Usage:\n\tcat <source>...");
                break;
            case "touch":
                System.out.println("Usage:\n\ttouch <target>...");
                break;
            case "cp":
                System.out.println("Usage:\n\tcp <source> <target>");
                break;
            case "mv":
                System.out.println("Usage:\n\tmv <source> <target>");
                break;
            case "append":
                System.out.println("Usage:\n\tappend <target>");
                break;
            case "rm":
                System.out.println("Usage:\n\trm <target>...");
                break;
            case "mkdir":
                System.out.println("Usage:\n\tmkdir <target>...");
                break;
            case "ls":
                System.out.println("Usage:\n\tls [target]...");
                break;
            case "chmod":
                System.out.println("Usage:\n\tchmod <permissions> <target>...");
                break;
            case "ll":
                System.out.println("Usage:\n\tll [target]...");
                break;
            case "help":
                System.out.println("Usage:\n\thelp <command>...");
                break;
            default:
                System.out.println("Unrecognised command!");
                break;
            }
        }
    }
}
