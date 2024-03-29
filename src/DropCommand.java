
class DropCommand extends Command{

    private String itemName;

    public DropCommand(String itemName){
        this.itemName = itemName;
    }

    String execute() {
        if (this.itemName.isEmpty()) {
            System.out.println("Drop what?");
    }
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        
        if (itemName.equals("all")) {
            GameState.instance().inventory.clear();
            return "You dropped everything in your bag.";
        }

        String nameOfDropped = "";
        try {
            Item i = GameState.instance().getDungeon().getItem(this.itemName);
            nameOfDropped = i.getPrimaryName();
            GameState.instance().removeFromInventory(i);
            GameState.instance().addItemToRoom(i, GameState.instance().getAdventurersCurrentRoom());
        } catch (NoItemException e) {
            System.out.println("There is no " + this.itemName + " to drop!");
        }
        if (nameOfDropped.isEmpty()) {
            return "...";
        } else {
            return nameOfDropped + " dropped";
        }
    }
}
