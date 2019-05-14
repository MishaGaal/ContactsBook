package mike.galitsky.contactsboock;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactsRepository {
    private ContactDAO contactDAO;
    private LiveData<List<Contact>> allContacts;

    public ContactsRepository (Application application){
        ContactsBase contactsBase = ContactsBase.getInstance(application);
        contactDAO = contactsBase.contactDAO();
        allContacts = contactDAO.getAllContacts();
    }

    public void insert (Contact contact){
        new InsetNoteAsyncTask(contactDAO).execute(contact);

    }
    public void update (Contact contact){
        new UpdateNoteAsyncTask(contactDAO).execute(contact);
    }
    public void delete (Contact contact){
        new DeleteNoteAsyncTask(contactDAO).execute(contact);
    }
    public void deleteAllContacts (){
        new DeleteAllContactsAsyncTask(contactDAO).execute();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    private static class InsetNoteAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDAO contactDAO;

        private InsetNoteAsyncTask (ContactDAO contactDAO){
            this.contactDAO = contactDAO;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDAO.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDAO contactDAO;

        private UpdateNoteAsyncTask (ContactDAO contactDAO){
            this.contactDAO = contactDAO;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDAO.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Contact, Void, Void>{

        private ContactDAO contactDAO;

        private DeleteNoteAsyncTask (ContactDAO contactDAO){
            this.contactDAO = contactDAO;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDAO.delete(contacts[0]);
            return null;
        }
    }

    private static class DeleteAllContactsAsyncTask extends AsyncTask<Void, Void, Void>{

        private ContactDAO contactDAO;

        private DeleteAllContactsAsyncTask (ContactDAO contactDAO){
            this.contactDAO = contactDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDAO.deleteAllContacts();
            return null;
        }
    }
}
