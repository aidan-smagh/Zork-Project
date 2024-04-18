import java.util.HashSet;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.ArrayList;
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
           

            ArrayList<String> fullCommands = item.getFullCommands();

            String fullCommand = "";
                // Iterate through the commands to find the matching one
                for (String fullCommand1 : fullCommands) {
                    if (fullCommand1.contains(verb)) {
                        // Perform the action associated with the command
                        // For now, just printing the full command

                        fullCommand = fullCommand1;
      //                  System.out.println("hi " + fullCommand);
                        
                        // Optionally, you can return a response message based on the command
                        // String responseMsg = item.getMessageForVerb(verb);
                        // return responseMsg;
                    }
                }





           // String fullCommand = item.getFullCommand();
    //        System.out.println("hi "+fullCommand);


            if (fullCommand.contains("Transform")) {
            
        // String targetItemName = verb.split("\\(")[1].split("\\)")[0];
            String targetItemName = extractTargetItemName(fullCommand);
              
          // System.out.println("target item: "+ targetItemName);

           Item targetItem1 = GameState.instance().getDungeon().getItem(targetItemName);
         //   System.out.println("hi "+targetItem1);
          // Hashtable<String, Item> dungeonItems = GameState.instance().getDungeon().getItem();

            // Print the items in the dungeon
         //   System.out.println("Items in the dungeon:");
         //   for (String itemName : dungeonItems.keySet()) {
         //       System.out.println("- " + itemName);
          //  }




           if(targetItemName != null){
              // System.out.println("hi");
                    Item targetItem = GameState.instance().getDungeon().getItem(targetItemName);
           //         System.out.println(targetItem);
                    transformItem(item, targetItem);

                 }
              // else{
               //System.out.println("targetItemName is null");
               // return "targetItemName is null";
              // }
              
             }
            

            if(fullCommand.contains("Teleport")){
        //        System.out.println("teleport exists");
                teleport();
            }

            if(fullCommand.contains("Score")){

            int scoreValue = extractScoreValue(fullCommand);
            
          //  System.out.println("score: "+scoreValue);

            GameState.instance().addToScore(scoreValue);
            }
            
            if (fullCommand.contains("Win")) {
                win(fullCommand);
                
            }
            if (fullCommand.contains("Die")) {
                die(fullCommand);
            }
         //wound
           if(fullCommand.contains("Wound")){
          //  System.out.println("wound exists");
            wound(fullCommand);
           }

            if(fullCommand.contains("Drop")){
         //   System.out.println("hii!");
                drop(item); //drop method
            }

            
            if(fullCommand.contains("Disappear")){
                disappear(item);
            }

            //disappear
            if (fullCommand.contains("Disappear")) {
                GameState.instance().disappear(item);
            }
            if (fullCommand.contains("Drop")) {
                GameState.instance().drop(item);
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


    private int extractScoreValue(String verb) {
    int scoreIndex = verb.indexOf("Score");
 //   System.out.println(scoreIndex);
    
    if (scoreIndex != -1) {
        int startIndex = verb.indexOf('(', scoreIndex);
        int endIndex = verb.indexOf(')', startIndex);
        if (startIndex != -1 && endIndex != -1) {
            String scoreString = verb.substring(startIndex + 1, endIndex);
            return Integer.parseInt(scoreString);
        }
    }
    return 0; // Return 0 if score value not found
    }




     void transformItem(Item originalItem, Item targetItem) {
        // Remove the original item from the inventory
       // GameState.instance().removeFromInventory(originalItem);
      try{
  //      System.out.println("Removing " + originalItem.getPrimaryName() + " from the current room...");
        GameState.instance().removeFromInventory(originalItem);
        GameState.instance().removeItemFromRoom(originalItem, GameState.instance().currentRoom);
   //  System.out.println("Removed " + originalItem.getPrimaryName() + " from the current room.");
    
        // Add the target item to the inventory
       // GameState.instance().addToInventory(targetItem);
 //       System.out.println("Adding " + targetItem.getPrimaryName() + " to the current room...");

     GameState.instance().addItemToRoom(targetItem, GameState.instance().currentRoom);
  //  System.out.println("done");
     //   System.out.println("Added " + targetItem.getPrimaryName() + " to the current room.");
      }
      catch (Exception e) {
        // Handle the exception if the item is not found
        e.printStackTrace();
    }
    }

 

    private void teleport() {
    // Get the current room
    Room currentRoom = GameState.instance().getAdventurersCurrentRoom();

  //  System.out.println("current Room: " +currentRoom);
    // Get a random room different from the current room
    
   
ArrayList<Room> availableRooms = new ArrayList<>(GameState.instance().getDungeon().rooms.values());
    availableRooms.remove(currentRoom);

    // Get a random index to select a room
    int randomIndex = (int) (Math.random() * availableRooms.size());

    // Select the random room
    Room randomRoom = availableRooms.get(randomIndex);
  //  System.out.println("Random Room: " + randomRoom.getName());



  //  System.out.println("randomRoom: " + randomRoom);

    // Teleport the player to the random room
    GameState.instance().setAdventurersCurrentRoom(randomRoom);
//    System.out.println("You have been teleported to " + randomRoom.getName() + ".");
    
    }


    private int wound(String verb) {
         int woundIndex = verb.indexOf("Wound");
     //    System.out.println(woundIndex);

         if (woundIndex != -1) {
             int startIndex = verb.indexOf('(', woundIndex);
             int endIndex = verb.indexOf(')', startIndex);
             if (startIndex != -1 && endIndex != -1) {
             String woundString = verb.substring(startIndex + 1, endIndex); 
             int woundValue = Integer.parseInt(woundString);
       //      System.out.println(woundValue);
             int currentHP = GameState.instance().PLAYER.getHP();
         //    System.out.println(currentHP);
             int newHP = currentHP - woundValue;

          

          GameState.instance().PLAYER.setHP(newHP);
  //          System.out.println("You've been wounded!");
         
         }
     }
     return 0; // Return 0 if score value not found
     }


    private void drop(Item item){
        DropCommand drop = new DropCommand(item.getPrimaryName());
        drop.execute();
    //    System.out.println("Item dropped");
    }

    private void disappear(Item item){
    
    GameState.instance().removeFromInventory(item);
         GameState.instance().removeItemFromRoom(item, GameState.instance().currentRoom);
         GameState.instance().getDungeon().dungeonItems.remove(item.getPrimaryName());
    }
    private void win(String fullCommand) {
        int winTextIndex = fullCommand.indexOf(':');

        if (winTextIndex != -1) {
            String answer = fullCommand.substring(winTextIndex + 1).trim();
            System.out.println(answer);
        }
        System.out.println("Congratulations, you have won the game!");
        GameState.instance().endGame();
    }
    private void die(String fullCommand) {
        int dieTextIndex = fullCommand.indexOf(':');
        if (dieTextIndex != -1) {
            String answer = fullCommand.substring(dieTextIndex + 1).trim();
            System.out.println(answer);
        }
        System.out.println("You have died");
        System.out.println("GAME OVER!");
        GameState.instance().endGame();
    }
}


      
