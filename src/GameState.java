import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;



public class GameState {

    static GameState theinstance = null; 
    private Dungeon dungeon = null;           //dungeon
    private Room currentRoom = null;          //from Room
    private HashSet<Room> visited = null;

    private GameState() {
        // Singleton pattern
        dungeon = null;
        currentRoom = null;
        visited = new HashSet<>();
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


    void store(String saveName) throws Exception{
        try (PrintWriter pw = new PrintWriter(saveName)) {
            // Write the first line
            pw.println(dungeon.getTitle());
            pw.println("Zork II");
            //pw.println("Dungeon file: " + new File("files/farmer.zork").getAbsolutePath() + "\n");
            pw.print(new File("files/farmer.zork").getAbsolutePath() + "\n");
            pw.print("Room states:\n");
            for (Room room : visited) {
                pw.print(room.getName() + ":\n");
                pw.print("beenHere=true\n");
                pw.print("---\n");
            }
            // Write the ending delimiter
            pw.println("===");
            pw.print("Current room: " + currentRoom.getName() + "\n");
        }
        catch (Exception e) {
            System.err.println("Error loading the game: " + e.getMessage());
            System.exit(1);
        }
    }




    void restore(String saveFileName) throws Exception {
        
    try{
        BufferedReader reader = new BufferedReader(new FileReader(saveFileName));     
      //  System.out.println("Success1");
        // Step 2: Read and ignore the first line (informational version line)
        reader.readLine();
        reader.readLine();
        // Step 3: Read the second line to get the full path to the dungeon file
        String dungeonFilePath = reader.readLine();
        Dungeon saveddungeon = new Dungeon(dungeonFilePath);

        reader.readLine();

        String line;
        while (!(line = reader.readLine()).equals("===")) {
            //String roomName = line.substring(0, line.length() - 1); // Remove the colon at the end
            //Room room = dungeon.getRoom(roomName);
            reader.readLine();
            reader.readLine();

        }

        String currentRoomLine = reader.readLine();
    //    System.out.println(currentRoomLine);
        if (currentRoomLine.startsWith("Current room: ")) {
            String currentRoomName = currentRoomLine.substring("Current room: ".length());
           
          //  System.out.println(currentRoomName);

            Room currentsavedRoom = saveddungeon.getRoom(currentRoomName.trim());
          //  System.out.println(currentRoom);
            if (currentsavedRoom != null) {
                GameState.instance().setAdventurersCurrentRoom(currentsavedRoom);
            }
        }
    } catch (Exception e) {
            throw new RuntimeException(e);
    }
            //    } catch (Dungeon.IllegalDungeonFormatException e) {
    //        throw new RuntimeException(e);
    //    } catch (Room.NoRoomException e) {
    //        throw new RuntimeException(e);
    //    }

    }
    public class IllegalSaveFormatException extends Exception{

         public IllegalSaveFormatException(String e){
             super(e);
         }
     }

}
