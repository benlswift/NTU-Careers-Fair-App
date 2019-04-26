package bensw.projectprototype1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.List;

import static bensw.projectprototype1.R.color.colorAccent;
import static bensw.projectprototype1.R.color.colorPrimary;


public class ViewAll extends android.app.Fragment {
    View view;
    Button emp1;
    Button emp2;
    Button emp3;
    Button emp4;
    //Button new1;
    TextView textView;
    String company = "";
    long numCompanies;
    List<Button> b;
    public static String ID = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_view_all, container, false);
// get the reference of Button
        textView = (TextView) view.findViewById(R.id.textView);
        emp1 = (Button) view.findViewById(R.id.emp1btn);
        emp2 = (Button) view.findViewById(R.id.emp2btn);
        emp3 = (Button) view.findViewById(R.id.emp3btn);
        emp4 = (Button) view.findViewById(R.id.emp4btn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
                // Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
                String userId = myRef.push().getKey();

// creating user object

// pushing user to 'users' node using the userId
                myRef.child("companies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numCompanies = dataSnapshot.getChildrenCount();
                Log.w("hi", "# companies:" + numCompanies);
                //emp4.setText(comp.name);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });

        myRef.child("companies").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                companies comp = dataSnapshot.getValue(companies.class);
                company = comp.name;
                emp1.setText(company);
//                addBtn(company);
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
                ID = "1";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ID = "2";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ID = "3";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        emp4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
// display a message by using a Toast
                ID = "4";
                loadFragment(new companyPage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });


//        myRef.child("companies").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                numCompanies = dataSnapshot.getChildrenCount();
//                Log.w("hi", "# companies:" + numCompanies);
//                //emp4.setText(comp.name);
//                for(int k =1; k <= numCompanies; k++ )
//                {
//                    Log.w("hi", "Company:" + k);
//                    //String j = String.valueOf(k);
//                    myRef.child("companies").child("3").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            companies comp = dataSnapshot.getValue(companies.class);
//                            company = comp.name;
//                            addBtn(company);
//                            Log.w("hi", "Name:" + company);
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError error) {
//                            // Failed to read value
//                            Log.w("hi", "Failed to read value.", error.toException());
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("hi", "Failed to read value.", error.toException());
//            }
//        });



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
    public void addBtn(String name){
        LinearLayout linearLayout = new LinearLayout(getActivity());
        // Set the layout full width, full height
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL); //or VERTICAL

        Button button = new Button(getActivity());
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        button.setText(name);
        //For buttons visibility, you must set the layout params in order to give some width and height:
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);

        ViewGroup viewGroup = (ViewGroup) view;

        linearLayout.addView(button);
        viewGroup.addView(linearLayout);
        Toast.makeText(getActivity(), "Name: " + name, Toast.LENGTH_LONG).show();


    }
}