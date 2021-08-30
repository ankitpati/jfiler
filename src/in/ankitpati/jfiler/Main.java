package in.ankitpati.jfiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import in.ankitpati.jfiler.commands.Append;
import in.ankitpati.jfiler.commands.Cat;
import in.ankitpati.jfiler.commands.Chmod;
import in.ankitpati.jfiler.commands.Cp;
import in.ankitpati.jfiler.commands.Help;
import in.ankitpati.jfiler.commands.Ll;
import in.ankitpati.jfiler.commands.Ls;
import in.ankitpati.jfiler.commands.Mkdir;
import in.ankitpati.jfiler.commands.Mv;
import in.ankitpati.jfiler.commands.Rm;
import in.ankitpati.jfiler.commands.Touch;

class Main {
    public static void main(String args[]) {
        if (args.length == 0) {
            System.err.println(
                    "Usage:\n\tjava -jar jFiler.jar <operation> [argument]...");
            System.exit(1);
        }

        ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(args));
        commandArgs.remove(0);

        try {
            switch (args[0].toLowerCase()) {
            case "cat":
                new Cat(commandArgs).run();
                break;
            case "touch":
                new Touch(commandArgs).run();
                break;
            case "cp":
                new Cp(commandArgs).run();
                break;
            case "mv":
                new Mv(commandArgs).run();
                break;
            case "append":
                new Append(commandArgs).run();
                break;
            case "rm":
                new Rm(commandArgs).run();
                break;
            case "mkdir":
                new Mkdir(commandArgs).run();
                break;
            case "ls":
                new Ls(commandArgs).run();
                break;
            case "chmod":
                new Chmod(commandArgs).run();
                break;
            case "ll":
                new Ll(commandArgs).run();
                break;
            case "help":
                new Help(commandArgs).run();
                break;
            default:
                System.err.println("jFiler: Unrecognised operation!");
                break;
            }
        }

        catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        }

        catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }

        catch (UnsupportedOperationException uoe) {
            System.err.println(
                        "This operation is not supported on this platform.");
        }

        catch (IOException ioe) {
            System.err.println(ioe.getMessage() +
                                    " could not be read from, or written to!");
        }
    }
}
