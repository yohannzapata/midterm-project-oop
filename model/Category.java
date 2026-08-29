package model;

public enum Category {

    CLOTHING("Clothing"),
    ELECTRONICS("Electronics"),
    ENTERTAINMENT("Entertainment");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static String listAll() {
        StringBuilder builder = new StringBuilder();
        for (Category category : values()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(category.label);
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return label;
    }
}
