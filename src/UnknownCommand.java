
class UnknownCommand extends Command{

    private String bogusCommandString;

    public UnknownCommand(String bogusCommandString){
        this.bogusCommandString = bogusCommandString;
    }

    String execute(){
        return "Unknown command: " + bogusCommandString;
    }




}
