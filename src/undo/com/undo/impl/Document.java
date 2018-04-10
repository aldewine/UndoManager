package undo.com.undo.impl;

import undo.com.undo.com.undo.util.UtilFunc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Document implements undo.com.undo.interfaces.Document {

    private byte[] file;
    private int position;

    public Document(String strPath) throws IOException {
        Path path = Paths.get(strPath);
        file = Files.readAllBytes(path);
        position = 0;
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
        }else{
            throw new IllegalStateException("the document doesn't have " + s + " as " + pos + ".");
        }

        byte[] result = new byte[file.length];
        System.arraycopy(file, 0, result, 0, file.length);
    }

    @Override
    public void insert(int pos, String s) {
        if(pos > file.length){
            throw new IllegalStateException(pos + " is an illegal position.");
        }
        file = UtilFunc.insertBytes(file, pos, s);
    }

    @Override
    public void setDot(int pos) {
        if(pos > file.length){
            throw new IllegalStateException(pos + " is an illegal position.");
        }
        this.position = pos;
    }

    public byte[] getFile() {
        return file;
    }

    public String getAsString(){
        String readStr = new String(file, StandardCharsets.UTF_8);
        return readStr;
    }
}
