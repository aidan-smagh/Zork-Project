
class DropCommand extends Command{

    private String itemName;

    public DropCommand(String itemName){
        this.itemName = itemName;
    }

    String execute(){
        return "item dropped! " + itemName;
    }

}
