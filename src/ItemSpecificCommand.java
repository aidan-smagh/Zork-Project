
class ItemSpecificCommand extends Command{

    private String verb;
    private String noun;


    public ItemSpecificCommand(String verb, String noun){
        this.verb = verb;
        this.noun = noun;
    }

    String execute(){

    try{
        Item item = GameState.instance().getItemInVicinityNamed(noun);
       //item = drpepper


        if (item != null){

            String responseMsg = item.getMessageForVerb(verb);
            
            if (verb.contains("Transform")) {
            
        // String targetItemName = verb.split("\\(")[1].split("\\)")[0];
            String targetItemName = extractTargetItemName(verb);
               if(targetItemName != null){
                    Item targetItem = GameState.instance().getItemFromInventoryNamed(targetItemName);
                    transformItem(item, targetItem);

                 }
              // else{
               //System.out.println("targetItemName is null");
               // return "targetItemName is null";
              // }
              
             }
            

            if(verb.contains("Teleport")){
                teleport();
            }

            if (responseMsg != null) {
                    return responseMsg;
                } else {
                    return "You can't " + verb + " the " + noun + ".";
                }
        } else {
                return "There is no " + noun + " here.";
            }

    } catch (Exception e) {
              return "Assimilate what?";
          }
      
            // item = GameState.instance().getItemInVicinityNamed(noun);
    }       
   

    private String extractTargetItemName(String verb) {
        int transformIndex = verb.indexOf("Transform");
        if (transformIndex != -1) {
            int startIndex = verb.indexOf('(', transformIndex);
            int endIndex = verb.indexOf(')', startIndex);
            if (startIndex != -1 && endIndex != -1) {
                return verb.substring(startIndex + 1, endIndex);
            }
        }
        return null;
    }




     void transformItem(Item originalItem, Item targetItem) {
        // Remove the original item from the inventory
       // GameState.instance().removeFromInventory(originalItem);
      try{
        System.out.println("Removing " + originalItem.getPrimaryName() + " from the current room...");

        GameState.instance().removeItemFromRoom(originalItem, GameState.instance().currentRoom);
     System.out.println("Removed " + originalItem.getPrimaryName() + " from the current room.");
    
        // Add the target item to the inventory
       // GameState.instance().addToInventory(targetItem);
        System.out.println("Adding " + targetItem.getPrimaryName() + " to the current room...");

     GameState.instance().addItemToRoom(targetItem, GameState.instance().currentRoom);
    System.out.println("done");
        System.out.println("Added " + targetItem.getPrimaryName() + " to the current room.");
      }
      catch (Exception e) {
        // Handle the exception if the item is not found
        e.printStackTrace();
    }
    }

 

    private void teleport() {
    // Get the current room
    Room currentRoom = GameState.instance().getAdventurersCurrentRoom();

    // Get a random room different from the current room
    Room randomRoom;
    do {
        int randomIndex = (int) (Math.random() * GameState.instance().getDungeon().rooms.size());
        randomRoom = GameState.instance().getDungeon().rooms.get(randomIndex);
    } while (randomRoom == currentRoom);

    // Teleport the player to the random room
    GameState.instance().setAdventurersCurrentRoom(randomRoom);
    System.out.println("You have been teleported to " + randomRoom.getName() + ".");
    
    }
}


      
