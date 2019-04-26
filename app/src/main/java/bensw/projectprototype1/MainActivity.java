package bensw.projectprototype1;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button viewAll;
    Button home;
    View view;

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        createComp("Boots","Nottingham","1","Yes","Yes", "Health and beauty retailer and pharmacy chain");
//        createComp("Experian","Nottingham","2","Yes","No","Consumer credit reporting company");
//        createComp("BBC","London","3","Yes","No","World's oldest national broadcasting organisation and the largest broadcaster in the world");
//        createComp("Capital One", "Nottingham", "4", "Yes", "Yes","Bank holding company specializing in credit cards, auto loans, banking and savings accounts");
//        createUser("test","test","1");
//        addJobs("Recruitment Consultant","","Graduate","2","1");
//        addJobs("Broadcast Engineer","","Placement","3","1");
//        addJobs("Software Engineer","","Graduate","4","1");
       // addFav("CRGBnN1tLRfBn544oAXAtyCHpaq1","1");
        // get the reference of Button
        home = (Button) findViewById(R.id.button8);
        home.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
                loadFragment(new homePage());
                // Get instance of Vibrator from current Context Vibrator

            }
        });

        loadFragment(new homePage());
        if(getIntent().getIntExtra("fragment",0)==1){
            loadFragment(new Profile());
        }

    }
    private void createComp(String name, String city,String key,String graduate, String placement, String info) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String userId = myRef.push().getKey();


        companies comp = new companies(name, city,graduate,placement,info);

        myRef.child("companies").child(key).setValue(comp);

    }
    public void addFav(String userID, String favourite) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String userId = myRef.push().getKey();

        myRef.child("users").child(userID).setValue(favourite);
    }
    private void addJobs(String name, String desc, String type, String compKey, String jobKey){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String userId = myRef.push().getKey();

        jobs job = new jobs(name,desc,type);

        myRef.child("companies").child(compKey).child(jobKey).setValue(job);
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
