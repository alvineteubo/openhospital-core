package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.isf.exa.model.Exam;
import org.isf.operation.model.Operation;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.junit.jupiter.api.Test;

public class TestsExamReductionPlan {

	private String examCode = "EXA123";
	private double reductionRate = 50.0;
	private int reductionPlanId = 1;



		public ExamsReduction setupExamReduction(ExamsReductionId examsReductionId, Exam exam, ReductionPlan reductionPlan,
		boolean usingConstructor) {
			if (usingConstructor) {

				return new ExamsReduction(examsReductionId, reductionPlan, exam, reductionRate);
			} else {
				// Création vide + utilisation des setters
				ExamsReduction examsReduction = new ExamsReduction();
				examsReduction.setExam(exam);
				examsReduction.setReductionRate(reductionRate);
				examsReduction.setReductionPlan(reductionPlan);
				examsReduction.setId(examsReductionId);

				return examsReduction;
			}
		}


		public void checkExamReductionAttributes(ExamsReduction examsReduction, String expectedExamCode, int expectedReductionPlanId,
		double expectedReductionRate) {
			assertThat(examsReduction).isNotNull();
			assertThat(examsReduction.getExam().getCode()).isEqualTo(expectedExamCode);
			assertThat(examsReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
			assertThat(examsReduction.getReductionRate()).isEqualTo(expectedReductionRate);
		}

}