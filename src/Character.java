import java.util.*;
class Character {
    String name;
    int healthPoints;
    int defense;

    Character (String name, int healthPoints, int defense) {
        this.name=name;
        this.healthPoints=healthPoints;
        this.defense=defense;

    }

    public Character(Scanner s) throws NoCharacterException {
        String line = s.nextLine();
        if (line.equals("===")) {
            throw new NoCharacterException();
        }
        String[] lineInfo = line.split(",");
        this.name = lineInfo[0];
        this.healthPoints = Integer.parseInt(lineInfo[1]);
        this.defense = Integer.parseInt(lineInfo[2]);
        s.nextLine();
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

    public String printLifeStatus(int healthPoints) {
        if (healthPoints > 75) {
            return "High HP! No worries!";
        } else if (healthPoints > 50) {
            return "A little hurt, but still kciking.";
        } else if (healthPoints > 25) {
            return "Nearing dangerous levels of hurt.";
        } else {
            return "Critically low health!!!";
        }
    }
}
