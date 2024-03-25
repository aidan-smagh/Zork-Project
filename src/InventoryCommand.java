
import java.util.ArrayList;
class InventoryCommand extends Command{


    String execute(){
    
    ArrayList<Item> inventory = GameState.instance().getInventory();

    if(inventory.isEmpty()){
        return "no item in inventory";
    }
    else{
        StringBuilder inventoryList = new StringBuilder("You are carrying:\n");
            for (Item item : inventory) {
                inventoryList.append("  ").append(item.getPrimaryName()).append("\n");
            }
            return inventoryList.toString();
        }
  


//    return "inventory";    
    }


}
