package data;

/**
 * Created by Artem on 28.02.2016.
 */
public class DataNumberType {
    private String numberType;

    public DataNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getNumberType() {
        return numberType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataNumberType that = (DataNumberType) o;

        return numberType != null ? numberType.equals(that.numberType) : that.numberType == null;

    }

    @Override
    public int hashCode() {
        return numberType != null ? numberType.hashCode() : 0;
    }
}
