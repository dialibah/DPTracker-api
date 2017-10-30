package sn.dialibah.chargements.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by nureynisow on 22/09/2017.
 * for DPTracker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Chargement")
@Builder
public class ChargementEntity {

    @Id
    private String id;

    private String description;

    @Indexed(unique = true)
    private String guid;

    private String createdBy;

    private LocalDate leavingDate;

    private LocalDate arrivalDate;

    @Indexed
    private StatutChargement statutChargement;

    private List<Colis> colis;
}
