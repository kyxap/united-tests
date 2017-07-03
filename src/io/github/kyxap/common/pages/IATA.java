package io.github.kyxap.common.pages;

/*
International Air Transport Association airport code (IATA)
 */
public enum IATA {
    LAX("LAX", "Los Angeles, CA, US"),
    PDX("PDX", "Portland, OR, US");

    private final String s;
    private final String l;

    IATA(String s, String l) {
        this.s = s;
        this.l = l;
    }

    public String getShort() {
        return s;
    }

    public String getFull() {
        return l;
    }
}
