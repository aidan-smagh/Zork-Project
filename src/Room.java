import java.util.ArrayList;

public class Room{


   String name;
   String desc;  //description
   
   ArrayList<Exit> exits;

    Room(String name, String desc){
       this.name = name;
       this.desc = desc;
       this.exits = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;

    }

    String describe(){

        if(GameState.instance().hasBeenVisited(this)){
        //if(GameState.hasbeenVisited(this){
        //return this.name+ " " +this.desc;
        
       return this.name;
        } 
        else{

        String roomInfo = this.name + "\n" + this.desc;
        
        for(Exit exit : exits){
    
            roomInfo += "\n" + exit.describe();
      }
      GameState.instance().visit(this);
    
      return roomInfo;
    }
   }


    public Room leaveby(String dir){
        for(Exit exit : exits){
            if(exit.getDir().equals(dir)){
                return exit.getDest();
         }
       }
        return null;    
    }
    

    public void addExit(Exit exit){
        exits.add(exit);
    }
    

    public static void main(String[] args){
    
    
      Room room1 = new Room("Living Room", "A cozy place with a fireplace.");
      Room room2 = new Room("Kitchen", "A spacious kitchen with a large table.");
      Room room3 = new Room("M's BedRoom", "Bed on the left, table on the right with 2 monitors.");
        Exit exitToKitchen = new Exit("north", room1, room2);
        Exit exitMBedroom = new Exit("north", room2, room3);
        Exit exitFromKitchen = new Exit("south", room2, room1);

        room1.addExit(exitToKitchen);
        room2.addExit(exitMBedroom);
        room2.addExit(exitFromKitchen);

        System.out.println(room1.describe());
        System.out.println(room2.describe()); 
      // System.out.println(room3.describe());
        Room nextRoom = room1.leaveby("north");
        System.out.println(nextRoom.describe());
   
        Room nextRoom2 = room2.leaveby("north");
        System.out.println(nextRoom2.describe());

   }
}

