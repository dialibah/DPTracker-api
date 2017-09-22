package sn.dialibah.chargements.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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

    @Indexed(unique = true)
    private String guid;
}
