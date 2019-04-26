package bensw.projectprototype1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
//import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
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

public class companyPage extends android.app.Fragment {
    View view;
    TextView name;
    TextView location;
    TextView grad;
    TextView place;
    TextView info;
    Button job1;
    String jobID = "1";
    Button fav;
    String compID = ViewAll.ID;
String uid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_companypage, container, false);
        name = (TextView) view.findViewById(R.id.name);
        location = (TextView) view.findViewById(R.id.location);
        grad = (TextView) view.findViewById(R.id.graduate);
        place = (TextView) view.findViewById(R.id.placement);
        info = (TextView) view.findViewById(R.id.info);
        job1 = (Button) view.findViewById(R.id.job1);
        fav = (Button) view.findViewById(R.id.button10);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        // Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
uid = user.getUid();
// creating user object

// pushing user to 'users' node using the userId

        myRef.child("companies").child(compID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                companies comp = dataSnapshot.getValue(companies.class);
                name.setText(comp.name);
                location.setText(comp.city);
                info.setText(comp.info);
                //grad.setText(comp.graduate);
                //place.setText(comp.placement);
                if (comp.graduate.equals("Yes")){
                    grad.setText("Graduate");

                    grad.setBackgroundResource(R.color.colorPrimary);
                }
                else {
                    grad.setText("");
                }
                if (comp.placement.equals("Yes")){
                    place.setText("Placement");
                    place.setBackgroundResource(R.color.colorPrimary);
                }
                else{
                    place.setText("");
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });

        myRef.child("companies").child(compID).child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobs job = dataSnapshot.getValue(jobs.class);
                job1.setText(job.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                addFav(uid,compID);

                // Get instance of Vibrator from current Context Vibrator

            }
        });
        job1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                loadFragment(new jobPage());

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
    public String addFav(String userID, String favourite) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String userId = myRef.push().getKey();

        myRef.child("users").child(userID).setValue(favourite);
        return favourite + "-" + userID;
    }

}
