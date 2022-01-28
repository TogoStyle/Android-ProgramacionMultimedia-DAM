package dam.androidantoniovr.u4t8database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoListDBHelper extends SQLiteOpenHelper {

    //CREAMOS LA INSTANCIA PARA SQLITEOPENHELPER
    private static TodoListDBHelper instanceDBHelper;


    //COMPROBAMOS QUE SOLO TENGAMOS UNA INSTANCIA DE LA CLASE EN TODA LA APLICAION
    public static synchronized  TodoListDBHelper getInstance(Context context){
        if (instanceDBHelper == null){
            instanceDBHelper = new TodoListDBHelper(context.getApplicationContext());
        }
        return instanceDBHelper;
    }

    //SINGLETON
    private TodoListDBHelper(Context context){
        super(context, TodoListDBContract.DB_NAME, null, TodoListDBContract.DB_VERSION);
    }

//    public TodoListDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TodoListDBContract.Tasks.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL(TodoListDBContract.Tasks.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }



}
