package dam.androidantoniovr.u4t8database.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dam.androidantoniovr.u4t8database.model.Task;

public class TodoListDBManager {
    private TodoListDBHelper todoListDBHelper;

    public TodoListDBManager(Context context) {
        todoListDBHelper = TodoListDBHelper.getInstance(context);
    }

    //OPERACIONES

    //CREAMOS NUEVA FILA
    public void insert(String todo, String when, String description, String priority, String status) {
        //ABRIMOS LA BASE DE DATOS ES LECTURA Y ESCRITURA
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null) {
            ContentValues contentValue = new ContentValues();

            contentValue.put(TodoListDBContract.Tasks.TODO, todo);
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH, when);
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION, description);
            //TODO - agregamos prioridad y status al abrir la base de datos en lectura y escritura
            contentValue.put(TodoListDBContract.Tasks.PRIORITY, priority);
            contentValue.put(TodoListDBContract.Tasks.STATUS, status);

            sqLiteDatabase.insert(TodoListDBContract.Tasks.TABLE_NAME, null, contentValue);

//            sqLiteDatabase.close();
        }
    }

    //OBTENEMOS LA INFORMACION DE LA TABLA TASKS
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        //ABRIMOS LA BASE DE DATOS EN LECTURA
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();

        if (sqLiteDatabase != null) {
            String[] projection = new String[]{TodoListDBContract.Tasks._ID,
                    TodoListDBContract.Tasks.TODO,
                    TodoListDBContract.Tasks.TO_ACCOMPLISH,
                    TodoListDBContract.Tasks.DESCRIPTION,
                    //TODO - agregamos prioridad y status al abrir la base de datos en lectura
                    TodoListDBContract.Tasks.PRIORITY,
                    TodoListDBContract.Tasks.STATUS};

            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME,
                    projection, //COLUMNAS A DEVOLVER
                    null, //SIN CLAUSULA WHERE
                    null, //SIN VALORES PARA LA CLUSULA WHERE
                    null, //NO AGRUPAMOS LAS FILAS
                    null, //NO FULTRAMOS POR GRUPOS DE FILAS
                    null); //NO ORDENAMOS LAS FILAS


            if (cursorTodoList != null) {
                //OBTENEMOS EL INDICE REQUERIDO SEGUN LA COLUMNA
                int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);
                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int to_AccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                //TODO - obtenemos el indice requerido de los campos agregados.
                int priorityIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PRIORITY);
                int statusIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);

                //LEEMOS LA INFORMACIÓN Y LA AÑADIMOS AL ARRAYLIST
                while (cursorTodoList.moveToNext()) {
                    Task task = new Task(
                            cursorTodoList.getInt(_idIndex),
                            cursorTodoList.getString(todoIndex),
                            cursorTodoList.getString(to_AccomplishIndex),
                            cursorTodoList.getString(descriptionIndex),
                            //TODO - agregamos los datos obtenidos de prioridad y status al arraylist
                            cursorTodoList.getString(priorityIndex),
                            cursorTodoList.getString(statusIndex));

                    taskList.add(task);
                }
                //CERRAMOS EL CURSOR
                cursorTodoList.close();
            }
        }
        return taskList;
    }




    //TODO - Borramos la Task.
    public void deleteTask(int idTask){
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null){
            sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, TodoListDBContract.Tasks._ID + " = ?", new String[]{String.valueOf(idTask)});
        }
    }

    //TODO - Editamos la Task
    public void editTask(Task task){
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase!=null){
            deleteTask((task.get_id()));
            ContentValues contentValue = new ContentValues();

            contentValue.put(TodoListDBContract.Tasks._ID, task.get_id());
            contentValue.put(TodoListDBContract.Tasks.TODO, task.getTodo());
            contentValue.put(TodoListDBContract.Tasks.TO_ACCOMPLISH, task.getToAccomplish());
            contentValue.put(TodoListDBContract.Tasks.DESCRIPTION, task.getDescription());
            //TODO - agregamos prioridad y status al abrir la base de datos en lectura y escritura
            contentValue.put(TodoListDBContract.Tasks.PRIORITY, task.getPriority());
            contentValue.put(TodoListDBContract.Tasks.STATUS, task.getStatus());

            sqLiteDatabase.insert(TodoListDBContract.Tasks.TABLE_NAME, null, contentValue);
        }
    }

    //TODO - métodos encargados de gestionar el filtrado segun la opcion seleccionada
    public ArrayList<Task> notStarted(){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();
        if (sqLiteDatabase!=null){
            String[] projection = new String[]{TodoListDBContract.Tasks._ID,
                    TodoListDBContract.Tasks.TODO,
                    TodoListDBContract.Tasks.TO_ACCOMPLISH,
                    TodoListDBContract.Tasks.DESCRIPTION,
                    TodoListDBContract.Tasks.PRIORITY,
                    TodoListDBContract.Tasks.STATUS};

            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME,
                    projection, //COLUMNAS A DEVOLVER
                    TodoListDBContract.Tasks.STATUS + " = ?", //SIN CLAUSULA WHERE
                    new String[]{"Not Started"}, //CLAUSULA WHERE PARA FILTRAR POR NOT STARTED
                    null, //NO AGRUPAMOS LAS FILAS
                    null, //NO FULTRAMOS POR GRUPOS DE FILAS
                    null); //NO ORDENAMOS LAS FILAS

            if (cursorTodoList != null) {
                //OBTENEMOS EL INDICE REQUERIDO SEGUN LA COLUMNA
                int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);
                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int to_AccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                int priorityIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PRIORITY);
                int statusIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);

                //LEEMOS LA INFORMACIÓN Y LA AÑADIMOS AL ARRAYLIST
                while (cursorTodoList.moveToNext()) {
                    Task task = new Task(
                            cursorTodoList.getInt(_idIndex),
                            cursorTodoList.getString(todoIndex),
                            cursorTodoList.getString(to_AccomplishIndex),
                            cursorTodoList.getString(descriptionIndex),
                            cursorTodoList.getString(priorityIndex),
                            cursorTodoList.getString(statusIndex));

                    taskArrayList.add(task);
                }
                //CERRAMOS EL CURSOR
                cursorTodoList.close();
            }
        }
        return taskArrayList;
    }

    public ArrayList<Task> completed(){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();
        if (sqLiteDatabase!=null){
            String[] projection = new String[]{TodoListDBContract.Tasks._ID,
                    TodoListDBContract.Tasks.TODO,
                    TodoListDBContract.Tasks.TO_ACCOMPLISH,
                    TodoListDBContract.Tasks.DESCRIPTION,
                    TodoListDBContract.Tasks.PRIORITY,
                    TodoListDBContract.Tasks.STATUS};

            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME,
                    projection, //COLUMNAS A DEVOLVER
                    TodoListDBContract.Tasks.STATUS + " = ?", //SIN CLAUSULA WHERE
                    new String[]{"Completed"}, //CLAUSULA WHERE PARA FILTRAR POR COMPLETED
                    null, //NO AGRUPAMOS LAS FILAS
                    null, //NO FULTRAMOS POR GRUPOS DE FILAS
                    null); //NO ORDENAMOS LAS FILAS

            if (cursorTodoList != null) {
                //OBTENEMOS EL INDICE REQUERIDO SEGUN LA COLUMNA
                int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);
                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int to_AccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                int priorityIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PRIORITY);
                int statusIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);

                //LEEMOS LA INFORMACIÓN Y LA AÑADIMOS AL ARRAYLIST
                while (cursorTodoList.moveToNext()) {
                    Task task = new Task(
                            cursorTodoList.getInt(_idIndex),
                            cursorTodoList.getString(todoIndex),
                            cursorTodoList.getString(to_AccomplishIndex),
                            cursorTodoList.getString(descriptionIndex),
                            cursorTodoList.getString(priorityIndex),
                            cursorTodoList.getString(statusIndex));

                    taskArrayList.add(task);
                }
                //CERRAMOS EL CURSOR
                cursorTodoList.close();
            }
        }
        return taskArrayList;
    }

    public ArrayList<Task> inProgress(){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getReadableDatabase();
        if (sqLiteDatabase!=null){
            String[] projection = new String[]{TodoListDBContract.Tasks._ID,
                    TodoListDBContract.Tasks.TODO,
                    TodoListDBContract.Tasks.TO_ACCOMPLISH,
                    TodoListDBContract.Tasks.DESCRIPTION,
                    TodoListDBContract.Tasks.PRIORITY,
                    TodoListDBContract.Tasks.STATUS};

            Cursor cursorTodoList = sqLiteDatabase.query(TodoListDBContract.Tasks.TABLE_NAME,
                    projection, //COLUMNAS A DEVOLVER
                    TodoListDBContract.Tasks.STATUS + " = ?", //SIN CLAUSULA WHERE
                    new String[]{"In Progress"}, //CLAUSULA WHERE PARA FILTRAR POR IN PROGRESS
                    null, //NO AGRUPAMOS LAS FILAS
                    null, //NO FULTRAMOS POR GRUPOS DE FILAS
                    null); //NO ORDENAMOS LAS FILAS

            if (cursorTodoList != null) {
                //OBTENEMOS EL INDICE REQUERIDO SEGUN LA COLUMNA
                int _idIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks._ID);
                int todoIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TODO);
                int to_AccomplishIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.TO_ACCOMPLISH);
                int descriptionIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.DESCRIPTION);
                int priorityIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.PRIORITY);
                int statusIndex = cursorTodoList.getColumnIndexOrThrow(TodoListDBContract.Tasks.STATUS);

                //LEEMOS LA INFORMACIÓN Y LA AÑADIMOS AL ARRAYLIST
                while (cursorTodoList.moveToNext()) {
                    Task task = new Task(
                            cursorTodoList.getInt(_idIndex),
                            cursorTodoList.getString(todoIndex),
                            cursorTodoList.getString(to_AccomplishIndex),
                            cursorTodoList.getString(descriptionIndex),
                            cursorTodoList.getString(priorityIndex),
                            cursorTodoList.getString(statusIndex));

                    taskArrayList.add(task);
                }
                //CERRAMOS EL CURSOR
                cursorTodoList.close();
            }
        }
        return taskArrayList;
    }
 //TODO - métodos encargados de gestionar el filtrado de borrado segun la opcion seleccionada
    public void deleteCompleted(){
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();

        if (sqLiteDatabase != null){
            sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, TodoListDBContract.Tasks.STATUS + " = ?", new String[]{"Completed"});
        }
    }

    public void dropTable(){
        SQLiteDatabase sqLiteDatabase = todoListDBHelper.getWritableDatabase();
        if (sqLiteDatabase != null){
            sqLiteDatabase.delete(TodoListDBContract.Tasks.TABLE_NAME, null, null);
        }
    }

    public void close() {
        todoListDBHelper.close();
    }
}
