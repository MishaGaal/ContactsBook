package mike.galitsky.contactsboock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditionActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "mike.galitsky.contactsboock.EXTRA_NAME";
    public static final String EXTRA_LAST_NAME = "mike.galitsky.contactsboock.EXTRA_LAST_NAME";
    public static final String EXTRA_PHONE = "mike.galitsky.contactsboock.EXTRA_PHONE";
    public static final String EXTRA_ID = "mike.galitsky.contactsboock.EXTRA_ID";

    private EditText editTextName, editTextLastName, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        editTextName = findViewById(R.id.edit_text_name);
        editTextLastName = findViewById(R.id.edit_text_lastname);
        editTextPhone = findViewById(R.id.edit_text_phone);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextLastName.setText(intent.getStringExtra(EXTRA_LAST_NAME));
            editTextPhone.setText(intent.getStringExtra(EXTRA_PHONE));
        }
    }

    private void saveContact(){

        String name = editTextName.getText().toString();
        String last_name = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();

        if(name.trim().isEmpty() || last_name.trim().isEmpty() || phone.trim().isEmpty()){
            Toast.makeText(this, "Enter fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_LAST_NAME, last_name);
        intent.putExtra(EXTRA_PHONE, phone);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            intent.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_contact:
                saveContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
