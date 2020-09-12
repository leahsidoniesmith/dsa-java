package edu.emory.cs.algebraic;

import java.util.Arrays;


public class LongIntegerQuiz extends LongInteger {
    public LongIntegerQuiz(LongInteger n) { super(n); }

    public LongIntegerQuiz(String n) { super(n); }

    /**
     * Adds the specific integer that has the opposite sign as this integer.
     * @param n the integer to be added with the opposite sign.
     */
    @Override
    protected void addDifferentSign(LongInteger n) {

        // set this.sign
        sign = compareAbs(n) < 0 ? n.sign : sign;

        // create result array
        int m = Math.max(digits.length, n.digits.length);
        byte[] result = new byte[m + 1];

        // subtract using subtractHelper method
        if (n.digits.length > digits.length) subtractHelper(n.digits, digits, result);
        else if (n.digits.length < digits.length) subtractHelper(digits, n.digits, result);
        else
            if (compareAbs(n) < 0) subtractHelper(n.digits, digits, result);
            else subtractHelper(digits, n.digits, result);

        // set this.digits and remove leading 0s
        digits = result;
        while(0 == result[digits.length - 1] && digits.length > 1){
            digits = Arrays.copyOf(result, digits.length-1);
        }
    }

    /**
     * Finds the difference between the longer and shorter integer.
     * @param longer the longer integer.
     * @param shorter the shorter integer.
     * @param result the difference.
     */
    protected void subtractHelper(byte[] longer, byte[] shorter, byte[] result) {
        //copy longer integer to result[]
        System.arraycopy(longer, 0, result, 0, longer.length);

        //subtract shorter integer from result[]
        for (int i = 0; i < shorter.length; i++) {
            if (result[i] < shorter[i]) {
                result[i] += 10;
                result[i + 1] -= 1;
            }
            result[i] -= shorter[i];
        }
    }
}

