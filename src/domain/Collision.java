package domain;

public class Collision {

    private String value;
    private String hash;

    public Collision(String value, String hash) {
        this.value = value;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Collision{" +
                "value='" + value + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
