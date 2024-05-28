
package org.altbeacon.models.getInscripcions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InscripcionsExample {

    @SerializedName("inscripcions")
    @Expose
    private List<InscripcionsInscripcion> inscripcionsInscripcions;

    public List<InscripcionsInscripcion> getInscripcions() {
        return inscripcionsInscripcions;
    }

    public void setInscripcions(List<InscripcionsInscripcion> inscripcionsInscripcions) {
        this.inscripcionsInscripcions = inscripcionsInscripcions;
    }

}
