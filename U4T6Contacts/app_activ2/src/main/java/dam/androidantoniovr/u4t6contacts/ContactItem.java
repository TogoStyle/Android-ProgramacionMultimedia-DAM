package dam.androidantoniovr.u4t6contacts;

import android.net.Uri;

import java.io.Serializable;

//TODO - Clase ContactItem
//CLASE DEL OBJETO CONTACTO, CON ATRIBUTOS, CONSTRUCTORES Y GETTER Y SETTERS
public class ContactItem  implements Serializable {

    private String Id;
    private String name;
    private String number;
    private Uri photo;
    private String contactId;
    private String lookup;
    private String raw;
    private String phoneType;

    public ContactItem() {
    }

    public ContactItem(String id, String name, String number, Uri photo,String lookup, String raw, String phoneType, String contactId) {
        Id = id;
        this.name = name;
        this.number = number;
        this.photo = photo;
        this.lookup = lookup;
        this.raw = raw;
        this.phoneType = phoneType;
    }

    public ContactItem(String id, String name, String number, Uri photo) {
        Id = id;
        this.name = name;
        this.number = number;
        this.photo = photo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}
