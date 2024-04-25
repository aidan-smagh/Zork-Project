import java.util.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Interpreter{

//    public static void main(String args[]) {
    public static void main(String args[]) throws Room.NoRoomException, FileNotFoundException, Dungeon.IllegalDungeonFormatException, NoItemException, NoCharacterException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file name or file path (with .zork or .sav extension):");
        String fileName = scanner.nextLine();
        GameState.instance().setDungeonFileName(fileName);

        Dungeon dungeon = null;



        if (fileName.endsWith(".zork")) {
            // Load the dungeon from the .zork file
            try {
                dungeon = new Dungeon(fileName);
                GameState.instance().initialize(dungeon);
                System.out.println(dungeon.getEntry().describe());
                dungeon.charsInDungeon.add(GameState.instance().PLAYER);                

            } catch (Dungeon.IllegalDungeonFormatException | FileNotFoundException | Room.NoRoomException e) {
                System.err.println("Error loading the dungeon: " + e.getMessage());
                System.exit(1);
            }
        } else if (fileName.endsWith(".sav")) {
            // Restore the game state from the .sav file
            try {
                GameState.instance().restore(fileName);
                GameState.instance().getDungeon().charsInDungeon.add(GameState.instance().PLAYER);
                //Dungeon savedDungeon = GameState.instance().getDungeon();
                //GameState.instance().initialize(dungeon);
                System.out.println(GameState.instance().getAdventurersCurrentRoom().describe());
                String input = scanner.nextLine();
                Command command = CommandFactory.instance().parse(input);
                String result = command.execute();
                System.out.println(result);

            }catch (FileNotFoundException e) {
                System.err.println("Error restoring  game: " + e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.err.println("Error restoring the game: " + e.getMessage());
                //System.exit(1);
            } 
        } else {
            System.out.println("Invalid file format. Supported formats: .zork, .sav");
            //System.exit(1);
        }

        // Initialize the game state

        //Scanner scanner = new Scanner(System.in);

        while (true) {
            
            if (GameState.instance().PLAYER.getHP() == 0) {
                System.out.println("You have died");
                System.out.println("GAME OVER!");
                break;
            }
            
            String input = scanner.nextLine();
            
   
            if (input.equals("q")) {
                break;
            }
            
            Command command = CommandFactory.instance().parse(input);
            //direction
            //Command command = new Command(input);
            String result = command.execute();
            System.out.println(result);

        }

    }
}


  //      System.out.println("Welcome to my House");
  //      Scanner scanner = new Scanner(System.in);
//        System.out.println("Input: "); 

        //Dungeon
//        Dungeon dungeon = new Dungeon("files/farmer.zork");
        
  //      GameState.instance().initialize(dungeon);

    //    System.out.println("title " + dungeon.getTitle());
//       // System.out.println("Entry Room: " + dungeon.getEntry().getName());
     //   System.out.println("Description: " + dungeon.getEntry().describe());
//
//        //System.out.println(dungeon.getEntry().getName());
 //       System.out.println(dungeon.getEntry().describe());

      //  while(true){
           
     //       String input = scanner.next();
      
         //   if(input.equals("q")){
        //        break;
       //     }
        

        //direction
     //   Command command = CommandFactory.instance().parse(input);
       // Command command = new Command(input);
     //    String result = command.execute();
      //      System.out.println(result);
  //  }
  //  }
//}
//    private static Dungeon buildSampleDungeon(){
//    Room entry = new Room("M's room", "There is 2 monitors on the left, on the east there is a door..");

 //   Room hallway = new Room("Hallway", "A dimly lit hallway with doors on both sides.");
 //   Room kitchen = new Room("Kitchen", "A cozy kitchen with the smell of freshly baked bread.");
 //   Room library = new Room("Library", "Bookshelves line the walls, and a large desk sits in the center.");
 //   Room garden = new Room("Garden", "An enchanting garden with vibrant flowers and a fountain.");
    
   //   Room livingRoom = new Room("Living Room", "A spacious living room with comfortable furniture.");

  //    Dungeon dungeon = new Dungeon(entry, "MyHome- Dungeon");


    //Exits
    
    //exits
   //     entry.addExit("e", hallway);
   //     hallway.addExit("w", entry);
   //     hallway.addExit("n", kitchen);
   //     kitchen.addExit("s", hallway);
   //     hallway.addExit("e", library);
   //     library.addExit("w", hallway);
   //     library.addExit("n", livingRoom);
   //    livingRoom.addExit("s", library);
   //     hallway.addExit("s", garden);
   //     garden.addExit("n", hallway);


  //  Dungeon dungeon = new Dungeon(entry, "MyWorld- Dungeon");

    //    System.out.println("title " + dungeon.getTitle());
    //    System.out.println("Entry Room: " + dungeon.getEntry().getName());
   //     System.out.println( dungeon.getEntry().describe());
        
    //    return dungeon;
  //  }


