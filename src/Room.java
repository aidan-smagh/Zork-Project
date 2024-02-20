import java.util.ArrayList;

public class Room{


   String name;
   String desc;  //description
   
   //ArrayList<Exit> exits;



    Room(String name, String desc){
       this.name = name;
       this.desc = desc;
      // this.exits = new A`rrayList<>();

           
    
    }

    public String getName(){
        return name;
    }

    public void setDesc(String desc){
        this.desc = desc;

    }

    String describe(){
        return this.name+ " " +this.desc;
    }

    //package leaveby(){
    //}

    //public addExit(){
    //}
    


   public static void main(String[] args){
    
        Room testRoom = new Room("Test Room", "This is a test Room.");
        System.out.println("Description: " + testRoom.describe());
    }
}
