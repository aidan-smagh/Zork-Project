public class CommandFactory {

    private static CommandFactory instance = null;

    private CommandFactory() {

    }

    public static CommandFactory instance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    Command parse(String commandString) {
        return new Command(commandString);
        //return new MovementCommand(commandString);
    }
}
