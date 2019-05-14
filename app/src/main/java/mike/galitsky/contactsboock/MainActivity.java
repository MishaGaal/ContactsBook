package mike.galitsky.contactsboock;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ContactsViewModel contactsViewModel;
    public static final int REQUEST_CODE = 1;
    public static final int REQUEST_CODE_UP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditionActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ContactsAdapter adapter = new ContactsAdapter();
        recyclerView.setAdapter(adapter);

        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        contactsViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.submitList(contacts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                contactsViewModel.delete(adapter.getContactAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Contact contact) {
                Intent intent = new Intent(MainActivity.this, EditionActivity.class);
                intent.putExtra(EditionActivity.EXTRA_NAME, contact.getName());
                intent.putExtra(EditionActivity.EXTRA_LAST_NAME, contact.getLastName());
                intent.putExtra(EditionActivity.EXTRA_PHONE, contact.getPhone());
                intent.putExtra(EditionActivity.EXTRA_ID, contact.getId());
                startActivityForResult(intent, REQUEST_CODE_UP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(EditionActivity.EXTRA_NAME);
            String last_name = data.getStringExtra(EditionActivity.EXTRA_LAST_NAME);
            String phone = data.getStringExtra(EditionActivity.EXTRA_PHONE);

            Contact contact = new Contact(name, last_name, phone);
            contactsViewModel.insert(contact);

            Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();
        }else if (requestCode == REQUEST_CODE_UP && resultCode == RESULT_OK){
            int id = data.getIntExtra(EditionActivity.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "Can't be saved", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(EditionActivity.EXTRA_NAME);
            String last_name = data.getStringExtra(EditionActivity.EXTRA_LAST_NAME);
            String phone = data.getStringExtra(EditionActivity.EXTRA_PHONE);
            Contact contact = new Contact(name, last_name, phone);
            contact.setId(id);
            contactsViewModel.update(contact);
        }else {
            Toast.makeText(this, "Cant be saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                contactsViewModel.deleteAllContacts();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
