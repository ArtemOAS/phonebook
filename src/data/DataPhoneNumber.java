package data;

/**
 * Created by Artem on 28.02.2016.
 */
public class DataPhoneNumber {
    private String numberPhone;

    public DataPhoneNumber(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPhoneNumber that = (DataPhoneNumber) o;

        return numberPhone != null ? numberPhone.equals(that.numberPhone) : that.numberPhone == null;

    }

    @Override
    public int hashCode() {
        return numberPhone != null ? numberPhone.hashCode() : 0;
    }
}
