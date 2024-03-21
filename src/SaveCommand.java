
class SaveCommand extends Command{

    private String saveFileName;

    public SaveCommand(String saveFileName){
        this.saveFileName = saveFileName;
    }

    String execute(){
        return "Game saved successfully to file " + saveFileName;
    }



}
