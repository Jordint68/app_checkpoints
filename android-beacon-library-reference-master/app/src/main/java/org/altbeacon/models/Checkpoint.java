
package org.altbeacon.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Checkpoint implements Serializable {

    @SerializedName("chk_id")
    @Expose
    private Integer chkId;
    @SerializedName("chk_km")
    @Expose
    private Integer chkKm;
    @SerializedName("chk_cir_id")
    @Expose
    private Integer chkCirId;

    public Integer getChkId() {
        return chkId;
    }

    public void setChkId(Integer chkId) {
        this.chkId = chkId;
    }

    public Integer getChkKm() {
        return chkKm;
    }

    public void setChkKm(Integer chkKm) {
        this.chkKm = chkKm;
    }

    public Integer getChkCirId() {
        return chkCirId;
    }

    public void setChkCirId(Integer chkCirId) {
        this.chkCirId = chkCirId;
    }

}
