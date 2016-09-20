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
    public List<String> title;

    @Field
    public List<String> source;

    @Field
    public List<String> email;

    @Field
    public List<String> url;

    @Field
    public List<String> file;

    @Field
    public List<String> tags;

    @Field
    public List<Boolean> isdrp;

    public List<Concept> concepts;

    public String fileName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
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

    public List<Boolean> getIsdrp() {
        return isdrp;
    }

    public void setIsdrp(List<Boolean> isdrp) {
        this.isdrp = isdrp;
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

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
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
        }
        return false;
    }

    @Override
    public String toString() {

        // TODO: Use StringBuilder
        StringBuilder builder = new StringBuilder()
                                    .append(id)
                                    .append(title == null ? "No title":title.get(0))
                                    .append(source == null ? "No source":source.get(0))
                                    .append(email == null ? "No email":email.get(0))
                                    .append(url == null ? "No url":url.get(0))
                                    .append(file == null ? "No File":file.get(0))
                                    .append(tags == null ? "No tags":tags.get(0));
        return builder.toString();
    }
}
