import java.util.Hashtable;

public class Dungeon{

    Hashtable<String,Room> rooms;
    Room entryRoom;
    String title;


    Dungeon(Room entryRoom, String title){
        this.rooms = new Hashtable<>();
        this.entryRoom = entryRoom;
        this.title = title;
    }

    public Room getEntry(){
        return entryRoom;
    }

    public String getTitle(){
        return title;
    }
    
    public void add(Room room){
        rooms.put(room.getName(), room);
    }

    public Room getRoom(String roomName){
        for(String key : rooms.keySet()){
            if(key.equals(roomName)){
                return rooms.get(key);
            }
        }
        return null;
    }



}
