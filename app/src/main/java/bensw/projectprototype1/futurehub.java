package bensw.projectprototype1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class futurehub extends android.app.Fragment {
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_futurehub, container, false);

        WebView browser = (WebView) view.findViewById(R.id.webview);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl("https://futurehub.ntu.ac.uk");
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
