class Exit{

    String dir;
    Room dest;  //room exiting to
    Room src; //rom currently in

    Exit(String dir, Room src, Room dest){
        this.dir = dir;
        this.src = src;
        this.dest = dest;
    }


    
    String describe(){
        return "Yo can go " + this.dir + " to " + this.dest.getName();
    } 

    String getDir(){
        return dir;
    }

    Room getSrc(){
        return src;
    }

    Room getDest(){
        return dest;
    }


    public static void main(String[] args){
        Room entry = new Room("M's room", "This is my room");
        Room hall = new Room("Hall", "This is the hall");

        //Exit
        Exit exit = new Exit("n", entry, hall);
        //Exit exit2 = new Exit("west", entry, ...(//wall);
        
        System.out.println("Exit Dir: " + exit.getDir());
        System.out.println("Src Room: " + exit.getSrc().getName());
        System.out.println("Des: " + exit.getDest().getName());
        System.out.println("Exit Description: " + exit.describe());
    }
}
