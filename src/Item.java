import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
public class Item {

    String primaryName;
    int weight;
    int damage=0;
   // String fullCommand;
    //hashtable and hashset for messages and aliases -used
    Hashtable<String, String> messages;
    HashSet<String> aliases; //the other names players can refer to the item are called its aliases.
    
    ArrayList<String> fullCommands;

    public Item(Scanner s) throws NoItemException {
        aliases = new HashSet<>();
        messages = new Hashtable<>();
        this.primaryName = s.nextLine();
        //System.out.println(primaryName);         
        if (primaryName.equals("===")) {
            throw new NoItemException();
        }
        String[] names = primaryName.split(",");
        this.primaryName = names[0];
        this.weight = Integer.parseInt(s.nextLine());
        
        String line = s.nextLine();
      


       // this.fullCommand = "";
        fullCommands = new ArrayList<>();
        
        //check that this line doesn't equal to ---. check for aliases       
        while (!line.contains("---")) {
           
           
           if(line.startsWith("damage")){
            //System.out.println(line);   
               int startInd = line.indexOf('[') +1;
                  int endInd = line.indexOf(']');
                  String dValue = line.substring(startInd, endInd);
                 this.damage = Integer.parseInt(dValue);
                 //System.out.println(dValue); 
                 line = s.nextLine();
             }

           
           
           
           
           
            String[] parts = line.split(":");
            String part1 = parts[0];
            fullCommands.add(part1);
            String command;

           // String fullcommand;

            if(part1.contains("[")){
             command = part1.split("\\[")[0].trim();
            // fullCommand = part1.trim();
            }
            else{
                command = part1;
            }
            messages.put(command, parts[1]);
           
      //   String command = line.split("\\[")[0].trim();
      //  messages.put(command, line.substring(command.length()+1));

            line = s.nextLine();
            //System.out.println("items line is "+line);
        }
        //add aliases         
        for (int i = 1; i < names.length; i++) {
            aliases.add(names[i].trim());
        }
   /*     if (line.contains("light")) {            //stuck here, dont know how to make this item a light
            String[] lineInfo = line.split("=");
            if (lineInfo[1].equals("true")) {
                LightSource light = new LightSource(s);
                //this = light;
            }
        } */ 
    } 

    public boolean goesBy(String name) {
        return primaryName.equals(name) || aliases.contains(name);
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public int getWeight() {
        return weight;
    }

    
    public String toString() { //what to put here??
        return primaryName;
    }


    //not needed methods, but made them to make sure the hashtable and hashset are doing its job properly!
    public void printMessages() {
    // System.out.println("Message:"));

        System.out.println("Messages:");
        for (String verb : messages.keySet()) {
            System.out.println(verb + ": " + messages.get(verb)); //print verb with its message.
        }
    }
    public void printAliases() { //prints aliases of that item
        System.out.println("Aliases:");
        for (String alias : aliases) {
            System.out.println(alias);
        }
    }

    public Hashtable<String, String>  getMessages() {
        return messages;
    }
    public HashSet<String> getAliases() {
        return aliases;
    }
    public ArrayList<String> getFullCommands(){
    return fullCommands;
    }


    public static void main(String[] args) throws NoItemException {
        try {
            // Test with the first file
            Scanner scanner = new Scanner(new File("files/f.txt"));
            Item item = new Item(scanner);
            System.out.println("Primary Name: " + item.getPrimaryName());
            System.out.println("Weight: " + item.getWeight());

            System.out.println("Msg for kick: " + item.getMessageForVerb("kick"));
            System.out.println("Msg for shake: " + item.getMessageForVerb("shake"));
            System.out.println("Msg for drink: " + item.getMessageForVerb("drink"));
            System.out.println(item.goesBy("can"));
            System.out.println(item.getMessageForVerb("kick"));

            item.printMessages();
            item.printAliases();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}




