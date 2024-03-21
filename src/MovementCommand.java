
class MovementCommand extends Command{

    private String dir;

    public MovementCommand(String dir){
        this.dir = dir;
    }

    String execute(){
  
        return "movement command for direction: " +dir;

    }


}
