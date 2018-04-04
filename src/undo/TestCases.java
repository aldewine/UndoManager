package undo;

import org.junit.jupiter.api.Test;
import undo.com.undo.impl.Document;
import undo.com.undo.impl.UndoManager;
import undo.com.undo.impl.UndoManagerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestCases {

    @Test
    public void readDoc(){
        System.out.println("****** Test start ******");
        try {
            Document doc = new Document("resources/testFile.txt");
            UndoManager undoManager = (new UndoManagerFactory()).createUndoManager(doc, 5);
            undoManager.getDocument().delete(6, "ipsum");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
