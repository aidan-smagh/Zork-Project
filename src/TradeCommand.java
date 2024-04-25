import java.util.*;

class TradeCommand extends Command {
    String charName;
    Character c;
    boolean validInput;

    public TradeCommand(String charName) {
        this.charName = charName;
        this.c = c;
        this.validInput = false;
    }

    String execute() {
        //check if character is there
        try {
            c = GameState.instance().getDungeon().getCharacter(charName);
          /*  if (!GameState.instance().charsInRoom.get(GameState.instance().currentRoom).contains(c) {
                throw new Exception();
            } */
        } catch (Exception e) {
            return "There's no "+charName+" in here.";
        }
        if (GameState.instance().charsInRoom.get(GameState.instance().currentRoom).contains(c)) {
        if (c.inventory.isEmpty()) {
            return "The "+c.getName()+" has nothing to trade.";
            
        }
        System.out.println("---");
        System.out.println("The "+c.getName()+" shows his wares. 'Take a look adventurer...'");
        for (Item i : c.inventory) {
            System.out.println(i.getPrimaryName()+" - "+i.getWeight());
        }
        if (GameState.instance().inventory.isEmpty()) {
            return "However, your noticably empty pockets make the "+c.getName()+" take no interest in you.";
        }
        System.out.println("...");
        //list PLAYER's items and their values
        System.out.println("You have:");
        for (Item i : GameState.instance().inventory) {
            System.out.println(i.getPrimaryName()+" - "+i.getWeight());
        }
        //get item to obtain from NPC
        System.out.println("What would you like from the "+c.getName()+"?");
        Scanner input = new Scanner(System.in);
        Item itemToGet=null;
        while (!validInput) {
            String line = input.nextLine();
            
                    try {
                     Item scrapItem = GameState.instance().getDungeon().getItem(line);
                        if (!c.inventory.contains(scrapItem)) {
                            return "The "+c.getName()+" has heard of that item , but doesn't have it - they go back to wandering the room.";
                        }
                    } catch (Exception e) {
                       // System.out.println("The "+c.getName()+" has heard of that item, but doesn't have it - They go back to wandering the room.");
                       // validInput = false;
                    }
                 
            
            try {                 
            Item item = GameState.instance().getDungeon().getItem(line); 
            validInput = true;
            itemToGet = item;
            } catch (Exception e) {
                System.out.println(c.getName()+" doesn't have that item. Pick something they have.");
                validInput = false;
            }
        }
        
        //get item to give away
        System.out.println("What do you want to give up for the "+itemToGet.getPrimaryName()+"?");
        Item itemToGive=null;
        validInput = false;
        while (!validInput) {
             try {
             Item item = GameState.instance().getDungeon().getItem(input.nextLine());
             validInput = true;
             itemToGive = item;
             } catch (Exception e) {
                 System.out.println("You don't have that item.");
                 validInput = false;
             }
         }

        //move items according to trade parameters
        GameState.instance().removeFromInventory(itemToGive);
        c.addToInventory(itemToGive);
        c.removeFromInventory(itemToGet);
        GameState.instance().addToInventory(itemToGet);
    
        return "The "+c.getName()+" is pleased with your decision - the trade is done!";
    } else { 
        return "There's no "+charName+" in this room.";
    }
    }
}
