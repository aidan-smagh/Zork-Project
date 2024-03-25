import java.util.ArrayList;

class TakeCommand extends Command{

    private String itemName;

    public TakeCommand(String itemName){
        this.itemName = itemName;
    }

    String execute(){
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        ArrayList<Item> inventory = GameState.instance().getInventory();

        
        if (itemName.isEmpty()) {
            return "Take what?";
        }
        
       // if(itemName.equals("all"){
        
      //  }
 // return "item taken " + itemName;
     //  Item item

       // if(inventory.contains(item)
    

//        inventory.add(itemName);

        return "Item taken";

    }

}
