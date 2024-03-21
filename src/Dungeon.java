import java.util.Hashtable;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Dungeon{

    Hashtable<String,Room> rooms = null;
    Room entryRoom = null;
    private String title = null;
    Hashtable<String, Item> dungeonItems = new Hashtable<>();;

    public Dungeon(Room entryRoom, String title){
        this.rooms = new Hashtable<>();
        this.entryRoom = entryRoom;
        this.title = title;
        this.dungeonItems = dungeonItems;
        rooms.put(entryRoom.getName(), entryRoom);
    }

//Dungeon scanner:
//

    Dungeon(String filename) throws IllegalDungeonFormatException, FileNotFoundException, Room.NoRoomException, NoItemException {
        GameState.instance().setDungeon(this); // Set the Dungeon in GameState

        this.rooms = new Hashtable<>();
        Scanner scanner = new Scanner(new File(filename));

        // Read the first line as the dungeon's title
        if (scanner.hasNextLine()) {
            this.title = scanner.nextLine();
            String versionLine = scanner.nextLine();
            if (!versionLine.equals("Zork III")) {
                throw new IllegalDungeonFormatException("Invalid version line: " + versionLine);
            }
            scanner.nextLine(); // "==="
            scanner.nextLine(); //Items: <- changed from 'Rooms:' as items are now at top of file
            

        
         
          
            while (scanner.hasNextLine()) { //while condition changed from 'true', functionality remains 
                try {
                    Item item = new Item(scanner);
                    this.add(item);                               
                } catch (Exception e) {break;} //items done, break to start hydrating rooms                      
            }            

            scanner.nextLine(); //consume 'Rooms:' 
            this.entryRoom = new Room(scanner); //create entryRoom
            rooms.put(entryRoom.getName(), entryRoom); //set entryRoom
            while (scanner.hasNextLine()) {
                try {
                Room room = new Room(scanner);               
                rooms.put(room.getName(), room);                
                } catch (Exception e2) {break;} //rooms done, break to start hydrating exits
            }
          
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                try {                    
                    Exit exit = new Exit(scanner);                                                           
                    exit.getSrc().addExit(exit.getDir(), exit.getDest());                    
                    } catch (Exception e3) {break;}
            }
                
                
        }    
    }
    
    







//            while (scanner.hasNextLine()) {
//
//                Room room = new Room(scanner);
//                rooms.put(room.getName(), room);
//
//                if (scanner.hasNextLine() && scanner.nextLine().equals("===")) {
//                    throw new Room.NoRoomException(); // Throw exception for the last "==="
//                }
//
//            }

//            Room room = new Room(scanner);
//            rooms.put(room.getName(), room);
//            Room room2 = new Room(scanner);
//            rooms.put(room.getName(), room);
//            Room room3 = new Room(scanner);
//            rooms.put(room.getName(), room);
       
   




    public Room getEntry(){
        return entryRoom;
    }

    public String getTitle(){
        return title;
    }
    
    public void add(Room room){
        rooms.put(room.getName(), room);
   
        //System.out.println("room added");
    }

    public Room getRoom(String roomName){
        for(String key : rooms.keySet()){
            if(key.equals(roomName)){
                return rooms.get(key);
            }
        }
        return null;
    }
    
    public void add (Item item) {
        this.dungeonItems.put(item.getPrimaryName(), item);
        /* unsure if this will override the other in-file add() for Rooms,
           I asssume it won't - it will call whichever method based
           on if it was passed a Room or Item */
    }

   // public Room getItem(String itemName) {} //updated GameState needed 
    public class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }








    public static void main(String[] args) throws FileNotFoundException, IllegalDungeonFormatException, NoItemException {

        // Instantiate Dungeon object with the hardcoded filename
        try {
            Dungeon dungeon = new Dungeon("files/farmerv3.zork");
            System.out.println("Dungeon Title: " + dungeon.getTitle());
            System.out.println("Rooms in the Dungeon:");
        for (String roomName : dungeon.rooms.keySet()) {
            System.out.println(roomName);
        }

        System.out.println();
            for (Room room : dungeon.rooms.values()) {
                System.out.println(room.describe());
                System.out.println("---");

            }

          /*  for (Room room : dungeon.rooms.values()) {
                System.out.println("Exits from " + room.getName() + ":");
               // for (Exit exit : room.exits.values()) {
                 //   System.out.println(exit.describe());
               // }
                System.out.println("---");
            } */
            for (Item item : dungeon.dungeonItems.values()) { //prints each item's primary name
                System.out.println(item.getPrimaryName());
                System.out.println("---");
            }  



        } catch (IllegalDungeonFormatException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Room.NoRoomException e) {
            throw new RuntimeException(e);
        }
    }


  //  public static void main(String[] args) {
        // Create a dungeon with an entry room and a title
      //  Room entryRoom = new Room("M's bedroom", "This is the starting room.");
     //   Dungeon dungeon = new Dungeon(entryRoom, "My Dungeon");

        // Add some rooms to the dungeon
      //  Room room1 = new Room("Hallway", "This is room 1.");
     //   Room room2 = new Room("Kitchen", "This is room 2.");
    //    dungeon.add(room1);
     //   dungeon.add(room2);

        // Print information about the dungeon
      //  System.out.println("Dungeon Title: " + dungeon.getTitle());
     //   System.out.println("Entry Room: " + dungeon.getEntry().getName());
//
//        System.out.println("Rooms in the Dungeon:");
//        for (String roomName : dungeon.rooms.keySet()) {
//            System.out.println(roomName);
//        }


        // Print the contents of the hashtable (both key and value)
        // Print the contents of the hashtable (both key and value)
     //   System.out.println("Rooms in the Dungeon:");
     //   for (Map.Entry<String, Room> entry : dungeon.rooms.entrySet()) {
      //      String key = entry.getKey();
      //      Room room = entry.getValue();
     //       System.out.println("Key: " + key + ", Value: " + room);
    //    }


  //  }

}
