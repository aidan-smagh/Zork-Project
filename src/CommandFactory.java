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
 //       return new Command(commandString);
        //return new MovementCommand(commandString);
   
   String[] parseParts = commandString.split(" ", 2);
   String commandTypes = parseParts[0].toLowerCase();

   if(commandTypes.equals("take")){   
       String item;          //assigns the second part of the command to 
       if(parseParts.length > 1){
       item = parseParts[1];
       }else{
           item = "";
       }
   
   return new TakeCommand(item);
     }
   

    else{
    return new UnknownCommand(commandString);
    }
    
    }

}

