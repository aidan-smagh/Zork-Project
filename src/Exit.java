import java.util.Scanner;

class Exit{

    private String dir = null;
    private Room dest = null;  //room exiting to
    private Room src = null; //rom currently in

    Exit(String dir, Room src, Room dest){
        this.dir = dir;
        this.src = src;
        this.dest = dest;
    }



    public Exit(Scanner scanner) throws Exception{
        Dungeon dungeon = GameState.instance().getDungeon();
        String temp = scanner.nextLine();
        
        if(temp.equals("===")){
            throw new NoExitException();
        }
        this.src = dungeon.getRoom(temp);
        this.dir = scanner.nextLine();
        this.dest = dungeon.getRoom(scanner.nextLine());
        scanner.nextLine();
    }





    
    String describe(){
        return "You can go " + this.dir + " to " + this.dest.getName();
    } 

    public String getDir(){
        return dir;
    }

    public Room getSrc(){
        return src;
    }

    public Room getDest(){
        return dest;
    }

    public class IllegalSaveFormatException extends Exception{

        public IllegalSaveFormatException(String e){
            super(e);

        }
    }

    public class NoExitException extends Exception{
}


//    public static void main(String[] args){
  //      Room entry = new Room("M's room", "This is my room");
    //    Room hall = new Room("Hall", "This is the hall");

        //Exit
    //    Exit exit = new Exit("n", entry, hall);
        //Exit exit2 = new Exit("west", entry, ...(//wall);
        
    //    System.out.println("Exit Dir: " + exit.getDir());
    //    System.out.println("Src Room: " + exit.getSrc().getName());
     //   System.out.println("Des: " + exit.getDest().getName());
     //   System.out.println("Exit Description: " + exit.describe());
   // }
}
