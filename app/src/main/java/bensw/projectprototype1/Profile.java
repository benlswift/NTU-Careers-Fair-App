package bensw.projectprototype1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Profile extends android.app.Fragment {
View view;
Button logout;
EditText email;
String uid;
TextView fav;
String favID = "1";
Button favbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_profile, container, false);
        email= (EditText) view.findViewById(R.id.email);
        logout = (Button) view.findViewById(R.id.logout);
        favbtn = (Button)view.findViewById(R.id.favBtn);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (getUser().equals("User Logged In")){
            // Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            String getEmail = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
            email.setText(getEmail);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                FirebaseAuth.getInstance().signOut();
                loadFragment(new homePage());

                // Get instance of Vibrator from current Context Vibrator

            }
        });
        myRef.child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favID = dataSnapshot.getValue(String.class);
                ViewAll.ID = favID;
                //favID = user.favourite;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
//        String favourite = getFav(uid);
//        if (favourite.equals("null")){
//
//        }
       // else {
            myRef.child("companies").child(favID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    companies comp = dataSnapshot.getValue(companies.class);
                    favbtn.setText(comp.name);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("hi", "Failed to read value.", error.toException());
                }
            });
        //}
        favbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                if (favbtn.getText()!="No Favourites") {
                    loadFragment(new companyPage());
                }

                // Get instance of Vibrator from current Context Vibrator

            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
    public static String getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            String getEmail = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            return "User Logged In";
        }
        else{
            return "User not logged in";
        }
    }
    public String getFav(String userid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        myRef.child("users").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favID = dataSnapshot.getValue(String.class);
                ViewAll.ID = favID;
                //favID = user.favourite;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
                favID = null;
            }
        });
        return favID;
    }

}


