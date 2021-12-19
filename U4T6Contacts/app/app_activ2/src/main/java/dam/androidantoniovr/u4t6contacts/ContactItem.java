package dam.androidantoniovr.u4t6contacts;

import android.net.Uri;

//TODO - Clase ContactItem
public class ContactItem {

    private String Id;
    private String name;
    private String number;
    private Uri photo;
    private String lookup;
    private int raw;
    private String phoneType;

    public ContactItem() {
    }

    public ContactItem(String id, String name, String number, Uri photo,String lookup, int raw, String phoneType) {
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

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}
