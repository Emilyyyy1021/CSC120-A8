import java.util.*;

import javax.management.RuntimeErrorException;

public class Game implements Contract {
    
    /** player */
    String player;

    /** health points */
    double points;

    /** player's height */
    double height;

    /** inventory */
    ArrayList<String> inventory = new ArrayList<>();

    /** Consturctor */
    public Game(String name, double height) {
        this.player = name;
        this.points = 100;
        this.height = height;
        this.inventory = new ArrayList<>();
    }

    /**
     * check the inventory
     * @param item
     * @return T/F the inventory contains the item
     */
    public boolean checkInventory(String item){
        return this.inventory.contains(item);
    }
    
    /**
     * Add the item to the inventory
     */
    public void grab(String item){
        this.inventory.add(item);
        System.out.println("Grabbed " + item);
    }

    /**
     * remove the item from the inventory
     */
    public String drop(String item){
        if (checkInventory(item)){
            this.inventory.remove(item);
            return "Dropped " + item;
        }else{
            return "Unsuccess";
        }
    }

    //random comments
    String[] arr = {"Cool", "Nice", "Suspicious", "I would not reccommend you taking that"};
    
    // create random variable
    Random random = new Random();

    /**
     * examine the item 
     */
    public void examine(String item){
        // prints out random comment
        int select = random.nextInt(arr.length);
        System.out.println("Examined(◐ ‿ ◑)" + item + " " + arr[select]);
    }

    /**
     * use the item 
     */
    public void use(String item){
        System.out.println("Used @(￣-￣)@");
    }

    /**
     * walk only in compass direction
     */
    public boolean walk(String direction){
        if (direction == "South" || direction == "North" || direction == "East" || direction == "West") {
            return true;
        }else {
            return false;
        }
    }

    /**
     * fly in a certain area
     */
    public boolean fly(int x, int y){
        if (x <= 100 && x >= -100 && y <= 100 && y>= -100){
            return true;
        }else {
            return false;
        }
    }

    /**
     * shrink with reducing height and health points
     */
    public Number shrink(){
        System.out.println("Oh no! You ate the poison mushrooms(・・?)");
        this.height -= 30.0;
        this.points -= 10;
        return this.height;
    }

    /**
     * grow with adding height and deduct health points 
     */
    public Number grow(){
        System.out.println("Oh no! You drink the poisonous potion Σ（・□・；）");
        this.height += 40.0;
        this.points -= 10;
        return this.height;
    }

    /**
     * rest and add health points
     */
    public void rest(){
        try {
            if (this.points == 100) {
                throw new RuntimeErrorException(new Error());
            } else {
                this.points += 10;
                System.out.println("Recovering... " + this.points + " ☆彡");
            }
        } catch (RuntimeErrorException e) {
            System.out.println("Cannot recover (T^T)");
        }
       
    }

    /**
     * undo but only prints out something
     */
    // sorry, i don't know what to do with this 
    public void undo(){
        System.out.println("Sorry. You can't undo your command (you have to be responsible with you decision)");
    }

    public static void main(String[] args) {
        // Create a Game object
        Game game = new Game("Emily", 173.0);
    
        // Grab some items
        game.grab("flower");
        game.grab("Sword");
    
        // Check inventory
        System.out.println("Inventory: " + game.inventory);
    
        // Drop an item
        String result = game.drop("Sword");
        System.out.println(result);
    
        // Examine an item
        game.examine("Sword");
    
        // Use an item
        game.use("Sword");
    
        // Walk in different directions
        System.out.println("Walk North: " + game.walk("North"));

        // Fly to coordinates
        System.out.println("Fly to (50, 50): " + game.fly(50, 50));
        System.out.println("Fly to (200, 200): " + game.fly(200, 200));
    
        // Shrink and grow
        System.out.println("Height after shrinking: " + game.shrink());
        System.out.println("Height after growing: " + game.grow());
    
        // Try to rest
        game.rest();
    
        // Try to undo
        game.undo();
    }
    

}
