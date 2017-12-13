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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactBean {
	private String nom, prenom, adresse, telephone, email;

}
