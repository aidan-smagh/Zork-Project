

class LookCommand extends Command{

    


    String execute(){
        
       Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
     //  return currentRoom.describe();
      boolean yn = GameState.instance().hasBeenVisited(currentRoom);
      yn = false;
        
     // if(yn){
       //   yn = false;
      //    return currentRoom.describe();
     // }

       return currentRoom.describe();
      // return "Look";
    }



}
