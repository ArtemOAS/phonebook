package data;

import java.util.List;

/**
 * Created by Artem on 28.02.2016.
 */
public class Phone {
    private long number;
    PhoneType phoneType;

    public Phone(long number, PhoneType phoneType) {
        this.number = number;
        this.phoneType = phoneType;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
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

        if (number != phone.number) return false;
        return phoneType == phone.phoneType;

    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (phoneType != null ? phoneType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                ", phoneType=" + phoneType +
                '}';
    }
}
