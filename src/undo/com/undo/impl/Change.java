package undo.com.undo.impl;

import undo.com.undo.interfaces.Document;

public class Change implements undo.com.undo.interfaces.Change {

    private String changeString;
    private int changePos;
    private String type;

    public Change(String changeString, int changePos, String type) {
        this.changeString = changeString;
        this.changePos = changePos;
        this.type = type;
    }

    @Override
    public void apply(Document doc) {
        try {
            if (type == "I") {
                doc.insert(changePos, changeString);
            } else if (type == "D") {
                doc.delete(changePos, changeString);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalStateException("the change cannot be applied to " + doc.toString() + ".");
        }
    }

    @Override
    public void revert(Document doc) {
        try {
            if(type == "D"){
                doc.insert(changePos, changeString);
            }else if(type == "I"){
                doc.delete(changePos, changeString);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalStateException("the change cannot be reverted in " + doc.toString() + ".");
        }
    }
}
