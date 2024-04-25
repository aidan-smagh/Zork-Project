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
               hit(enemy, selectedWeapon);
            } catch (NoItemException e) {
             System.out.println("You don't have that item in your inventory!");
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
        return "Hi - add to this";
    }

    void hit (Character c, Item i){
   // void hit(Character c){
//        for(Item item : GameState.instance().getInventory()){
  //      System.out.println(item.getPrimaryName());
   // }
        System.out.println("You hit " + c + " with " + i.getPrimaryName());
     
        int damage = i.getDamage();
        System.out.println(damage);
        int enemyHealth = c.getHP();
        System.out.println(enemyHealth);
    }

}













