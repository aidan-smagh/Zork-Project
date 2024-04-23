import java.io.*;
import java.util.*;
public class GameState {

    static GameState theinstance = null; 
    Dungeon dungeon = null;           
    Character PLAYER = null; //static Character representing the user (has HP, DEF, hurt messages)
    int playerScore = 0;  //score
    Item equippedItem = null;
    Room currentRoom = null;         
    HashSet<Room> visited = null;
    ArrayList<Item> inventory = null;
    Hashtable<Room, HashSet<Item>> roomContents = null;
    Hashtable<Room, HashSet<Character>> charsInRoom = null; //knows which chars are in which rooms
    private String dungeonFileName = null;

    private GameState() {
        // Singleton pattern
        dungeon = null;
        PLAYER = new Character("player",100,25);
        playerScore = 0;
        equippedItem = null;
        currentRoom = null;
        visited = new HashSet<>();
        inventory = new ArrayList<>();
        roomContents = new Hashtable<>();
        charsInRoom = new Hashtable<>();
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
     
    void store(String saveName) throws Exception{
        try (PrintWriter pw = new PrintWriter(saveName)) {
            // Write the first line
            pw.println(dungeon.getTitle());
            pw.println("Zork++");
            //pw.println("Dungeon file: " + new File("files/farmer.zork").getAbsolutePath() + "\n");
            pw.print(new File(this.dungeonFileName).getAbsolutePath() + "\n");
            pw.print("Room states:\n");
            for (Room room : visited) {
                pw.print(room.getName() + ":\n");
                pw.print("beenHere=true\n");
                if (GameState.instance().getItemsInRoom(room).isEmpty()) {
                  //if no items in room, check for NPCs  
                    if (GameState.instance().getCharsInRoom(room).isEmpty()) {
                     pw.println("---");
                     } else {
                         pw.println("NPCs: " + GameState.instance().getCharsInRoom(room));
                         pw.println("---");
                     }
                } else {
                    pw.println("Contents: " + GameState.instance().getItemsInRoom(room));
                                        
                    if (GameState.instance().getCharsInRoom(room).isEmpty()) {
                        pw.println("---");
                    } else {
                        pw.println("NPCs: " + GameState.instance().getCharsInRoom(room));
                        pw.println("---");
                    }
                } 
            }
            // Write the ending delimiter
            pw.println("===");
            pw.println("Adventurer:");
            pw.println("Current room: " + currentRoom.getName());
            if (inventory.isEmpty()) {
                pw.println(" ");
            } else {
                pw.print("Inventory: ");
                for (int i = 0; i < inventory.size(); i++) {
                    pw.print(inventory.get(i) + ",");            
                }

            }
                        
            pw.print("\nScore:" + playerScore +"\n");
            pw.println("Health:" + PLAYER.getHP());

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
                line2 = reader.readLine(); //throw away "---"
            }
             if (line2.startsWith("NPCS:")) {
                String charNames = line2.substring("NPCs: ".length());
                String exactName = charNames.substring(1, charNames.length()-1);
                reader.readLine();
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
        String nextL  = reader.readLine();
        
        if(nextL.startsWith("Inventory")){
        String itemsFormatted =  nextL.substring
            (11, nextL.length() - 1);
        //System.out.println(itemsFormatted);
        String theAnswer = itemsFormatted.replace(",", " ");
        String[] itemsByOne = itemsFormatted.split(",");
        for (String itemName : itemsByOne) {
            //System.out.println(itemName);
            GameState.instance().addToInventory(GameState.instance()
                .getDungeon().getItem(itemName));
        }
        }
 
        
        nextL = reader.readLine(); 
        if(nextL.contains("Score:")){  //restore score           
            String scoreInfo[] = nextL.split(":");
            String score = scoreInfo[1];
            GameState.instance().setScore(Integer.parseInt(score));

        }
        
      //  String line4 = reader.readLine();
      //  if(line4.startsWith("HealthPoints")){
      //      int healthPoints = Interger.parseInt(line4.substring(14));
      //      PLAYER.setHP(healthPoints);
      //  }

        nextL = reader.readLine();
         if(nextL.contains("Health:")){ //restore health
             String healthInfo[] = nextL.split(":");
             String health = healthInfo[1];
             GameState.instance().PLAYER.setHP(Integer.parseInt(health));            
         }
        
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    }
    ArrayList<Item> getInventory() {
        return inventory;
    }
    void addToInventory(Item item) {
        inventory.add(item);
    }
    void removeFromInventory(Item item) {
        inventory.remove(item);
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
        return roomContents.get(room);
    }
    
    void addItemToRoom(Item item, Room room) {
        roomContents.get(room).add(item);            
    }
    void removeItemFromRoom(Item item, Room room) {
        roomContents.get(room).remove(item); 
    }

    void addToScore(int n) {
        playerScore += n;
    } 
    //no getter for playerScore, it's public
    void setScore(int n) {
        playerScore = n;
    }
    //no getter for equppedItem, it's public 
    void setEquippedItem(Item i) {
        equippedItem = i;
    }
    
    void addCharToRoom(Character c, Room room) {       //these 3 methods are just replicas of the Item versions
        charsInRoom.get(room).add(c);
    }
    void removeCharFromRoom(Character c, Room room) {
         charsInRoom.get(room).remove(c);
    }
    HashSet<Character> getCharsInRoom(Room room) {
        //HashSet<Character> chars = charsInRoom.get(room);
        return charsInRoom.get(room);
    }

    void drop(Item i) { //new drop comd created AND executed, allows for multiple actions to be done with one user input, ex. some Item is hot and wounds you, and you also drop it at the same time
        DropCommand drop = new DropCommand(i.getPrimaryName());
        drop.execute();
    }
    
    void disappear(Item i) { //currently "untestable" w/ test main()s, but should work in-game. u can test but u have to manually input items an inventory and dungeon etc etc 
        GameState.instance().removeFromInventory(i);
        GameState.instance().removeItemFromRoom(i, GameState.instance().currentRoom);
        GameState.instance().getDungeon().dungeonItems.remove(i.getPrimaryName());  
    }
    void endGame() {
        System.exit(0);
    }
    public class IllegalSaveFormatException extends Exception{
         public IllegalSaveFormatException(String e){
             super(e);
         }
     }

    
   
}
