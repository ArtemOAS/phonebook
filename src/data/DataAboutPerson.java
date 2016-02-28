package data;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Artem on 28.02.2016.
 */
public class DataAboutPerson {
    private String firstName;
    private String lastName;
    private Collection<DataPhoneNumber> phoneNumbers;
    private Collection<DataNumberType> numberTypes;
    private String email;
    private Date birthday;

    public DataAboutPerson(String firstName, String lastName, Collection<DataPhoneNumber> phoneNumbers, Collection<DataNumberType> numberTypes, String email, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.numberTypes = numberTypes;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Collection<DataPhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Collection<DataNumberType> getNumberTypes() {
        return numberTypes;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAboutPerson that = (DataAboutPerson) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phoneNumbers != null ? !phoneNumbers.equals(that.phoneNumbers) : that.phoneNumbers != null) return false;
        if (numberTypes != null ? !numberTypes.equals(that.numberTypes) : that.numberTypes != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return birthday != null ? birthday.equals(that.birthday) : that.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        result = 31 * result + (numberTypes != null ? numberTypes.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
