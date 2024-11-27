package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import org.isf.medicals.model.Medical;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.ReductionPlan;

public class TestsMedicalReductionPlan {

	private double reductionRate = 30.0;

	public MedicalsReduction setupMedicalReduction(MedicalsReductionId medicalsReductionId, Medical medical, ReductionPlan reductionPlan,
					boolean usingConstructor) {
		if (usingConstructor) {

			return new MedicalsReduction(medicalsReductionId, reductionPlan, medical, reductionRate);
		} else {
			MedicalsReduction medicalsReduction = new MedicalsReduction();
			medicalsReduction.setMedical(medical);
			medicalsReduction.setReductionRate(reductionRate);
			medicalsReduction.setReductionPlan(reductionPlan);
			medicalsReduction.setId(medicalsReductionId);

			return medicalsReduction;
		}
	}

	public void checkMedicalReductionAttributes(MedicalsReduction medicalsReduction, Integer expectedMedicalCode, int expectedReductionPlanId,
					double expectedReductionRate) {
		assertThat(medicalsReduction).isNotNull();
		assertThat(medicalsReduction.getMedical().getCode()).isEqualTo(expectedMedicalCode);
		assertThat(medicalsReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(medicalsReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}
}
