class Command{

    private String dir;


    Command(String dir){
        this.dir = dir;
    }

    String execute(){
        //Room room = GameState.instance().visit(); 
        Room currentRoom  = GameState.instance().getAdventurersCurrentRoom();
     //   System.out.println("works- currentRoom");
        Room nextRoom = currentRoom.leaveby(dir);
        
        if(nextRoom != null){
     //   System.out.println("works!!!");
        }
     //   else{
     //   System.out.println("No Exit in this direction");
     //   }

        return nextRoom.describe(); 

    }
}

