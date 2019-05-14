package mike.galitsky.contactsboock;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Contact.class, version = 2, exportSchema = false)
public abstract class ContactsBase extends RoomDatabase {
    private static ContactsBase instance;
    public abstract ContactDAO contactDAO();

    public static synchronized ContactsBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactsBase.class, "Contacts_base")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulationAsyncTask(instance).execute();
        }
    };

    private static class PopulationAsyncTask extends AsyncTask<Void, Void, Void>{

        private ContactDAO contactDAO;

        private PopulationAsyncTask(ContactsBase db){
             contactDAO = db.contactDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDAO.insert(new Contact("Mike", "Galitsky",  "+380972363550"));
            contactDAO.insert(new Contact("Alys", "Daeneris",  "+380972343423"));
            contactDAO.insert(new Contact("Done", "Randger",  "+3804723435450"));
            return null;
        }
    }
}
