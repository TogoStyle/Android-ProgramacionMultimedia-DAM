package dam.androidantoniovr.u4t8database.data;

public final class TodoListDBContract {

    //NOMBRE DE LA BASE DE DATOS
    public static final String DB_NAME = "TODOLIST.DB";
    //VERSION DE LA BASE DE DATOS
    public static final int DB_VERSION = 1;

    //CREAMOS CONSTRUCTOR PRIVADO PARA EVITAR QUE ALGUIEN INSTANCIE LA CLASE
    private TodoListDBContract(){}

    //CREAMOS UNA CLASE ANIDADA QUE DEFINE EL CONTENIDO DE LA TABLA TASKS
    public static class Tasks{
        //NOMBRE DE LA TABLA
        public static final String TABLE_NAME = "TASKS";

        //NOMBRE DE LAS COLUMNAS
        public static final String _ID = "_id";
        public static final String TODO = "todo";
        public static final String TO_ACCOMPLISH = "to_accomplish";
        public static final String DESCRIPTION = "description";

        //CREATE_TABLE SQL String
        public static final String CREATE_TABLE = "CREATE TABLE " + TodoListDBContract.Tasks.TABLE_NAME
                + " ("
                + TodoListDBContract.Tasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TodoListDBContract.Tasks.TODO + " TEXT NOT NULL, "
                + TodoListDBContract.Tasks.TO_ACCOMPLISH + " TEXT, "
                + TodoListDBContract.Tasks.DESCRIPTION + " TEXT"
                + ");";

        public static final  String DELETE_TABLE  = "DROP TABLE IF EXISTS " + TodoListDBContract.Tasks.TABLE_NAME;
    }

}
