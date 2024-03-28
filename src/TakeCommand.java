import java.util.ArrayList;

class TakeCommand extends Command{

    private String itemName;

    public TakeCommand(String itemName){
        this.itemName = itemName;
    }

    String execute() {
        if (itemName.isEmpty()) {
            return "Take what?";
        }
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        int invWeight = 0;
        int invCapacity = 40;
        for (Item item : GameState.instance().inventory) { //calculate weight of inventory (not including what is to be taken)
                invWeight += item.weight;
        }
            
        if (itemName.equals("all")) { 
            int allWeight = 0;
            ArrayList<Item> itemsToTake = new ArrayList<>();
            for (Item item : GameState.instance().roomContents.get(currentRoom)) { //calculates sum of Item weights
                    allWeight += item.weight;
                    itemsToTake.add(item);
            }
            if (invWeight+allWeight <= invCapacity) { //checks if all fits in inventoryg
                for (Item item : itemsToTake) {
                    GameState.instance().removeItemFromRoom(item, currentRoom);
                    GameState.instance().addToInventory(item);
                    return "All taken.";
                }   
            } else {
                return "Everything in here won't fit in your bag!";
            } 
        }               
        String nameOfTaken="";
        try {        
        Item i = GameState.instance().getDungeon().getItem(itemName);
        nameOfTaken = i.getPrimaryName();        
        if (!GameState.instance().roomContents.get(currentRoom).contains(i)) { //check if the desired Item is even in the currentRoom
            return "There's no "+nameOfTaken+" in here!";
        }
        
        for (Item item : GameState.instance().inventory) { //check if the desired Item is already taken          
            if (item.goesBy(itemName)) {
                return "You already have the "+itemName+"!";
            }
        }
        if (invWeight+i.weight > 40) {         //check if the Item-to-be-taken's weight overflows the player's inventory
            return "Inventory is too heavy! ("+(invWeight+i.weight)+"/40)";
        }

        GameState.instance().removeItemFromRoom(i, currentRoom);
        GameState.instance().addToInventory(i);
        } catch (NoItemException e) {             
            System.out.println("There's no "+itemName+" in here!");            
        }
        
        
        
         
       // if(itemName.equals("all"){
        
       // }
 // return "item taken " + itemName;
     //  Item item

       // if(inventory.contains(item)
    

//        inventory.add(itemName);
        if (nameOfTaken.isEmpty()) {
            return "...";
        } else {            
            return nameOfTaken + " taken!";
        }
        
    }

}
