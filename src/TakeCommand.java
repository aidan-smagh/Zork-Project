
class TakeCommand extends Command{

    private String itemName;

    public TakeCommand(String itemName){
        this.itemName = itemName;
    }

    String execute(){
        return "item taken " + itemName;
    }

}
