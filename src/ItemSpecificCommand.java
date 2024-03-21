
class ItemSpecificCommand extends Command{

    private String verb;
    private String noun;


    public ItemSpecificCommand(String verb, String noun){
        this.verb = verb;
        this.noun = noun;
    }

    String execute(){
        return "verb and noun";
    }


}
