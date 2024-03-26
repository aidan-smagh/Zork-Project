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
        ArrayList<Item> inventory = GameState.instance().getInventory();
        String nameOfTaken="";
        try {
        Item i = GameState.instance().getDungeon().getItem(itemName);
        nameOfTaken = i.getPrimaryName();
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
            return nameOfTaken + " taken";
        }
        
    }

}
