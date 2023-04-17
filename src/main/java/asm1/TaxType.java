package asm1;

public enum TaxType {
    TAX_FREE(0.0),
    NORMAL_TAX(0.1),
    LUXURY_TAX(0.2);

    private double rate;

    TaxType(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}