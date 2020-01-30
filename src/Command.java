package src;

import java.util.HashMap;
import java.util.Map;

interface Executable {
    public void exec(String args);
}

class NewProject implements Executable {
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
