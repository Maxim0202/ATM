package Uniform;


public enum Currency {
    RUR("RUR"),
    USD("USD"),
    EUR("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}