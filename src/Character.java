import java.util.*;
class Character {
    String name;
    int healthPoints;
    int defense;
    ArrayList<Item> inventory = null;
    Character (String name, int healthPoints, int defense) {
        this.name=name;
        this.healthPoints=healthPoints;
        this.defense=defense;
        this.inventory = new ArrayList<>();
    }

    public Character(Scanner s) throws NoCharacterException, NoItemException {
        String line = s.nextLine();
        if (line.equals("===")) {
            throw new NoCharacterException();
        }
        String[] lineInfo = line.split(",");
        this.name = lineInfo[0];
        this.healthPoints = Integer.parseInt(lineInfo[1]);
        this.defense = Integer.parseInt(lineInfo[2]);
        this.inventory = new ArrayList<>();
        s.nextLine();
    }
    
    public String toString() {
        return this.name;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public int getHP() {
        return healthPoints;
    }
    
    public void setHP(int n) {
        this.healthPoints = n;
    }

    public int getDEF() {
        return this.defense;
    }

    public void setDEF(int n) {
        this.defense = n;
    }
    
    public void killPlayer() {
        this.setHP(0);
    }

    public Item getFromInventory(String itemName) throws NoItemException {        
        for (Item i : this.inventory) {
            if (i.goesBy(itemName)) {
                return i;
            }
        }
        throw new NoItemException();
    }
    public void addToInventory(Item item) {
         inventory.add(item);
         //System.out.println(item.getPrimaryName() + "added to "+this.name);
    }
    void removeFromInventory(Item item) {
         inventory.remove(item);
    } 

    public String printLifeStatus(int healthPoints) {
        //System.out.println("life status called");
        if (healthPoints > 75) {
            return this.name+" has high HP!!";
        } else if (healthPoints > 50) {
            return "The "+this.name+" is a little hurt, but still kicking.";
        } else if (healthPoints > 25) {
            return "They're nearing dangerous levels of hurt.";
        } else if (healthPoints >0) {
            return this.name+" has critically low health!!!";
        } else {
            return "";
        }
    }
}
