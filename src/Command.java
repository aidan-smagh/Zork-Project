class Command{

    private String dir;


    Command(String dir){
        this.dir = dir;
    }

    String execute(){
        //Room room = GameState.instance().visit(); 

    if (dir.equals("save")) {
            try {
                // Step 3: Call GameState::store() when "save" is entered
                //String saveName = "save.zork";
                GameState.instance().store("files/save.sav");
                System.out.println("Game saved successfully.");
            } catch (Exception e) {
                // Handle file not found exception
                System.err.println("Error: Could not save the game.");
                e.printStackTrace();
            }
    }

            
    
    else if(dir.equals("restore")){
            try {
                GameState.instance().restore("files/save.sav");
                System.out.println("Game restored successfully.");
            } catch (Exception e) {
                System.err.println("Error: Could not restore the game.");
                e.printStackTrace();
            }

        }


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

