class EquipCommand extends Command {
    
    private String equippedItemName;

    public EquipCommand(String equippedItemName) {
    
        this.equippedItemName = equippedItemName;

    }
    String execute() {
        if (equippedItemName.isEmpty()) {
            return "Equip what?";
        }
        String nameOfEquipped = "";
        try {
            Item item = GameState.instance().getDungeon()
                .getItem(equippedItemName);
            nameOfEquipped = item.getPrimaryName();
            GameState.instance().setEquippedItem(GameState.instance()
                    .getItemFromInventoryNamed(nameOfEquipped));    
        } catch (NoItemException e) {
        }
        if (nameOfEquipped.isEmpty()) {
            return "...";
        } else {
            return equippedItemName + " has been equipped!";
        }
    }
}
