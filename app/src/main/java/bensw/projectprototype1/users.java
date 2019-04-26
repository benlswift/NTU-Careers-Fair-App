package bensw.projectprototype1;

import java.util.List;

public class users {
    public String favourite;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public users() {
    }

    public users(String favourite) {
        this.favourite = favourite;
    }
}
