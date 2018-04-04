package undo.com.undo.com.undo.util;

public class UtilFunc {
    public static byte[] moveBytesLeft(byte[] byteArray, int startIndex, int shiftSize) {
        if (shiftSize > startIndex) {
            return byteArray;
        }

        for (int i = startIndex; i+shiftSize < byteArray.length; i++) {
            byteArray[i] = byteArray[i + shiftSize];
        }

        byte[] output = new byte[byteArray.length - shiftSize];
        System.arraycopy(byteArray, 0, output, 0, output.length);

        return output;
    }
}
