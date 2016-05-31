package pnrs.rtrk.studenti;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static pnrs.rtrk.studenti.R.id.firstnamefind;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static StudentAdapter studentAdapter;
    private ListView listView;
    private EditText firstN, lastN, indexN, indexFind;
    private View button, buttonFind;
    private TextView firstText, lastText, indText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentAdapter = new StudentAdapter(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(MainActivity.studentAdapter);
        listView.setOnItemClickListener(this);

        firstText = (TextView) findViewById(R.id.firstnamefind);
        lastText = (TextView) findViewById(R.id.lastnamefind);
        indText = (TextView) findViewById(R.id.indexnumberfind);

        button = (Button) findViewById(R.id.button);
        buttonFind = (Button) findViewById(R.id.button2);
        firstN = (EditText) findViewById(R.id.editText);
        lastN = (EditText) findViewById(R.id.editText2);
        indexN = (EditText) findViewById(R.id.editText3);
        indexFind = (EditText) findViewById(R.id.editText4);
        button.setOnClickListener(this);
        buttonFind.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Student student;

        String first;
        String last;
        String index;

        if (button == view && !firstN.getText().toString().equals("") && !lastN.getText().toString().equals("")
                && !indexN.getText().toString().equals("")) {
            first = firstN.getText().toString();
            last = lastN.getText().toString();
            index = indexN.getText().toString();

            lastN.setText("");
            firstN.setText("");
            indexN.setText("");

            student = new Student(first, last, index);

            studentAdapter.add(student);
            ContentValues values = new ContentValues();
            ContentResolver resolver = getContentResolver();
            values.put("FirstName", student.getFirstName());
            values.put("LastName", student.getLastName());
            values.put("IndexNumber", student.getIndexNumber());
            resolver.insert(Uri.parse("content://pnrs.rtrk.mycontentprovider/student"), values);

        } else if (buttonFind == view && !indexFind.getText().toString().equals("")) {
            ContentResolver resolver = getContentResolver();
            String indexNum;
            indexNum = indexFind.getText().toString();
            Log.d("main index", "" + indexNum);
            Cursor cursor;
            cursor = resolver.query(Uri.parse("content://pnrs.rtrk.mycontentprovider/student"), null, "IndexNumber=?", new String[]{indexNum}
                    , null);

            String ime, prezime, indeks;
            Log.d("main cursor", "" + cursor.getCount());

            cursor.moveToFirst();

            if (cursor.getCount() != 0) {
                firstText.setText("");
                lastText.setText("");
                indText.setText("");
                ime = cursor.getString(cursor.getColumnIndex("FirstName"));
                prezime = cursor.getString(cursor.getColumnIndex("LastName"));
                indeks = cursor.getString(cursor.getColumnIndex("IndexNumber"));
                firstText.setText(ime);
                lastText.setText(prezime);
                indText.setText(indeks);
                indexFind.setText("");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Student student = (Student) studentAdapter.getItem(i);
        Log.d("main", "" + student.getIndexNumber());
        ContentResolver resolver = getContentResolver();
        resolver.delete(Uri.parse("content://pnrs.rtrk.mycontentprovider/student"), student.getIndexNumber(), null);
        studentAdapter.remove(i);
    }


}
