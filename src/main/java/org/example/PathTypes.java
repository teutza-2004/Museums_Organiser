package org.example;

public enum PathTypes {
    GROUPS("groups"),
    LISTENER("listener"),
    MUSEUMS("museums");

    private final String value;

    PathTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static PathTypes getType(String input) {
        for (PathTypes type : values()) {
            if (type.getValue().equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
