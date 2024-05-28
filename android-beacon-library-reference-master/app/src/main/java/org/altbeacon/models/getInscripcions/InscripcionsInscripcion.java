
package org.altbeacon.models.getInscripcions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InscripcionsInscripcion {

    @SerializedName("ins_id")
    @Expose
    private Integer insId;
    @SerializedName("ins_par_id")
    @Expose
    private Integer insParId;
    @SerializedName("ins_data")
    @Expose
    private String insData;
    @SerializedName("ins_dorsal")
    @Expose
    private Integer insDorsal;
    @SerializedName("ins_retirat")
    @Expose
    private Integer insRetirat;
    @SerializedName("ins_bea_id")
    @Expose
    private Object insBeaId;
    @SerializedName("ins_ccc_id")
    @Expose
    private Integer insCccId;
    @SerializedName("ins_checkpoints")
    @Expose
    private Integer insCheckpoints;
    @SerializedName("participant")
    @Expose
    private InscripcionsParticipant inscripcionsParticipant;
    @SerializedName("beacons")
    @Expose
    private Beacon beacons;

    public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    public Integer getInsParId() {
        return insParId;
    }

    public void setInsParId(Integer insParId) {
        this.insParId = insParId;
    }

    public String getInsData() {
        return insData;
    }

    public void setInsData(String insData) {
        this.insData = insData;
    }

    public Integer getInsDorsal() {
        return insDorsal;
    }

    public void setInsDorsal(Integer insDorsal) {
        this.insDorsal = insDorsal;
    }

    public Integer getInsRetirat() {
        return insRetirat;
    }

    public void setInsRetirat(Integer insRetirat) {
        this.insRetirat = insRetirat;
    }

    public Object getInsBeaId() {
        return insBeaId;
    }

    public void setInsBeaId(Object insBeaId) {
        this.insBeaId = insBeaId;
    }

    public Integer getInsCccId() {
        return insCccId;
    }

    public void setInsCccId(Integer insCccId) {
        this.insCccId = insCccId;
    }

    public Integer getInsCheckpoints() {
        return insCheckpoints;
    }

    public void setInsCheckpoints(Integer insCheckpoints) {
        this.insCheckpoints = insCheckpoints;
    }

    public InscripcionsParticipant getParticipant() {
        return inscripcionsParticipant;
    }

    public void setParticipant(InscripcionsParticipant inscripcionsParticipant) {
        this.inscripcionsParticipant = inscripcionsParticipant;
    }

    public Beacon getBeacons() {
        return beacons;
    }

    public void setBeacons(Beacon beacons) {
        this.beacons = beacons;
    }

}
