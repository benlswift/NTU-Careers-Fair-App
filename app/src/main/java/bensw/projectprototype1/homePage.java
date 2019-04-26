package bensw.projectprototype1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class homePage extends android.app.Fragment {


    View view;
    Button button;
    Button profile;
    Button map;
    Button futurehubbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_page, container, false);
// get the reference of Button
        button = (Button) view.findViewById(R.id.button);
        futurehubbtn = (Button) view.findViewById(R.id.button5);
        profile = (Button) view.findViewById(R.id.button6);
        map = (Button) view.findViewById(R.id.button4);
// perform setOnClickListener on first Button
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
// display a message by using a Toast
                loadFragment(new ViewAll());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        futurehubbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
// display a message by using a Toast
                loadFragment(new futurehub());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
// display a message by using a Toast
                loadFragment(new map());
                // Get instance of Vibrator from current Context Vibrator

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
//                    // Name, email address, and profile photo Url
//                    String name = user.getDisplayName();
//                    String email = user.getEmail();
//                    Uri photoUrl = user.getPhotoUrl();
//
//                    // Check if user's email is verified
//                    boolean emailVerified = user.isEmailVerified();
//
//                    // The user's ID, unique to the Firebase project. Do NOT use this value to
//                    // authenticate with your backend server, if you have one. Use
//                    // FirebaseUser.getToken() instead.
//                    String uid = user.getUid();
                    loadFragment( new Profile());
                }
                else {
// display a message by using a Toast
                    Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(myIntent);
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

}