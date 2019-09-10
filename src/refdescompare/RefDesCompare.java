package refdescompare;

/**
 * A utility class for sort ordering alpha-numeric reference designator strings.
 * <p>
 * When sorted as a string, reference designators with numeric portions are
 * sorted out of order, such as this: R1, R10, R2. This comparator evaluates a
 * string of digits as a numerical value and compares the two strings
 * accordingly.
 * <p>
 * Supports strings that are repeating alpha-numeric-alpha-numeric ...
 */

public class RefDesCompare
{
    /**
     * Compares its two alpha-numeric reference designator arguments for order.
     * Returns a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     * <p>
     * Comparison is case insensitive.
     * <p>
     * Gracefully handles either or both arguments being null without throwing
     * an exception.
     * 
     * @see java.util.Comparator::compare
     * 
     * @param refDes1
     *            the first reference designator to be compared.
     * @param refDes2
     *            the second reference designator to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public static int compare(String refDes1, String refDes2)
    {
        // Check for nulls
        if ((refDes1 == null) && (refDes2 == null)) return 0;
        if (refDes1 == null) return -1;
        if (refDes2 == null) return 1;

//        // Ignore case during comparison
//        refDes1 = refDes1.toUpperCase();
//        refDes2 = refDes2.toUpperCase();

        // easy short circuit
        if (refDes1.equalsIgnoreCase(refDes2)) return 0;

        int comp = 0;
        int index1 = 0;
        int index2 = 0;

        while ((index1 < refDes1.length()) && (index2 < refDes2.length()))
        {
            // extract alpha portion
            String alpha1 = "";
            while ((index1 < refDes1.length()) && Character.isAlphabetic(refDes1.charAt(index1)))
            {
                alpha1 += refDes1.charAt(index1);
                index1++;
            }

            String alpha2 = "";
            while ((index2 < refDes2.length()) && Character.isAlphabetic(refDes2.charAt(index2)))
            {
                alpha2 += refDes2.charAt(index2);
                index2++;
            }

            comp = alpha1.compareToIgnoreCase(alpha2);
            if (comp != 0) return comp;

            // extract numeric portions
            String numeric1 = "0";
            while ((index1 < refDes1.length()) && Character.isDigit(refDes1.charAt(index1)))
            {
                numeric1 += refDes1.charAt(index1);
                index1++;
            }
            int value1 = Integer.parseUnsignedInt(numeric1);

            String numeric2 = "0";
            while ((index2 < refDes2.length()) && Character.isDigit(refDes2.charAt(index2)))
            {
                numeric2 += refDes2.charAt(index2);
                index2++;
            }
            int value2 = Integer.parseUnsignedInt(numeric2);

            comp = value1 - value2;
            if (comp != 0) return comp;
        }

        // end of both strings -- they must be equal
        // (with short circuit above, we should never get here)
        return 0;
    }

}
