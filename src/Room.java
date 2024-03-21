import java.util.Hashtable;
import java.util.Scanner;
import java.util.HashSet;

public class Room{


   private String name = null;
   private String desc= null;  //description
   
   private Hashtable<String,Exit> exits = null;
   private HashSet<Item> itemsInRoom = null;  

   public Room(String name, String desc){
       this.name = name;
       this.desc = desc;
       this.exits = new Hashtable<>();
       this.itemsInRoom = new HashSet<Item>(); //to be removed, room items will be accessed through GameState 
    }



    Room (Scanner scanner) throws NoRoomException, NoItemException{

        this.name = scanner.nextLine();
        if(this.name.equals("===")){
            throw new NoRoomException();
        }
        //checking for start of items section
        String check = scanner.next();
        //System.out.println(check);
        if(check.equals("Contents:")) {
            String itemName = scanner.nextLine().trim();
            String[] itemNames = itemName.split(",");
            for (String item : itemNames) {
                //System.out.println(item);
                //System.out.println("");
                check = "";
            }
        } else {
            this.desc += check;
        
        } /*removed else condition that threw NoItemException,
            rooms can be hydrated with *or without* items -
            'Contents:' not there = room w/ no items */
        

        this.exits = new Hashtable<String, Exit>();

            this.desc = check + "";
            String temp = "";
            temp = scanner.nextLine();
            
            while(!temp.equals("---")) {
                this.desc += temp + "\n";
                temp = scanner.nextLine();
            }
            temp = "";

    }





    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;

    }

    String describe() {
        String roomInfo = "";
      
      //i/Room exitss
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
    HashSet<Item> getContents() {
        return this.itemsInRoom; 
    }
    
    //Item getItemNamed(String name) { //still working
        //return this.itemsInRoom.contains(name);
   // }

    void add(Item item) {
        this.itemsInRoom.add(item);
    }
    void remove(Item item) {
        this.itemsInRoom.remove(item);
    }
    public static class NoRoomException extends Exception{

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

