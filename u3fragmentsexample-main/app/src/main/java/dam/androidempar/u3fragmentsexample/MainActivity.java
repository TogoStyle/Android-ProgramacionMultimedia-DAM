package dam.androidempar.u3fragmentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called from UI Button onClick():  replaces fragment2 by new instance
    public void addFragment(View view) {
        // option 1: create fragment manually: newInstance
        Fragment fragment2 = Fragment2.newInstance("Hello!", "fragment2 #" + ++counter);

        // Option 2: Create a bundle for args
        //Bundle bundle = new Bundle();
        //bundle.putString(Fragment2.ARG_PARAM1, "Hello!");
        //bundle.putString(Fragment2.ARG_PARAM2, "fragment2 #" + ++counter);


        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)   // optimizes operations in this transaction
                .addToBackStack(null)         // null - name is optional

                // Option1: pass a fragment instance:
                .replace(R.id.fragmentContainerView_2, fragment2, null)   // remove + add


                // Option2: let fragment manager instantiate fragment2 and set arguments
                //.replace(R.id.fragmentContainerView_2, Fragment2.class, bundle)

                // set up animation
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)

                // In the same transaction: we could also replace fragment1 by fragment2
                //.replace(R.id.fragment_container_view_1, Fragment2.class, bundle)

                .commit();
    }
}