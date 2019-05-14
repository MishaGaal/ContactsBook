package mike.galitsky.contactsboock;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Name;

    @ColumnInfo(name = "Surname")
    private String LastName;

    private String Phone;


    public Contact() {
    }

    public Contact(String name, String lastName, String phone) {
        Name = name;
        LastName = lastName;
        Phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
