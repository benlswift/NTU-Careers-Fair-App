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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class map extends android.app.Fragment {
View view;
    Button emp1;
    Button emp2;
    Button emp3;
    Button emp4;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_map, container, false);
        emp1 = (Button) view.findViewById(R.id.btn1);

        emp2 = (Button) view.findViewById(R.id.btn2);
        emp3 = (Button) view.findViewById(R.id.btn3);
        emp4 = (Button) view.findViewById(R.id.btn4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        myRef.child("companies").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies comp = dataSnapshot.getValue(companies.class);
                emp1.setText(comp.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        myRef.child("companies").child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies comp = dataSnapshot.getValue(companies.class);
                emp2.setText(comp.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        myRef.child("companies").child("3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies comp = dataSnapshot.getValue(companies.class);
                emp3.setText(comp.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        myRef.child("companies").child("4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies comp = dataSnapshot.getValue(companies.class);
                emp4.setText(comp.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        emp1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ViewAll.ID = "1";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ViewAll.ID = "2";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ViewAll.ID = "3";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ViewAll.ID = "4";
                loadFragment(new companyPage());
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
}
