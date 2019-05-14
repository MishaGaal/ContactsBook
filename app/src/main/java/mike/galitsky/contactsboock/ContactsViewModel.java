package mike.galitsky.contactsboock;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContactsViewModel extends AndroidViewModel {
    private ContactsRepository repository;
    private LiveData<List<Contact>> allContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactsRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(Contact contact){
        repository.insert(contact);
    }
    public void update(Contact contact){
        repository.update(contact);
    }
    public void delete(Contact contact){
        repository.delete(contact);
    }
    public void deleteAllContacts(){
        repository.deleteAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }
}
