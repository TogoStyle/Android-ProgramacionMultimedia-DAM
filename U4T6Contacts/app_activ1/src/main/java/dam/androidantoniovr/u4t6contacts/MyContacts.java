package dam.androidantoniovr.u4t6contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class MyContacts {

    private ArrayList<ContactItem> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private ArrayList<ContactItem> getContacts() {
        ArrayList<ContactItem> contactList = new ArrayList<>();

        //Da el punto de entrada al content provider del dispositivo.
        ContentResolver contentResolver = context.getContentResolver();

        //TODO - 10.1 Modifica Consulta
        String[] projection = new String[]{ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.Data.PHOTO_THUMBNAIL_URI};

        String selectionFiler = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        //permite llevar a cabo la consulta a un Content provider que devolviendo un cursor, podremos leer el resultado de la consulta
        Cursor contactsCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                projection,
                selectionFiler,
                null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");

        //TODO - Código necesario para mostar y gestionar toda la información de cada contacto
        if (contactsCursor != null) {
            int nameIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int contactId = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID);
            int lookupKey = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.LOOKUP_KEY);
            int rawContact = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID);
            int phoneType = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);
            int photoUri = contactsCursor.getColumnIndexOrThrow(ContactsContract.Data.PHOTO_THUMBNAIL_URI);

            while (contactsCursor.moveToNext()) {

                String name = contactsCursor.getString(nameIndex);
                String number = contactsCursor.getString(numberIndex);
                String contactid = contactsCursor.getString(contactId);
                String lookUp = contactsCursor.getString(lookupKey);
                String raw = contactsCursor.getString(rawContact);
                String type = contactsCursor.getString(phoneType);
                Uri pUri = Uri.parse(contactsCursor.getString(photoUri));

                ContactItem contact = new ContactItem(contactid, name, number, pUri);

                contactList.add(contact);
            }
            contactsCursor.close();
        }
        return contactList;
    }

    public ContactItem getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {
        return myDataSet.size();
    }
}
