package CardPackage;

public class Card {
    private int pinCode;
    private String validity;
    private String currency="RUR";

    public Card(int pinCode, String validity) {
        this.pinCode = pinCode;
        this.validity = validity;
    }
    public int getPinCode() {
        return pinCode;
    }

    public String getValidity() {
        return validity;
    }

    public String getCurrency() {
        return currency;
    }
}