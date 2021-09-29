import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private TimeUtil timeUtil;
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.timeUtil = new TimeUtil();
    }

    public boolean isRestaurantOpen() {

        LocalTime currentTime= getCurrentTime();

        if((ChronoUnit.SECONDS.between(getOpeningTime(),currentTime) == 0 || getOpeningTime().isBefore(currentTime)) && (ChronoUnit.SECONDS.between(currentTime,
                getClosingTime())==0 || getClosingTime().isAfter(currentTime))){
            return true;
        }else return false;

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }
    // preferred a different logic hence I dont need get current time method stated below.
    public LocalTime getCurrentTime(){ return  timeUtil.fetchCurrentTime(); }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setTimeUtil(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }
}
