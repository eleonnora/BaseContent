package pnrs.rtrk.studenti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student on 12.5.2016.
 */
public class StudentAdapter extends BaseAdapter {

    public Context con;
    public ArrayList<Student> mStudents;

    public StudentAdapter(Context t) {
        mStudents = new ArrayList<Student>();
        con = t;
    }

    public void add(Student object) {
        mStudents.add(object);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mStudents.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mStudents.size();
    }

    @Override
    public Object getItem(int position) {
        return mStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater Inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Inflater.inflate(R.layout.list_item, null);

            TextView first = (TextView) view.findViewById(R.id.firstname);
            TextView last = (TextView) view.findViewById(R.id.lastname);
            TextView ind = (TextView) view.findViewById(R.id.indexnumber);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.firstName = first;
            viewHolder.lastName = last;
            viewHolder.indexNumber = ind;

            view.setTag(viewHolder);
        }

        Student student = (Student) getItem(i);

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.firstName.setText(student.getFirstName());
        holder.lastName.setText(student.getLastName());
        holder.indexNumber.setText(student.getIndexNumber());

        return view;
    }


}
