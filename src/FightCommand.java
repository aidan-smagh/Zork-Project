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
            Character i = GameState.instance().getDungeon()
                .getCharacter(enemyName);
            Room currentRoom = GameState.instance()
                .getAdventurersCurrentRoom();
            //grab the character that is in the room
            if (!GameState.instance().getCharsInRoom(currentRoom)
                    .contains(i)) {
                System.out.println("The fight is on!");
            }
            for (Item item : GameState.instance().inventory) {
                System.out.println("Item name is " + item.getPrimaryName());
            }
        } catch (NoCharacterException e) {
            System.out.println("There's no " + enemyName + " in here!");
        }
        return "yo mama";
    }
}
