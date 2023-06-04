package com.example.erpnext.models.holiday_list;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HolidayList {

    @SerializedName("docs")
    @Expose
    private List<Doc> docs;
    @SerializedName("docinfo")
    @Expose
    private Docinfo docinfo;
    @SerializedName("_link_titles")
    @Expose
    private LinkTitles linkTitles;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Docinfo getDocinfo() {
        return docinfo;
    }

    public void setDocinfo(Docinfo docinfo) {
        this.docinfo = docinfo;
    }

    public LinkTitles getLinkTitles() {
        return linkTitles;
    }

    public void setLinkTitles(LinkTitles linkTitles) {
        this.linkTitles = linkTitles;
    }

}


