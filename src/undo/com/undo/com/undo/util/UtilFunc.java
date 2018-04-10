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

    public static byte[] insertBytes(byte[] byteArray, int startIndex, String inputString){
        //read byte[] from default file
        byte[] output = new byte[byteArray.length + inputString.length()];
        byte[] inputStr = inputString.getBytes();
        for(int i = 0; i < output.length - inputString.length(); i++){

            if(i < startIndex){
                output[i] = byteArray[i];
            }else{
                output[i + inputString.length()] = byteArray[i];
            }
        }
        System.arraycopy(inputStr, 0, output, startIndex, inputStr.length);

        return output;
    }
}
