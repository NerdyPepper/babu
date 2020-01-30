import src.ArgParse;
import src.Command;

public class Main {
    public static void main( String args[] ) {
        ArgParse parser = new ArgParse( args );
        System.out.println(parser);
        Command.exec(parser);
    }
}

