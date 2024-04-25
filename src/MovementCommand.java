
class MovementCommand extends Command{

    private String dir;

    public MovementCommand(String dir){
        this.dir = dir;
    }

    String execute(){
  
    Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        //System.out.println("C - works- " + currentRoom);
        Room nextRoom = currentRoom.leaveby(dir);
        GameState.instance().movesMade++;
        if (nextRoom != null) {
        }

        return nextRoom.describe();

//        return "movement command for direction: " +dir;

    }


}
