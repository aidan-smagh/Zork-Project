import java.util.Hashtable;

public class Room{


   private String name = null;
   private String desc= null;  //description
   
   private Hashtable<String,Exit> exits = null;

   public Room(String name, String desc){
       this.name = name;
       this.desc = desc;
       this.exits = new Hashtable<>();
    }

    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;

    }

    String describe() {
        String roomInfo = "";
        if (!GameState.instance().hasBeenVisited(this)) {
            roomInfo = this.name + "\n" + this.desc;
            GameState.instance().visit(this);
        }
        else{
            roomInfo = this.name;
        }
        for (Exit exit : exits.values()) {
            roomInfo += "\n" + exit.describe();
        }
        return roomInfo;
    }


    Room leaveby(String dir) {
        if (exits.containsKey(dir)) {
            Exit exit = exits.get(dir);
            GameState.instance().setAdventurersCurrentRoom(exit.getDest());

            //System.out.println("works- " + this);
            //System.out.println("works!!!");
            return exit.getDest();
        } else {
            System.out.println("No Exit in this direction");
            return this;
        }
    }
    

    public void addExit(String dir, Room destRoom) {
    Exit exit = new Exit(dir, this, destRoom);
    exits.put(dir, exit);
    //System.out.println("Exit added: " + dir + " to " + destRoom.getName());
}
    

   // public static void main(String[] args){
    
    
   //   Room room1 = new Room("Living Room", "A cozy place with a fireplace.");
    //  Room room2 = new Room("Kitchen", "A spacious kitchen with a large table.");
    //  Room room3 = new Room("M's BedRoom", "Bed on the left, table on the right with 2 monitors.");
      //  Exit exitToKitchen = new Exit("north", room1, room2);
      //  Exit exitMBedroom = new Exit("north", room2, room3);
      //  Exit exitFromKitchen = new Exit("south", room2, room1);

       // room1.addExit(exitToKitchen);
       // room2.addExit(exitMBedroom);
      //  room2.addExit(exitFromKitchen);

       // System.out.println(room1.describe());
       // System.out.println(room2.describe()); 
      // System.out.println(room3.describe());
       // Room nextRoom = room1.leaveby("north");
       // System.out.println(nextRoom.describe());
   
  //      Room nextRoom2 = room2.leaveby("north");
    //    System.out.println(nextRoom2.describe());

  // }
}

