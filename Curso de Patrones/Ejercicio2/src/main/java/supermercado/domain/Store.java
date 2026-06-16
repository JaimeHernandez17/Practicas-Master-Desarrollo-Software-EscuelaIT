package supermercado.domain;

/**
 * Representa una tienda física del supermercado.
 */
public class Store {
    private String code;
    private String name;
    private String address;
    private String phone;
    private String countryCode;

    public Store(String code, String name, String address, String phone, String countryCode) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.countryCode = countryCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountryCode() {
        return countryCode;
    }
}

