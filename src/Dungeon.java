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
    Hashtable<String, Character> dungeonCharacters = new Hashtable<>();
    public Dungeon(Room entryRoom, String title){
        this.rooms = new Hashtable<>();
        this.entryRoom = entryRoom;
        this.title = title;
        this.dungeonItems = dungeonItems;
        this.dungeonCharacters = dungeonCharacters;
        rooms.put(entryRoom.getName(), entryRoom);
    }

//Dungeon scanner:
//

    Dungeon(String filename) throws IllegalDungeonFormatException, FileNotFoundException, Room.NoRoomException, NoItemException, NoCharacterException {
        GameState.instance().setDungeon(this); // Set the Dungeon in GameState
        this.rooms = new Hashtable<>();
        Scanner scanner = new Scanner(new File(filename));

        // Read the first line as the dungeon's title
        if (scanner.hasNextLine()) {
            this.title = scanner.nextLine();
            String versionLine = scanner.nextLine();
            if (!versionLine.equals("Zork++")) {
                throw new IllegalDungeonFormatException("Invalid version! Required: 'Zork++'" );
            }
            System.out.println(scanner.nextLine()); // "==="
            System.out.println(scanner.nextLine()); // 

            /*while (scanner.hasNextLine()) {
                try {
                    Character character = new Character(scanner); 
                    this.add(character);        //character hydration
                } catch (Exception e4) {break;}
            } */

           //System.out.println( scanner.nextLine()); //consume 'Items:'
            while (scanner.hasNextLine()) {  
                try {                    
                    Item item = new Item(scanner);
                    this.add(item);                               
                } catch (Exception e) {break;} //items done, break to start hydrating rooms                      
            }         
    
            System.out.println(scanner.nextLine()); //consume 'Rooms:'                      
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
    public Room getRoom(String roomName) throws Room.NoRoomException {        
        for(Room room : GameState.instance().getDungeon().rooms.values()){                                 
            if(room.getName().equals(roomName)){              
               return room;
            }
        }
        throw new Room.NoRoomException();                
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
        throw new NoItemException(); //throws if item not found   
    }

    public void add(Character c) {
        this.dungeonCharacters.put(c.getName(), c);
    }
    public Character getCharacter(String charName) throws NoCharacterException {
        for (Character c : GameState.instance().getDungeon().dungeonCharacters.values()) {
            if (c.getName().equals(charName)) {
                return c;
            }
        } 
        throw new NoCharacterException(); //throws if character not found
    }
    
    public class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }








    public static void main(String[] args) throws FileNotFoundException, IllegalDungeonFormatException, NoItemException, NoCharacterException {

        // Instantiate Dungeon object with the hardcoded filename
        try {
            Dungeon dungeon = new Dungeon("../files/DualLabs.zork");
            System.out.println("Dungeon Title: " + dungeon.getTitle());
            System.out.println("Rooms in the Dungeon:");
        for (String roomName : dungeon.rooms.keySet()) {
            System.out.println(roomName);
        }
        /*
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
            

            System.out.println("Chars in the Dungeon:");
            for (Character c : dungeon.dungeonCharacters.values()) { //prints each item's primary name
                Character testChar = dungeon.getCharacter(c.getName());
                System.out.println(testChar.getName());
                System.out.println("---");
            }  



        } catch (IllegalDungeonFormatException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Room.NoRoomException e) {
            throw new RuntimeException(e);
        }
    }


 

}
