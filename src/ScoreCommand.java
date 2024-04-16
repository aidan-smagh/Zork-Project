

public class ScoreCommand extends Command{

    public String execute(){
    
    int score = GameState.instance().playerScore;
    
    String playerRank = "";

    if(score <= 10){
    playerRank = "Beginner";
    }
    else if(score <= 20){
        playerRank = "Amateur";
    }
    else if(score <= 30){
        playerRank = "Experienced";
    }
    else{
       playerRank = "Expert";
    }

    return "You have accumulated " + score + " points. This gives you a rank of "
        + playerRank + ".";
}}
