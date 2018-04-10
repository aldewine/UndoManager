package undo;


import org.junit.Test;
import static org.junit.Assert.*;
import undo.com.undo.impl.Change;
import undo.com.undo.impl.Document;
import undo.com.undo.impl.UndoManager;
import undo.com.undo.impl.UndoManagerFactory;

import java.io.IOException;

public class TestCases {

    @Test
    public void contentTest(){
        try {
            Document doc = new Document("resources/testFile.txt");
            UndoManager undoManager = (new UndoManagerFactory()).createUndoManager(doc, 5);
            undoManager.registerChange(new Change("Sercan",10,"I"));

            assertEquals("Lorem ipsuSercanm dolor sit amet, consectetur.", undoManager.getFileAsString());

            undoManager.undo();

            assertEquals("Lorem ipsum dolor sit amet, consectetur.", undoManager.getFileAsString());

            undoManager.registerChange(new Change(" amet, ", 21, "D"));

            assertEquals("Lorem ipsum dolor sitconsectetur.", undoManager.getFileAsString());

            undoManager.undo();

            assertEquals("Lorem ipsum dolor sit amet, consectetur.", undoManager.getFileAsString());

            undoManager.redo();

            assertEquals("Lorem ipsum dolor sitconsectetur.", undoManager.getFileAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void undoRedoTest(){
        try {
            Document doc = new Document("resources/testFile.txt");
            UndoManager undoManager = (new UndoManagerFactory()).createUndoManager(doc, 5);
            undoManager.registerChange(new Change("Sercan",10,"I"));

            assertEquals("Lorem ipsuSercanm dolor sit amet, consectetur.", undoManager.getFileAsString());
            assertTrue(undoManager.canUndo());

            undoManager.undo();

            assertFalse(undoManager.canUndo());
            assertTrue(undoManager.canRedo());
            assertEquals("Lorem ipsum dolor sit amet, consectetur.", undoManager.getFileAsString());

            undoManager.registerChange(new Change(" amet, ", 21, "D"));

            assertFalse(undoManager.canRedo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testException(){
        try {
            Document doc = new Document("resources/testFile.txt");
            UndoManager undoManager = (new UndoManagerFactory()).createUndoManager(doc, 5);
            undoManager.registerChange(new Change("Sercan",50,"I"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionForDelete(){
        try {
            Document doc = new Document("resources/testFile.txt");
            UndoManager undoManager = (new UndoManagerFactory()).createUndoManager(doc, 5);
            undoManager.registerChange(new Change("Sercan",10,"D"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
