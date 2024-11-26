package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.isf.exa.model.Exam;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ReductionPlan;
import org.junit.jupiter.api.Test;

public class TestsExamReductionPlan {

	private String examCode = "EXA123";
	private double reductionRate = 50.0;
	private int reductionPlanId = 1;

	public ExamsReduction setupExamReduction(String examCode, int reductionPlanId, double reductionRate) {
		ExamsReduction examReduction = new ExamsReduction();

		Exam exam = new Exam();
		exam.setCode(examCode);

		ReductionPlan reductionPlan = new ReductionPlan();
		reductionPlan.setId(reductionPlanId);

		examReduction.setExam(exam);
		examReduction.setReductionPlan(reductionPlan);
		examReduction.setReductionRate(reductionRate);

		return examReduction;
	}

	// Méthode de vérification pour un ExamsReduction
	public void checkExamReductionAttributes(ExamsReduction examsReduction, String expectedExamCode, int expectedReductionPlanId, double expectedReductionRate) {
		assertThat(examsReduction).isNotNull();
		assertThat(examsReduction.getExam().getCode()).isEqualTo(expectedExamCode);
		assertThat(examsReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(examsReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}

	// Test pour valider la liste des ExamsReduction
	@Test
	public void testExamsReductionList() {
		String examCode1 = "EXA123";
		String examCode2 = "EXA124";
		int reductionPlanId = 1;
		double reductionRate = 50.0;

		// Configuration de deux ExamsReduction
		ExamsReduction examReduction1 = setupExamReduction(examCode1, reductionPlanId, reductionRate);
		ExamsReduction examReduction2 = setupExamReduction(examCode2, reductionPlanId, reductionRate);

		List<ExamsReduction> exams = List.of(examReduction1, examReduction2);

		// Vérification
		assertThat(exams).hasSize(2);
		checkExamReductionAttributes(exams.get(0), examCode1, reductionPlanId, reductionRate);
		checkExamReductionAttributes(exams.get(1), examCode2, reductionPlanId, reductionRate);
	}
}