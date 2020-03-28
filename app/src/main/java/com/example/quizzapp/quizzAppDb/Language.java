package com.example.quizzapp.quizzAppDb;

/**
 * Created by tiboCorb on 28/03/2020
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Language {

    //Primary key
    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long languageID;

    private String name;
    private String logo;

   @Generated(hash = 818752225)
public Language(Long languageID, String name, String logo) {
    this.languageID = languageID;
    this.name = name;
    this.logo = logo;
}

    public Language(){

    }

    public Long getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Long languageID) {
        this.languageID = languageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
