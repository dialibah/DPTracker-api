package sn.dialibah.chargements.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chargement {

    private String guid;

    private String createdBy;

    private LocalDate leavingDate;

    private LocalDate arrivalDate;

    private StatutChargement statutChargement;

    private String comments;

    private List<Colis> colis;

}
