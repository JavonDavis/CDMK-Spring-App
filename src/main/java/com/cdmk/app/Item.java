package com.cdmk.app;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * Created by javon on 20/07/2016.
 */
public class Item {

    @Field
    public String id;

    @Field
    public List<String> url;

    @Field
    public List<String> text;

    @Field
    public List<String> file;

    @Field
    public List<String> tags;

    public List<Concept> concepts;

    public String fileName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item)
        {
            if(this.url != null) {
                return ((Item) obj).url != null && this.url.get(0).trim().equals(((Item) obj).url.get(0).trim());
            } else if(this.fileName != null) {
                return ((Item) obj).fileName != null && this.fileName.trim().equals(((Item) obj).fileName.trim());
            } else if(this.file != null) {
                return ((Item) obj).file != null && this.file.get(0).trim().equals(((Item) obj).file.get(0).trim());
            }
            else {
                return !(this.text == null || ((Item) obj).text == null) && this.text.get(0).trim().equals(((Item) obj).text.get(0).trim());
            }

        }
        return false;
    }

    @Override
    public String toString() {

        // TODO: Use StringBuilder
        String result = "";
        result += "id:"+this.id;
        result += "\n";
        result += "url:"+this.url;
        result += "\n";
        result += "text:"+this.text;
        result += "\n";
        result += "file:"+this.file;
        result += "\n";
        result += "tags"+this.tags;
        result += "\n";
        return result;
    }
}
