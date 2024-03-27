
class ItemSpecificCommand extends Command{

    private String verb;
    private String noun;


    public ItemSpecificCommand(String verb, String noun){
        this.verb = verb;
        this.noun = noun;
    }

    String execute(){

    try{
        Item item = GameState.instance().getItemInVicinityNamed(noun);
        
        if (item != null){
            String responseMsg = item.getMessageForVerb(verb);
            if (responseMsg != null) {
                    return responseMsg;
                } else {
                    return "You can't " + verb + " the " + noun + ".";
                }
            } else {
                return "There is no " + noun + " here.";
            }

    } catch (Exception e) {
              return "Assimilate what?";
          }
      
            // item = GameState.instance().getItemInVicinityNamed(noun);
        
    }
}
        //chechs if both the Room and hte inventory of the item is in them.
        //
        //if found
       // if(item != null){
         //   String responseMessage = item.getMessageForVerb(verb);

           // if(responseMessage != null){
           //     return responseMessage;
      //      }
       
        //    else{
        //        return "you can't " + verb + " the " + noun + ".";
        //    }
      //  }
      //  else{
      //      return "There is no " + noun + " here.";
      //  }

   //     } catch (Exception e) {
 //           return "Assimilate what?";
    //    }
  //  }
       // return "verb and noun";
    
//}
