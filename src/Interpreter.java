import java.util.*;
class Interpreter{

    public static void main(String args[]) {
        System.out.println("Welcome to my House");
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input: "); 

        //Dungeon
        Dungeon dungeon = buildSampleDungeon();
        
        GameState.instance().initialize(dungeon);

        while(true){
           
            String input = scanner.next();
      
            if(input.equals("q")){
                break;
            }
        

        //direction
        Command command = CommandFactory.instance().parse(input);
       // Command command = new Command(input);
         String result = command.execute();
            System.out.println(result);
    }
    }

    private static Dungeon buildSampleDungeon(){
    Room entry = new Room("M's room", "There is 2 monitors on the left, on the east there is a door..");

    Room hallway = new Room("Hallway", "A dimly lit hallway with doors on both sides.");
    Room kitchen = new Room("Kitchen", "A cozy kitchen with the smell of freshly baked bread.");
    Room library = new Room("Library", "Bookshelves line the walls, and a large desk sits in the center.");
    Room garden = new Room("Garden", "An enchanting garden with vibrant flowers and a fountain.");
    
      Room livingRoom = new Room("Living Room", "A spacious living room with comfortable furniture.");

      Dungeon dungeon = new Dungeon(entry, "MyHome- Dungeon");


    //Exits
    
    //exits
        entry.addExit("e", hallway);
        hallway.addExit("w", entry);
        hallway.addExit("n", kitchen);
        kitchen.addExit("s", hallway);
        hallway.addExit("e", library);
        library.addExit("w", hallway);
        library.addExit("n", livingRoom);
        livingRoom.addExit("s", library);
        hallway.addExit("s", garden);
        garden.addExit("n", hallway);


  //  Dungeon dungeon = new Dungeon(entry, "MyWorld- Dungeon");

    //    System.out.println("title " + dungeon.getTitle());
    //    System.out.println("Entry Room: " + dungeon.getEntry().getName());
        System.out.println( dungeon.getEntry().describe());
        
        return dungeon;
    }



}

