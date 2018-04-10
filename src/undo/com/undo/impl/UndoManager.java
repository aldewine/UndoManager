package undo.com.undo.impl;

import undo.com.undo.interfaces.Change;
import undo.com.undo.interfaces.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class UndoManager implements undo.com.undo.interfaces.UndoManager {

    private Document document;
    private int bufferSize;
    private Deque<Change> undoList;
    private Deque<Change> redoList;

    public UndoManager(Document document, int bufferSize){
        //initialize all variables for undoManager
        super();
        this.document = document;
        this.bufferSize = bufferSize;
        this.undoList = new ArrayDeque<Change>();
        this.redoList = new ArrayDeque<Change>();
    }

    @Override
    public void registerChange(Change change) {
        //check if we exceeded bufferSize
        if(undoList.size() > bufferSize){
            //if exceeded remove first entered change
            undoList.removeFirst();
        }
        //Add new change to undoList
        undoList.addLast(change);

        //since registered new change, clear redoList
        redoList.clear();

        change.apply(document);
    }

    @Override
    public boolean canUndo() {
        //check if there is registered change
        if(undoList.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public void undo() {
        if(!canUndo()){
            throw new IllegalStateException("the manager is in a state that does not allow an undo");
        }
        //add undo Change to redoList so that can be used for redo in future
        redoList.addLast(undoList.getLast());
        //remove the last entered change from undoList
        undoList.removeLast();
        redoList.getLast().revert(document);
    }

    @Override
    public boolean canRedo() {
        //check if redoList has any registered Changes
        if(redoList.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public void redo() {
        if(!canRedo()){
            throw new IllegalStateException("the manager is in a state that does not allow an redo");
        }
        undoList.addLast(redoList.getLast());
        redoList.removeLast();
        undoList.getLast().apply(document);

    }

    public void save(String path){
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(document.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileAsString(){
        return document.getAsString();
    }
}
