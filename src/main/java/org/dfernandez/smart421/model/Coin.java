package org.dfernandez.smart421.model;


public enum Coin {

    ONE_POUND(100),
    FIFTY_PENCE(50),
    TWENTY_PENCE(20),
    TEN_PENCE(10),
    FIVE_PENCE(5),
    TWO_PENCE(2),
    ONE_PENNY(1);

    // Restriction property denomination
    private final int denomination;

    private Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
