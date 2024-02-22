import java.util.Hashtable;

public class Dungeon{

    Hashtable<String,Room> rooms;
    Room entryRoom;
    private String title;


    public Dungeon(Room entryRoom, String title){
        this.rooms = new Hashtable<>();
        this.entryRoom = entryRoom;
        this.title = title;

        rooms.put(entryRoom.getName(), entryRoom);
    }

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

//}
