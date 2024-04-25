class FightCommand extends Command {
  
    String enemyName;

    public FightCommand(String enemyName) {
       
        this.enemyName = enemyName;
        
    }
    String execute() {
        if (this.enemyName.isEmpty()) {
            return "Fight what?";
        }
        System.out.println("");
        try {
            //grab the NPC in the room
            Character i = GameState.instance().getDungeon()
                .getCharacter(enemyName);
            Room currentRoom = GameState.instance()
                .getAdventurersCurrentRoom();
            
            if (!GameState.instance().getCharsInRoom(currentRoom)
                    .contains(i)) {
                System.out.println("The fight is on!");
            }
        } catch (NoCharacterException e) {
            System.out.println("There's no " + enemyName + " in here!");
        }
        return "yo mama";
    }
}
