package org.altbeacon.models.getInscripcions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beacon {

    @SerializedName("bea_id")
    @Expose
    private int bea_id;

    @SerializedName("bea_code")
    @Expose
    private String bea_code;

    public Beacon(int bea_id, String bea_code) {
        this.bea_id = bea_id;
        this.bea_code = bea_code;
    }

    public int getBea_id() {
        return bea_id;
    }

    public void setBea_id(int bea_id) {
        this.bea_id = bea_id;
    }

    public String getBea_code() {
        return bea_code;
    }

    public void setBea_code(String bea_code) {
        this.bea_code = bea_code;
    }


}
