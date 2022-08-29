package nvs.alg2ir.concurrent;

/**
 * Petite classe pourvue de deux methodes simples
 *
 * Exemple inspire par Thinking in Java, 3rd Edition, Beta Copyright (c)2002 by
 * Bruce Eckel www.BruceEckel.com
 */
public class ToujoursPair {

    private int i = 0;

    public void nextI() {
        ++i;
        ++i;
    }

    public int getI() {
        return i;
    }
}
