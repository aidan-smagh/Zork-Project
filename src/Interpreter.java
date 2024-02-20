import java.util.*;
class Interpreter{

    public static void main(String args[]) {
        System.out.println("This will soon be Zork I!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: "); 

        //Dungeon
        Dungeon dungeon = buildSampleDungeon();

        while(true){
           
            String input = scanner.nextLine();
      
            if(input.equals("q")){
                break;
            }

        }
    }

    private static Dungeon buildSampleDungeon(){
    Room entry = new Room("M's room", "There is 2 monitors on the left, on the east there is a door..");

    Room hallway = new Room("Hallway", "A dimly lit hallway with doors on both sides.");
    Room kitchen = new Room("Kitchen", "A cozy kitchen with the smell of freshly baked bread.");
    Room library = new Room("Library", "Bookshelves line the walls, and a large desk sits in the center.");
    Room garden = new Room("Garden", "An enchanting garden with vibrant flowers and a fountain.");
 

    //Exits
    Exit entryToHallway = new Exit("east", entry, hallway);
    Exit hallwayToEntry = new Exit("west", hallway, entry);
    Exit hallwayToKitchen = new Exit("north", hallway, kitchen);
    Exit kitchenToHallway = new Exit("south", kitchen, hallway);
    Exit hallwayToLibrary = new Exit("east", hallway, library);
    Exit libraryToHallway = new Exit("west", library, hallway);
    Exit hallwayToGarden = new Exit("south", hallway, garden);
    Exit gardenToHallway = new Exit("north", garden, hallway);

    entry.addExit(entryToHallway);
    hallway.addExit(hallwayToEntry);
    hallway.addExit(hallwayToKitchen);
    kitchen.addExit(kitchenToHallway);
    hallway.addExit(hallwayToLibrary);
    library.addExit(libraryToHallway);
    hallway.addExit(hallwayToGarden);
    garden.addExit(gardenToHallway);


    Dungeon dungeon = new Dungeon(entry, "MyWorld- Dungeon");

        System.out.println("title " + dungeon.getTitle());
        System.out.println("Entry Room: " + dungeon.getEntry().getName());
        System.out.println("Description: " + dungeon.getEntry().describe());
        
        return dungeon;
    }
}

