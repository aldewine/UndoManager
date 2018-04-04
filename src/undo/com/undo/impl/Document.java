package undo.com.undo.impl;

import undo.com.undo.com.undo.util.UtilFunc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Document implements undo.com.undo.interfaces.Document {

    private byte[] file;

    public Document(String strPath) throws IOException {
        Path path = Paths.get(strPath);
        file = Files.readAllBytes(path);

        System.out.println("file has been read. size:" + file.length);
    }

    @Override
    public void delete(int pos, String s) {

        //Read byte[] from default file
        byte[] readArray = new byte[s.length()];
        System.arraycopy(file, pos, readArray, 0, s.length());
        String readStr = new String(readArray, StandardCharsets.UTF_8);

        //Check string equality with input
        if(s.equals(readStr)){
            //since this is a delete operation move bytes by string length
            file = UtilFunc.moveBytesLeft(file, pos, s.length());
        }

        byte[] result = new byte[file.length];
        System.arraycopy(file, 0, result, 0, file.length);
        readStr = new String(result, StandardCharsets.UTF_8);
        System.out.println("delete operation is finished! result:" + readStr);
    }

    @Override
    public void insert(int pos, String s) {
        
    }

    @Override
    public void setDot(int pos) {

    }
}
