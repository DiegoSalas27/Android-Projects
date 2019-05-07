package pe.edu.upc.namekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView nameTextView;
    // Preferences parameters
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    // Preferences object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        updateCurrentName("");

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKeptName("");
                Snackbar.make(view, "Name has been cleared",
                        Snackbar.LENGTH_LONG)
                        .setAction("Keep Name", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                displayWelcome();
                            }
                        }).show();
                updateCurrentName("");
            }
        });
        displayWelcome();
    }

    private void displayWelcome() {
        // Presents an Alert Dialog to request a name if not present
        String name = getKeptName();
        if(name.length() > 0) {
            // A name was found, so it welcomes back the user
            Toast.makeText(this, "Welcome back " + name + "!",
                    Toast.LENGTH_LONG).show();
            updateCurrentName(name);
        }
        else {
            // No name is present, so it needs to request one
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Name Keeper");
            alert.setMessage("What is your name?");
            final EditText nameEditText = new EditText(this);
            alert.setView(nameEditText);
            alert.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = nameEditText.getText().toString();
                            if (name.length() > 0) {
                                setKeptName(name);
                                Toast.makeText(getApplicationContext(),
                                        "Welcome " + name + "!",
                                        Toast.LENGTH_LONG).show();
                                updateCurrentName(name);
                            }
                        }
                    });
            alert.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),
                                    "Don't worry, no pressure",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
            alert.show();
        }
    }

    private void setKeptName(String name) {
        // Stores given name in Preferences
        if(sharedPreferences != null) {
            // Opens Preferences file for edition
            SharedPreferences.Editor e = sharedPreferences.edit();
            e.putString(PREF_NAME, name);
            e.commit();
        }
    }
    private String getKeptName() {
        // Obtains stored name if present,
        // otherwise returns an empty string
        if(sharedPreferences == null) {
            sharedPreferences = getSharedPreferences(
                    PREFS, MODE_PRIVATE);
        }
        return sharedPreferences.getString(PREF_NAME, "");
    }

    private void updateCurrentName(String name) {
        // Update TextView depending on name
        String nameMessage = name.length() > 0 ?
                "Current name is " + name :
                "No name kept";
        nameTextView.setText(nameMessage);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
