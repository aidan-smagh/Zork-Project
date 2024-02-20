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
        Dungeon dungeon = new Dungeon(entry, "Sample Dungeon");

        System.out.println("title " + dungeon.getTitle());
        System.out.println("Entry Room: " + dungeon.getEntry().getName());
        System.out.println("Description: " + dungeon.getEntry().describe());
        
        return dungeon;
    }
}

