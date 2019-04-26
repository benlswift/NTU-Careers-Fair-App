package bensw.projectprototype1;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;
    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        register = (Button) findViewById(R.id.reg);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }
    public void loadReg(View view){
        Toast.makeText(LoginActivity.this, "REGISTER...",
                Toast.LENGTH_SHORT).show();
        Intent regIntent = new Intent(LoginActivity.this, Register.class);
        startActivity(regIntent);
    }
    public void signInUser(View view){
        EditText emailField = findViewById(R.id.email);
        EditText passwordField = findViewById(R.id.password);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("hi", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //loadFragment(new Profile());
                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            myIntent.putExtra("fragment",1);
                            startActivity(myIntent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("hi", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

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