package undo.com.undo.impl;

import undo.com.undo.interfaces.Document;

public class UndoManagerFactory implements undo.com.undo.interfaces.UndoManagerFactory {

    @Override
    public UndoManager createUndoManager(Document doc, int bufferSize) {
        UndoManager undoManager = new undo.com.undo.impl.UndoManager(doc, bufferSize);
        return undoManager;
    }
}
