package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import org.isf.exa.model.Exam;
import org.isf.medicals.model.Medical;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.isf.reductionplan.model.ExamsReduction;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

class TestsReductionplan {

	// Valeurs de test pour ReductionPlan
	private final String description = "Plan de réduction test";
	private final double operationRate = 20.5f;
	private final double medicalRate = 15.0f;
	private final double examRate = 10.0f;
	private final double otherRate = 5.5f;

	private final String examId1 = "EXA123";
	private final String examId2 = "EXA124";
	private final double reductionRate1 = 50.0f;
	private final double reductionRate2 = 30.0f;
	private final Integer medical1 = 6;
	private final Integer medical2 = 5;


	public ReductionPlan setup(boolean usingConstructor) {
		if (usingConstructor) {

			return new ReductionPlan(description, operationRate, medicalRate, examRate, otherRate);
		} else {

			ReductionPlan reductionplan = new ReductionPlan();
			reductionplan.setDescription(description);
			reductionplan.setOperationRate(operationRate);
			reductionplan.setExamRate(examRate);
			reductionplan.setMedicalRate(medicalRate);
			reductionplan.setOtherRate(otherRate);

			return reductionplan;
		}
	}


	public void check(ReductionPlan reductionplan) {
		assertThat(reductionplan.getDescription()).isEqualTo(description);
		assertThat(reductionplan.getOperationRate()).isEqualTo(operationRate);
		assertThat(reductionplan.getMedicalRate()).isEqualTo(medicalRate);
		assertThat(reductionplan.getExamRate()).isEqualTo(examRate);
		assertThat(reductionplan.getOtherRate()).isEqualTo(otherRate);
	}












}
