package com.example.quizzapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tiboCorb on 28/03/2020
 */


public class Language extends RealmObject {

    @PrimaryKey
    private Long languageId;
    private String name;
    private byte[] logo;

    public Language(Long languageId, String name, byte[] logo) {
        this.languageId = languageId;
        this.name = name;
        this.logo = logo;
    }

    public Language() {
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageID) {
        this.languageId = languageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  byte[] getLogo() {
        return logo;
    }

    public void setLogo( byte[] logo) {
        this.logo = logo;
    }
}
