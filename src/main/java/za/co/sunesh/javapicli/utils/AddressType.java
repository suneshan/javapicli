package za.co.sunesh.javapicli.utils;

public enum AddressType {
    PHYSICAL(1, "Physical Address"),
    BUSINESS(2, "Business Address"),
    POSTAL(3, "Postal Address");

    private int option;
    private String description;

    private AddressType(int option, String description) {
        this.option = option;
        this.description = description;
    }

    public static String getDescriptionFromOption(int option) {
        for (AddressType addressType: values()) {
            if (addressType.option == option) {
                return addressType.description;
            }
        }
        return "";
    }
}
