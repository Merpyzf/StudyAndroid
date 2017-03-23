package com.merpyzf.reviewandroid.domian;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/20.
 */

public class PersonData {

    private int result;
    private ArrayList<PersonInfo> personInfos;


    public ArrayList<PersonInfo> getPersonInfos() {
        return personInfos;
    }

    public void setPersonInfos(ArrayList<PersonInfo> personInfos) {
        this.personInfos = personInfos;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
