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
   
  // System.out.println(commandString);
  // System.out.println(commandTypes);

   if(commandTypes.equals("take")){   
       String item;          //assigns the second part of the command to 
       if(parseParts.length > 1){
       item = parseParts[1];
       }
       else{
           item = "";
       }
       if(item.isEmpty()){
           return new TakeCommand("");
       }else{
           return new TakeCommand(item);}
   } 

   else if(commandTypes.equals("look")){
      return new LookCommand();
   }


   else if(commandTypes.equals("save")){
      String file;
      if(parseParts.length > 1){
          file = parseParts[1];
      }else{
          file = "";
      }
  return new SaveCommand(file);
   }

   else if (commandTypes.equals("n") || commandTypes.equals("u") || commandTypes.equals("d") || commandTypes.equals("s") || commandTypes.equals("w") || commandTypes.equals("e")) {
        return new MovementCommand(commandString);
    }
   // return new MovementCommand(commandString);
   
   else if (commandTypes.equals("i") || commandTypes.equals("inventory")) {
            return new InventoryCommand();
        }
   else if (commandTypes.equals("drop")) {
        String item;
        if(parseParts.length > 1){
            item = parseParts[1];
        } else {
            item = "";
        }
        return new DropCommand(item);
   }
   else if (parseParts.length == 2) {
            return new ItemSpecificCommand(parseParts[0], parseParts[1]);
        }

    else if(commandTypes.equalsIgnoreCase("score")){
        return new ScoreCommand(); 
    }   
    
    else if(commandTypes.equalsIgnoreCase("health")){
        return new HealthCommand();
    }    
    else if(commandTypes.equalsIgnoreCase("fight")) {
        String enemyName = null;
        if (parseParts.length > 1) {
            enemyName = parseParts[1];
        } else {
            enemyName = "";
        }
        return new FightCommand(enemyName);
    } 
   else{
    return new UnknownCommand(commandString);
    }
    
    }

}

