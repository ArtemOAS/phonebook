package data;

import java.util.List;

/**
 * Created by Artem on 28.02.2016.
 */
public class Phone {
    private String number;
    private PhoneType phoneType;

    public Phone(String number, PhoneType phoneType) {
        this.number = number;
        this.phoneType = phoneType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (number != null ? !number.equals(phone.number) : phone.number != null) return false;
        return phoneType == phone.phoneType;

    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (phoneType != null ? phoneType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                ", phoneType=" + phoneType +
                '}';
    }
}
