import java.util.Hashtable;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Dungeon{

    Hashtable<String,Room> rooms = null;
    Room entryRoom = null;
    private String title = null;
    Hashtable<String, Item> dungeonItems = new Hashtable<>();

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
                throw new IllegalDungeonFormatException("Invalid version! Required: 'Zork III'" );
            }
            scanner.nextLine(); // "==="
            scanner.nextLine(); //Items: <- changed from 'Rooms:' as items are now at top of file 
         
            while (scanner.hasNextLine()) {  
                try {                    
                    Item item = new Item(scanner);
                    this.add(item);                               
                } catch (Exception e) {break;} //items done, break to start hydrating rooms                      
            }  
           
            scanner.nextLine(); //consume 'Rooms:'           
           
            while (scanner.hasNextLine()) {
                try {                
                Room room = new Room(scanner);                     
                this.add(room);                                                   
                } catch (Exception e2) {break;} //rooms done, break to start hydrating exits
            }         
           scanner.nextLine(); //consume Exits:
 
            while (scanner.hasNextLine()) {
                try {                                        
                    Exit exit = new Exit(scanner);                  
                    exit.getSrc().addExit(exit.getDir(), exit.getDest());
                    } catch (Exception e3) {break;}
            }                                
        }    
    }
    

    public Room getEntry(){
        return entryRoom;
    }

    public String getTitle(){
        return title;
    }
    
    public void add(Room room){
        GameState.instance().getDungeon().rooms.put(room.getName(), room);
    }

    public Room getRoom(String roomName) {        
        for(Room room : GameState.instance().getDungeon().rooms.values()){
                                 
            if(room.getName().equals(roomName)){              
               return room;
            }
        }
        System.out.println("room not found, set to null"); 
        return null;
    }
    
    public void add (Item item) {
        this.dungeonItems.put(item.getPrimaryName(), item);
    }

    public Item getItem(String itemName) throws NoItemException {
        String name = itemName;        
        for (Item item : GameState.instance().getDungeon().dungeonItems.values()) {
            if (item.goesBy(itemName)) {
                return item;
            }
        }
        throw new NoItemException(); //loops ends without returning = no item found   
    }
  
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
            System.out.println("Items in the Dungeon:");
            for (Item item : dungeon.dungeonItems.values()) { //prints each item's primary name
                Item testItem = dungeon.getItem(item.getPrimaryName());
                System.out.println(testItem.getPrimaryName());
                System.out.println("---");
            }  



        } catch (IllegalDungeonFormatException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Room.NoRoomException e) {
            throw new RuntimeException(e);
        }
    }


 

}
