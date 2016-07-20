package com.cdmk.app;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;
import java.util.Objects;

/**
 * Created by javon on 20/07/2016.
 */
public class Item {

    @Field
    public List<String> url;

    @Field
    public List<String> text;

    @Field
    public List<String> file;

    @Field
    public List<String> tags;

    public List<Concept> concepts;

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item)
        {
            if(this.url != null) {
                if(((Item) obj).url == null)
                    return false;
                return this.url.get(0).equals(((Item) obj).url.get(0));
            } else if(this.file != null)
            {
                if(((Item) obj).file == null)
                    return false;
                return this.file.get(0).equals(((Item) obj).file.get(0));
            } else {
                if(this.text == null || ((Item) obj).text == null)
                    return false;
                return this.text.get(0).equals(((Item) obj).text.get(0));
            }

        }
        return false;
    }
}
