
class SaveCommand extends Command{

    private String saveFileName;

    public SaveCommand(String saveFileName){
        this.saveFileName = saveFileName;
    }

    String execute(){
    
        try {
                // Step 3: Call GameState::store() when "save" is entered
                //String saveName = "save.zork";
                GameState.instance().store("files/save1.sav");
                System.out.println("Game saved successfully.");
                return "Game saved!";    

        } catch (Exception e) {
                // Handle file not found exception
                System.err.println("Error: Could not save the game.");
                e.printStackTrace();
               
                return "error: couldn't save the game";    
        }
        
        //        return "Game saved successfully to file " + saveFileName;
    }



}
