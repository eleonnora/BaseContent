package pnrs.rtrk.studenti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 11.5.2016.
 */
public class Database extends SQLiteOpenHelper {


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE student (FirstName TEXT, LastName TEXT, IndexNumber TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("student", null, values);
        close();
    }

    public Student[] readStudents() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("student", null, null, null, null, null, null, null);

        int i = 0;
        Student[] students = new Student[cursor.getCount()];

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String FirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
            String LastName = cursor.getString(cursor.getColumnIndex("LastName"));
            String IndexNumber = cursor.getString(cursor.getColumnIndex("IndexNumber"));

            Student student = new Student(FirstName, LastName, IndexNumber);

            students[i] = student;
            i++;
        }

        return students;
    }

    public Student readStudentByIndex(String index) {
        SQLiteDatabase db = getReadableDatabase();
        Student student;

        Cursor cursor = db.query("student", null, "IndexNumber=" + index, null, null, null, null, null);

        if (cursor.getCount() != 0) {
            String FirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
            String LastName = cursor.getString(cursor.getColumnIndex("LastName"));
            String IndexNumber = cursor.getString(cursor.getColumnIndex("IndexNumber"));

            student = new Student(FirstName, LastName, IndexNumber);

            return student;
        } else {
            return null;
        }
    }

    public void deleteStudent(String index) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("student", "IndexNumber=?", new String[]{index});
        close();
    }
}
