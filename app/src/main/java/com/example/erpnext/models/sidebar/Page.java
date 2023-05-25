package com.example.erpnext.models.sidebar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("for_user")
    @Expose
    private String forUser;
    @SerializedName("parent_page")
    @Expose
    private String parentPage;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("public")
    @Expose
    private Integer _public;
    @SerializedName("module")
    @Expose
    private String module;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("is_hidden")
    @Expose
    private Integer isHidden;
    @SerializedName("label")
    @Expose
    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public String getParentPage() {
        return parentPage;
    }

    public void setParentPage(String parentPage) {
        this.parentPage = parentPage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublic() {
        return _public;
    }

    public void setPublic(Integer _public) {
        this._public = _public;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
