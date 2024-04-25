

class LookCommand extends Command{

    


    String execute(){
        
       Room currentRoom = GameState.instance().currentRoom;
     //  return currentRoom.describe();
     // boolean yn = GameState.instance().hasBeenVisited(currentRoom);
     // yn = false;
      // GameState.instance().hasBeenVisited(currentRoom, false); // Set visited flag to false
 

     // if(yn){
       //   yn = false;
      //    return currentRoom.describe();
     // }

       return currentRoom.describeFull();
      // return "Look";
    }



}
