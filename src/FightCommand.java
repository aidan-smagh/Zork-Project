import java.util.Scanner;
import java.util.HashSet;
class FightCommand extends Command {
  
    String enemyName;

    public FightCommand(String enemyName) {
       
        this.enemyName = enemyName;
        
    }
    String execute() {
        if (this.enemyName.isEmpty()) {
            return "Fight what?";
        }
        System.out.println("");
        try {
            Character enemy = GameState.instance().getDungeon()
                .getCharacter(enemyName);
            Room currentRoom = GameState.instance()
                .getAdventurersCurrentRoom();
            //grab the character that is in the room
           
            HashSet<Character> charsInRoom = GameState.instance().getCharsInRoom(currentRoom);
        
            if(charsInRoom.contains(enemy)){
            Scanner scanner = new Scanner(System.in);

            System.out.println("The fight is on!");
           
                for (Item item : GameState.instance().getInventory()) {
                    System.out.println("- " + item.getPrimaryName());
                }

              //  Item selectedWeapon = GameState.instance().getItemFromInventoryNamed("Sword");

            System.out.println("Which item would you like to use? ");
            String selectedItem = scanner.nextLine();
            
            System.out.println(selectedItem);
            try {
               Item selectedWeapon = GameState.instance().getItemFromInventoryNamed(selectedItem);
               System.out.println(selectedWeapon);
               int hitCount = 0;
               System.out.println("How many times will you hit the "+enemy.getName()+"?");
                hitCount = Integer.parseInt(scanner.nextLine());
                for (int i=0; i<hitCount; i++) {
                    
                    hit(enemy, selectedWeapon);
                    if (enemy.getHP() <= 0) {
                    GameState.instance().removeCharFromRoom(enemy, currentRoom);
                        break;
                    }
                }
                String enemyStatus = enemy.printLifeStatus(enemy.getHP());
                System.out.println(enemyStatus);
            } catch (NoItemException e) {
             System.out.println("There's no "+selectedItem+" in your inventory!");
            }

           // hit(enemy);
            }

           /*
            if (!GameState.instance().getCharsInRoom(currentRoom)
                    .contains(i)) {
                System.out.println("The fight is on!");
            }
            for (Item item : GameState.instance().inventory) {
                System.out.println("Item name is " + item.getPrimaryName());
            }
            */

        } catch (NoCharacterException e) {
            System.out.println("There's no " + enemyName + " in here!");
        }


       // return "enemy name not found";
        return "This fight has ended.";
    }

    void hit (Character c, Item i){
   // void hit(Character c){
//        for(Item item : GameState.instance().getInventory()){
  //      System.out.println(item.getPrimaryName());
   // } 
        
        //System.out.println("You hit " + c + " with " + i.getPrimaryName());
        c.setHP(c.getHP()-i.getDamage()); //decrease chars HP by the weapon's damage
        //System.out.println(c.getName()+"'s HP has been reduced by "+i.getDamage());
        //System.out.println(c.getName()+"'s health is now "+c.getHP()+".");
        Room currentRoom = GameState.instance().currentRoom; 
        if (c.getHP() <= 0 ) {
            System.out.println(c.getName()+" slain!");
            if (!c.inventory.isEmpty()) {
                System.out.println("Their loot drops to the floor! Take a look around.");
                for (Item item : c.inventory) {    
                    c.removeFromInventory(item);
                    GameState.instance().addItemToRoom(item, currentRoom);
                }
            } else {
                System.out.println("The "+c.getName()+" wasn't carrying anything...");
            }
        }
    }

}













