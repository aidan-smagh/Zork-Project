import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class GameState {

    static GameState theinstance = null; 
    Dungeon dungeon = null;           //dungeon
    Room currentRoom = null;          //from Room
    HashSet<Room> visited = null;
    ArrayList<Item> inventory = null;
    Hashtable<Room, HashSet<Item>> roomContents = null;
    private String dungeonFileName = null;

    private GameState() {
        // Singleton pattern
        dungeon = null;
        currentRoom = null;
        visited = new HashSet<>();
        inventory = new ArrayList<>();
        roomContents = new Hashtable<>();
        this.dungeonFileName = dungeonFileName;
    }

    public static GameState instance() {
        if (theinstance == null) {
            theinstance = new GameState();
        }
        return theinstance;
    }

    public void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.currentRoom = dungeon.getEntry();

    }

    public Room getAdventurersCurrentRoom() {
        return currentRoom;
    }

    public void setAdventurersCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }


    void visit(Room room) {
        visited.add(room);
    }

    boolean hasBeenVisited(Room room) {
        return visited.contains(room);
    }
    public void setDungeonFileName(String filename) {
        this.dungeonFileName = filename;
    }
    
//    public describeFull(){
        
  //  }
  
    void store(String saveName) throws Exception{
        try (PrintWriter pw = new PrintWriter(saveName)) {
            // Write the first line
            pw.println(dungeon.getTitle());
            pw.println("Zork III");
            //pw.println("Dungeon file: " + new File("files/farmer.zork").getAbsolutePath() + "\n");
            pw.print(new File(this.dungeonFileName).getAbsolutePath() + "\n");
            pw.print("Room states:\n");
            for (Room room : visited) {
                pw.print(room.getName() + ":\n");
                pw.print("beenHere=true\n");
                if (GameState.instance().getItemsInRoom(room).isEmpty()) {
                    pw.println("---");
                } else {

                    pw.println("Contents: " + GameState.instance()
                        .getItemsInRoom(room));
                    pw.println("---");
                }
            }
            // Write the ending delimiter
            pw.println("===");
            pw.println("Adventurer:");
            pw.print("Current room: " + currentRoom.getName() + "\n");
            if (inventory.isEmpty()) {
                pw.println(" ");
            } else {
                pw.print("Inventory: ");
                for (int i = 0; i < inventory.size(); i++) {
                    pw.print(inventory.get(i) + ",");
            
                }
            }
        }
        catch (Exception e) {
            System.err.println("Error loading the game: " + e.getMessage());
            System.exit(1);
        }
    }




    void restore(String saveFileName) throws Exception {
        
    try{
        BufferedReader reader = new BufferedReader
            (new FileReader(saveFileName));     
        //System.out.println("Success1");
        // Step 2: Read and ignore the first line 
        //(informational version line)
        reader.readLine(); //throw away James Farmer Hall
        reader.readLine(); //throw away ZorkIII
        // Step 3: Read the second line to get 
        //the full path to the dungeon file
        String dungeonFilePath = reader.readLine();
        Dungeon savedDungeon = new Dungeon(dungeonFilePath);
        GameState.instance().initialize(savedDungeon);
        //System.out.println(savedDungeon.getTitle());
        reader.readLine(); //throw away "Room states:"
         
        String line;
        while (!(line = reader.readLine()).equals("===")) {
            
            //get rid of the colon at the end
            String roomName = line.substring(0, line.length() - 1);
            Room room = savedDungeon.getRoom(roomName);

            reader.readLine(); //throw away "beenHere = true"
            //System.out.println(roomName);
             
            String line2 = reader.readLine();          
            if(line2.startsWith("Contents:")) {
                String itemNames = line2.substring("Contents: ".length());
                String exactName = itemNames.substring
                    (1, itemNames.length()-1);
                //System.out.println(exactName + "your mom");
                //System.out.println(" ");
                reader.readLine(); //throw away "---"
            } else if (line2.startsWith("---")) {
                //do nothing
            }
        }
        reader.readLine(); //throw away "Adventurer"
        String currentRoomLine = reader.readLine();
        String currentRoomFitted = currentRoomLine.substring
            (14, currentRoomLine.length());
        //System.out.println("Current room: " +currentRoomFitted);
        
           
        Room currentSavedRoom = savedDungeon.getRoom(currentRoomFitted);
        //setAdventurersCurrentRoom(currentSavedRoom);
        this.currentRoom = currentSavedRoom;
          //  System.out.println(currentRoom);
            if (currentSavedRoom != null) {
                GameState.instance().setAdventurersCurrentRoom
                    (currentSavedRoom);
            }
        //read in the inventory 
        String inventoryItems = reader.readLine();
        String itemsFormatted =  inventoryItems.substring
            (11, inventoryItems.length() - 1);
        //System.out.println(itemsFormatted);
        String theAnswer = itemsFormatted.replace(",", " ");
        String[] itemsByOne = itemsFormatted.split(",");
        for (String itemName : itemsByOne) {
            //System.out.println(itemName);
            GameState.instance().addToInventory(GameState.instance()
                .getDungeon().getItem(itemName));
        } 
        
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    }
    ArrayList<Item> getInventory() {
        return inventory;
    }
    void addToInventory(Item item) {
        GameState.instance().inventory.add(item);
    }
    void removeFromInventory(Item item) {
        GameState.instance().inventory.remove(item);
    }
    Item getItemInVicinityNamed(String name) throws NoItemException { //in progress
        String itemName = name;
        for (Item item : inventory) {
           if (item.aliases.contains(itemName) || item.getPrimaryName().equalsIgnoreCase(itemName)){
                return item;
            }
        }         //this line below accesses currentRoom's Items to search through
        HashSet<Item> items = GameState.instance().roomContents.get(GameState.instance().currentRoom);
        for (Item item : items) {
          if (item.aliases.contains(itemName) || item.getPrimaryName().equalsIgnoreCase(itemName)){
           // if (item.aliases.contains(itemName)) {
                return item;
            }
        }
        throw new NoItemException();
    }
    Item getItemFromInventoryNamed(String name) throws NoItemException {
        //loop so we can use this as a getter for a specific item
        String itemName = name;
        for (Item item : inventory) {
            if (item.aliases.contains(itemName)) {
                return item;
            }             
        }  
        throw new NoItemException();
    }
    HashSet<Item> getItemsInRoom(Room room) {
        return GameState.instance().roomContents.get(room);
    }
    
    void addItemToRoom(Item item, Room room) {
        GameState.instance().roomContents.get(room).add(item);
        /*loop through items and if its the correct item, add it to
        the set of items in the adventurers current room.
        for (Item itemAdded : inventory) {
            if (itemAdded.getPrimaryName().equals(item.getPrimaryName())) {
                GameState.instance().getAdventurersCurrentRoom().add(itemAdded);
            }
        }*/
        
    }
    void removeItemFromRoom(Item item, Room room) {
          GameState.instance().roomContents.get(room).remove(item); 
    }
    public class IllegalSaveFormatException extends Exception{

         public IllegalSaveFormatException(String e){
             super(e);
         }
     }

}
