package util;

public class LocatorLivat {
    private String value;
    private final String name;
    private String type;
    private String description;

    public LocatorLivat(String n) {
        name = n;
    }

    public void setValue(String v) {
        value = v;
    }

    public String getValue() {
        return this.value;
    }

    public void setType(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    void setDescription(String d) {
        description = d;
    }

    public String getDescription() {
        return description;
    }
}

