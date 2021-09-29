import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    public static final String AMELIE_S_CAFE = "Amelie's cafe";
    public static final String CHENNAI = "Chennai";
    public static final String SWEET_CORN_SOUP = "Sweet corn soup";
    public static final String VEGETABLE_LASAGNE = "Vegetable lasagne";
    Restaurant restaurant;

    private TimeUtil mockedTimeUtilClass(String time) {
        final TimeUtil mock = Mockito.mock(TimeUtil.class);
        // when current time is called, return current name and restaurant opening hours plus/minus time passed in
        // parameter
        Mockito.when(mock.fetchCurrentTime()).thenReturn(
                LocalTime.parse(time));
        return mock;
    }

    private void createRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant(AMELIE_S_CAFE, CHENNAI, openingTime, closingTime);
        restaurant.addToMenu(SWEET_CORN_SOUP, 119);
        restaurant.addToMenu(VEGETABLE_LASAGNE, 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        createRestaurant();
        final String time = "10:30:01";
        final TimeUtil mock = mockedTimeUtilClass(time);

        restaurant.setTimeUtil(mock);
        assertEquals(true,restaurant.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        createRestaurant();
        final TimeUtil mock = mockedTimeUtilClass("10:29:00");

        restaurant.setTimeUtil(mock);
        assertEquals(false,restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        createRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createRestaurant();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}