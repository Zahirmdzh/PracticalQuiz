package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    Spinner sItems;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etAge = findViewById(R.id.editTextAge);
        sItems = findViewById(R.id.spinnerItems);
        btnSave = findViewById(R.id.buttonSave);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        String strAge = etAge.getText().toString();
        int age = Integer.parseInt(strAge);
        int idSpinner = sItems.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Name", strName);
        prefEdit.putInt("strAge", age);
        prefEdit.putInt("spinner", idSpinner);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("Name", "Default name");
        int age = prefs.getInt("strAge", 0);
        int spinner = prefs.getInt("spinner", 0);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        etName.setText(strName);
        etAge.setText(Integer.toString(age));
        sItems.setSelection(spinner);

    }
}