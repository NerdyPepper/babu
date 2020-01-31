package src;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

interface Executable {
    public void exec(String args);
}

class NewProject implements Executable {
    static String projTemplate = 
        "public class Main {\n" +
        "    public static void main(String args[]) {\n" +
        "        System.out.println(\"Hello, world!\");" +
        "    }\n" +
        "}";

    public void exec(String args) {
        File projDir = new File(args);
        File projSrc = new File(projDir, "src");
        File projTarget = new File(projDir, "target");
        File projMain = new File(projSrc, "Main.java");
        boolean didMake = false;
        if (!projDir.exists()) {
            try {
                didMake = projDir.mkdirs() &&
                    projSrc.mkdirs() &&
                    projTarget.mkdirs() &&
                    projMain.createNewFile();
            } catch (IOException e) {
                System.err.printf("ERR: %s\n", e);
            }
        }
        if (!didMake) {
            System.err.printf("ERROR: Unable to create project '%s'\n", args);
            System.exit(-1);
        } else {
            try {
                FileWriter mainFile = new FileWriter(projMain);
                mainFile.write(projTemplate);
                mainFile.close();
            } catch (IOException e) { 
                System.err.printf("ERR: %s\n", e);
            }
        }
    }
}

class BuildProject implements Executable {
    public void exec(String args) {
        System.out.println(args);
    }
}

class RunProject implements Executable {
    public void exec(String args) {
        System.out.println(args);
    }
}

public class Command {
    private static final Map<Target, Executable> commands = new HashMap<Target, Executable>();
    static {
        commands.put(Target.NEW, new NewProject());
    }

    public static void exec(ArgParse ap) {
        commands.get(ap.getCommandName()).exec(ap.getValue());
    }
}
