
public class HealthCommand extends Command{

    public String execute(){
    
    int healthNum = GameState.instance().PLAYER.getHP();

      String healthStatus;
        if (healthNum >= 80) {
            healthStatus = "You feel fit as a fiddle.";
        } else if (healthNum >= 60) {
            healthStatus = "You're a bit light-headed.";
        } else if (healthNum >= 40) {
            healthStatus = "Each step is a stagger from the pain of your wounds.";
        } else {
            healthStatus = "You are about to die.";
        }

        return healthStatus;
    
    }




}
