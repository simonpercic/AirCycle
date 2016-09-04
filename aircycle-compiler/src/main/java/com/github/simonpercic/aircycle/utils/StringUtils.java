package com.github.simonpercic.aircycle.utils;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class StringUtils {

    private StringUtils() {
        // no instance
    }

    /**
     * Returns <tt>true</tt> if the charSequence is null or of zero length.
     *
     * @param charSequence char sequence
     * @return <tt>true</tt> if charSequence is null or of zero length
     */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
