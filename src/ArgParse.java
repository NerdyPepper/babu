package src;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgParse {
    private Target commandName;
    private String value;

    private static Target matchTarget( String tar ) {
        switch ( tar.toLowerCase() ) {
        case "new":
        case "n":
            return Target.NEW;
        case "build":
        case "b":
            return Target.BUILD;
        case "run":
        case "r":
            return Target.RUN;
        }
        return Target.HELP;
    }

    private void useDefaults() {
        switch ( this.commandName ) {
        case NEW:
            this.value = "new_babu_project";
            break;

        default:
        case BUILD:
        case RUN:
            this.value = null;
            break;
        }
    }

    private static void printHelp() { System.out.println( "help text" ); }

    public ArgParse( String args[] ) {
        if ( args.length == 0 ) {
            printHelp();
            System.exit( 0 );
        } else {
            this.commandName = matchTarget( args[ 0 ] );
            try {
                this.value = Arrays.stream( args ).skip( 1 ).collect(
                    Collectors.joining( "_" ) );
            } catch ( ArrayIndexOutOfBoundsException e ) {
                this.useDefaults();
            }
        }
    }

    public Target getCommandName() {
        return this.commandName;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        String s = String.format( "Sub Command: %s, Value: %s",
                                  this.commandName.toString(), this.value );
        return s;
    }
}
