package undo.com.undo.impl;

import undo.com.undo.interfaces.Change;
import undo.com.undo.interfaces.Document;

public class UndoManager implements undo.com.undo.interfaces.UndoManager {

    private Document document;
    private int bufferSize;

    public UndoManager(Document document, int bufferSize){
        super();
        this.document = document;
        this.bufferSize = bufferSize;
        System.out.println("creating new UndoManager!");
    }

    @Override
    public void registerChange(Change change) {

    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canRedo() {
        return false;
    }

    @Override
    public void redo() {

    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
