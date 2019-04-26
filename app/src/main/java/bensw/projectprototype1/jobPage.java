package bensw.projectprototype1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class jobPage extends android.app.Fragment  {
View view;
String compID = ViewAll.ID;
TextView name;
TextView desc;
TextView type;
TextView compName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_job_page, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        name = (TextView) view.findViewById(R.id.jobName);
        desc = (TextView) view.findViewById(R.id.jobInfo);
        type = (TextView) view.findViewById(R.id.jobType);
        compName = (TextView) view.findViewById(R.id.compName);

        final DatabaseReference myRef = database.getReference();
        myRef.child("companies").child(compID).child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobs job = dataSnapshot.getValue(jobs.class);
                name.setText(job.name);
                desc.setText(job.desc);
                type.setText(job.type);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
            }
        });
        myRef.child("companies").child(compID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                companies comp = dataSnapshot.getValue(companies.class);
                compName.setText(comp.name);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hi", "Failed to read value.", error.toException());
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
