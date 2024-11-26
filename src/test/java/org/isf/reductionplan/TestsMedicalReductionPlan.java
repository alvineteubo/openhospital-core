package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.isf.medicals.model.Medical;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.junit.Test;

public class TestsMedicalReductionPlan {
	private double reductionRate = 30.0;

	public MedicalsReduction setupMedicalReduction(MedicalsReductionId medicalsReductionId, Medical medical, ReductionPlan reductionPlan, boolean usingConstructor) {
		if (usingConstructor) {
			// Création avec le constructeur complet
			return new MedicalsReduction(medicalsReductionId, reductionPlan, medical, reductionRate);
		} else {
			// Création vide + utilisation des setters
			MedicalsReduction medicalsReduction = new MedicalsReduction();
			medicalsReduction.setMedical(medical);
			medicalsReduction.setReductionRate(reductionRate);
			medicalsReduction.setReductionPlan(reductionPlan);
			medicalsReduction.setId(medicalsReductionId);

			return medicalsReduction;
		}
	}

	// Méthode de vérification pour un MedicalsReduction
	public void checkMedicalReductionAttributes(MedicalsReduction medicalsReduction, Integer expectedMedicalCode, int expectedReductionPlanId, double expectedReductionRate) {
		assertThat(medicalsReduction).isNotNull();
		assertThat(medicalsReduction.getMedical().getCode()).isEqualTo(expectedMedicalCode);
		assertThat(medicalsReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(medicalsReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}
}
