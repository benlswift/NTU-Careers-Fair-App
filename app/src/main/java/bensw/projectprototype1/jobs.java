package bensw.projectprototype1;

import java.util.ArrayList;
import java.util.List;

public class jobs {
    public String name;
    public String desc;
    public String type;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public jobs() {
    }

    public jobs(String name,String desc, String type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }
}
