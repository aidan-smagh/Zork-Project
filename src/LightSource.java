/*

import java.util.*;
public class LightSource extends Item {
    int luminance;
    int currentBrightness;
    int burnRate;

//need Scanner constructor 
LightSource(Scanner s) {
    String line = s.nextLine();
    String[] info = line.split(",");
    this.luminance = Integer.parseInt(info[0]);
    this.currentBrightness = this.luminance;
    this.burnRate = Integer.parseInt(info[1]); 
    s.nextLine();
    } 
LightSource(int luminance, int currentBrightness, int burnRate) {
    this.luminance = luminance;
    this.currentBrightness = this.luminance; //light starts at max brightness, aka luminance
    this.burnRate = burnRate;     
}

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
}  */
