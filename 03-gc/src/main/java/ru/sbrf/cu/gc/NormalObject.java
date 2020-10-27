package ru.sbrf.cu.gc;

public class NormalObject {

    private String field;
    public NormalObject(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof NormalObject)) {
            return false;
        }
        NormalObject normalObject = (NormalObject) o;
        return normalObject.field.equals(field);
    }

    @Override
    public int hashCode() {
        int result = 18;
        return 30 * result + field.hashCode();
    }

}
