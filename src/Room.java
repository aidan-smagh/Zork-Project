import java.util.Hashtable;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.*;
public class Room{


   private String name = null;
   private String desc= null;  //description
   
   private Hashtable<String,Exit> exits = null;


   public Room(String name, String desc){
       this.name = name;
       this.desc = desc;
       this.exits = new Hashtable<>();
    }



    Room (Scanner scanner) throws NoRoomException, NoItemException{
        
        this.name = scanner.nextLine();
                
        this.exits = exits;
        if(this.name.equals("===")){
            throw new NoRoomException();
        }
        //checking for start of items section
        String check = scanner.next();
        HashSet<Item> roomItems = new HashSet<>();
        if (check.equals("Contents:")) {            
            String itemNames = scanner.next(); 
            String[] items = itemNames.split(",");
            for (String itemName : items) {
                Item i = GameState.instance().getDungeon().getItem(itemName);
                roomItems.add(i);                
            }
            check = scanner.next();
            
        } else {
            this.desc += check;
        
        } 
        

        exits = new Hashtable<String, Exit>();

            this.desc = check + "";
            String temp = "";
            temp = scanner.nextLine();
            
            while(!temp.equals("---")) {
                this.desc += temp + "\n";
                temp = scanner.nextLine();
            }
            temp = "";
        if (GameState.instance().getDungeon().entryRoom == null) {
            GameState.instance().getDungeon().entryRoom=this;
        } 
        GameState.instance().roomContents.put(this, roomItems);
    }





    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;

    }

    public String describeFull(){
    
    String roomInfo = "";
    String itemInfo = "";
    HashSet<Item> items = GameState.instance().roomContents.get(GameState.instance().currentRoom);
    Iterator<Item> iterator = items.iterator();
        roomInfo = this.name + "\n" + this.desc;
        while (iterator.hasNext()) {
            itemInfo +=  "\nThere is a " + iterator.next().getPrimaryName() + " here.";
        }
        GameState.instance().visit(this);
        for (Exit exit : exits.values()) {
                 roomInfo += "\n" + exit.describe();
             }
         return roomInfo + itemInfo;
    }

    String describe() {
        String roomInfo = "";
        String itemInfo = "";
        HashSet<Item> items = GameState.instance().roomContents.get(GameState.instance().currentRoom);
        Iterator<Item> iterator = items.iterator();
      //i/Room exitss
        if (!GameState.instance().hasBeenVisited(this)) {            
            roomInfo = this.name + "\n" + this.desc;                
            while (iterator.hasNext()) {    
                itemInfo +=  "\nThere is a " + iterator.next().getPrimaryName() + " here.";
            }            
        GameState.instance().visit(this);            
        } else {
            roomInfo = this.name;
            while (iterator.hasNext()) {
                itemInfo += "\nThere is a " + iterator.next().getPrimaryName() + " here.";
            }
        }
        for (Exit exit : exits.values()) {
            roomInfo += "\n" + exit.describe();
        }
        return roomInfo+itemInfo;
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
        return GameState.instance().roomContents.get(this);
    }
    
    Item getItemNamed(String name) throws NoItemException {
        HashSet<Item> items = GameState.instance().roomContents.get(GameState.instance().currentRoom);
        for (Item item : items) {
            if (item.goesBy(name)) {
                return item;
            }             
        }
        throw new NoItemException();
    }
    
    

    void add(Item item) {
        GameState.instance().roomContents.get(GameState.instance().currentRoom).add(item);        
    }
    void remove(Item item) {
        GameState.instance().roomContents.get(GameState.instance().currentRoom).remove(item);
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

