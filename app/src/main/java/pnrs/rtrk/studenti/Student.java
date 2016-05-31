package pnrs.rtrk.studenti;

/**
 * Created by student on 11.5.2016.
 */
public class Student {

    public String FirstName;
    public String LastName;
    public String IndexNumber;

    public Student(String firstName, String lastName, String indexNumber) {
        FirstName = firstName;
        LastName = lastName;
        IndexNumber = indexNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getIndexNumber() {
        return IndexNumber;
    }
}
