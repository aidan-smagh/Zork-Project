import java.util.HashSet;

public class GameState {

    static GameState theinstance = null; 
    private Dungeon dungeon;           //dungeon
    private Room currentRoom;          //from Room
    private HashSet<Room> visited;

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

    public void visit(Room room) {
        visited.add(room);
    }

    public boolean hasBeenVisited(Room room) {
        return visited.contains(room);
    }
}

