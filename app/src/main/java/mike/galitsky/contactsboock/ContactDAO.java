package mike.galitsky.contactsboock;


import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDAO {
    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contacts")
    void deleteAllContacts();

    @Query("SELECT * FROM Contacts ORDER BY Name")
    LiveData<List<Contact>> getAllContacts();
}
