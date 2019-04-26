package bensw.projectprototype1;

import java.util.ArrayList;
import java.util.List;

public class companies {
    public String name;
    public String city;
    public String graduate;
    public String placement;
    public String info;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public companies() {
    }

    public companies(String name, String city, String graduate, String placement, String info) {
        this.name = name;
        this.city = city;
        this.graduate = graduate;
        this.placement = placement;
        this.info = info;
    }
}
