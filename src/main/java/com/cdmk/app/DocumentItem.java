package com.cdmk.app;

/**
 * Created by javon on 19/08/2016.
 */
public class DocumentItem {

    private int startIndex;
    private int endIndex;
    private String content;

    public DocumentItem(int startIndex, int endIndex, String content) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.content = content;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("Start Index:").append(startIndex)
                .append(System.getProperty("line.separator"))
                .append("End Index:")
                .append(endIndex)
                .append(System.getProperty("line.separator"))
                .append("Content:")
                .append(content)
                .append(System.getProperty("line.separator"));
        return builder.toString();
    }
}
