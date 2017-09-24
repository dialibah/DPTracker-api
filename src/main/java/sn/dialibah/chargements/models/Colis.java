package sn.dialibah.chargements.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nureynisow on 24/09/2017.
 * for DPTracker
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colis {

    private String guid;

    private TypeColis typeColis;

    private Double poids;

    private ContactBean destinataire;

    private ContactBean expediteur;
}
