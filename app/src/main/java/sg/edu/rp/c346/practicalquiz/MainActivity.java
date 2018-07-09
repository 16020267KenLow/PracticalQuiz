package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    Button btSave;
    Spinner spClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etAge = findViewById(R.id.editTextAge);
        btSave = findViewById(R.id.buttonSave);
        spClass = findViewById(R.id.spinner);

        etAge.requestFocus();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEdit = prefs.edit();
                Integer intClass = spClass.getSelectedItemPosition();

                prefEdit.putInt("class", intClass);
                prefEdit.commit();

                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        if (etName.getText().toString().isEmpty()) {
            prefEdit.putString("name", "");
        } else {
            prefEdit.putString("name", etName.getText().toString());
        }
        if (etAge.getText().toString().isEmpty()) {
            prefEdit.putInt("age", 0);
        } else {
            prefEdit.putInt("age", Integer.parseInt(etAge.getText().toString()));
        }

        //Step 1d: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting from the SharedPreferences object
        String name = prefs.getString("name", "");
        Integer age = prefs.getInt("age", 0);
        Integer intClass = prefs.getInt("class", 0);


        etName.setText(name);
        if (age == 0) {
            etAge.setText(null);
        }
        else {
            etAge.setText(age + "");
        }
        spClass.setSelection(intClass);
    }
}
