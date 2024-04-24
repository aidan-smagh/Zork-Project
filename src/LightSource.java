
<<<<<<< HEAD
import java.io.*;
import java.util.*;
public class LightSource extends Item {
  /*  String primaryName;
    int weight; */
    int luminance;
    int currentBrightness;
    int burnRate;
 /*   Hashtable<String, String> messages;
    HashSet<String> aliases; */
//need Scanner constructor 
    
 /*   public LightSource(Item item) throws NoItemException {
        Scanner s = new Scanner(new File(GameState.
        Item i = GameState.instance().getDungeon().getItem(item);
        this.primaryName = i.getPrimaryName();
        this.weight = i.getWeight();
        this.messages = i.getMessages();
        this.aliases = i.getAliases();
        this.fullCommands = i.getFullCommands(); */
    
   public LightSource(Scanner s) throws NoItemException {
        super(s);
        aliases = new HashSet<>();
        messages = new Hashtable<>();
        this.primaryName = s.nextLine(); 
        if (primaryName.equals("===")) {
            throw new NoItemException();
        }
        String[] names = primaryName.split(",");
        this.primaryName = names[0]; */
        //while (s.hasNextLine() {
        String lightInfo = s.nextLine();
        System.out.println("light info is "+lightInfo);
        String[] info = lightInfo.split("@");
        this.luminance = Integer.parseInt(info[0]);
        this.currentBrightness = this.luminance;
        this.burnRate = Integer.parseInt(info[1]); 
        s.nextLine();     
        this.weight = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        
        fullCommands = new ArrayList<>();
        
      //check that this line doesn't equal to ---. check for aliases
        while (!line.equals("---")) {
            String[] parts = line.split(":");
            String part1 = parts[0];
            fullCommands.add(part1);
            String command;
            if(part1.contains("[")){
               command = part1.split("\\[")[0].trim();
            } else {
               command = part1;
            }
            messages.put(command, parts[1]);
            line = s.nextLine();
        }
        //add aliases
        for (int i = 1; i < names.length; i++) {
            aliases.add(names[i].trim());
        } 
   } 
/* LightSource(int luminance, int currentBrightness, int burnRate) {
    this.luminance = luminance;
    this.currentBrightness = this.luminance; //light starts at max brightness, aka luminance
    this.burnRate = burnRate;     
} */
=======

import java.util.Scanner;

public class LightSource extends Item {
    int luminance = 0;
    int currentBrightness = 0;
    int burnRate = 0;

//need Scanner constructor 
    public LightSource(Scanner s) throws NoItemException  {
   
    super(s);
    
  //  String line = s.nextLine();
  //  String[] info = line.split(" ");
    
        String line = s.nextLine();
        if(line.equals("===")){
            throw new NoItemException();
        }
        String[] info = line.split(" ");

    this.luminance = Integer.parseInt(info[0]);
    this.currentBrightness = this.luminance;
    this.burnRate = Integer.parseInt(info[1]); 
      
    }
    
   
//LightSource(int luminance, int currentBrightness, int burnRate) {
  // super("LightSource");
   // this.luminance = luminance;
   // this.currentBrightness = this.luminance; //light starts at max brightness, aka luminance
   // this.burnRate = burnRate;     
//}
>>>>>>> f579c3c7e2f7b754f6b4775f819c1c70b022fa22

    public void diminish() {                 //decreases the brightnes
        this.currentBrightness -= burnRate;  //of light at chosen rate
    }
  
    public int getLuminance() {
        return this.luminance;        //luminance getter
    }                                 //and setter
    public void setLuminance(int n) {
        this.luminance = n;
   }

    public int getBrightness() {
        return this.currentBrightness;        //brightness getter
    }                                         //and setter
    public void setBrightness(int b) {
        this.currentBrightness = b;
    }

    public int getBurnRate() {
        return this.burnRate;         //burnRate getter
    }                                 //and setter   
    public void setBurnRate(int r) {
        this.burnRate = r;
    }
}  
