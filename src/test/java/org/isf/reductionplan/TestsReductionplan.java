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

	// Méthode pour initialiser un ReductionPlan
	public ReductionPlan setup(boolean usingConstructor) {
		if (usingConstructor) {
			// Création avec le constructeur complet
			return new ReductionPlan(description, operationRate, medicalRate, examRate, otherRate);
		} else {
			// Création vide + utilisation des setters
			ReductionPlan reductionplan = new ReductionPlan();
			reductionplan.setDescription(description);
			reductionplan.setOperationRate(operationRate);
			reductionplan.setExamRate(examRate);
			reductionplan.setMedicalRate(medicalRate);
			reductionplan.setOtherRate(otherRate);

			return reductionplan;
		}
	}


	public List<MedicalsReduction> setupMedicalsReduction() {

		ReductionPlan reductionPlan = new ReductionPlan();
		reductionPlan.setId(1);


		Medical medical1 = new Medical();
		medical1.setCode(6);

		Medical medical2 = new Medical();
		medical2.setCode(5);

		// Création des identifiants pour MedicalsReduction
		MedicalsReductionId id1 = new MedicalsReductionId(reductionPlan.getId(), medical1.getCode());
		MedicalsReductionId id2 = new MedicalsReductionId(reductionPlan.getId(), medical2.getCode());

		// Création des objets MedicalsReduction avec les réductions associées
		MedicalsReduction medicalsReduction1 = new MedicalsReduction(id1, reductionPlan, medical1, 80.0);
		MedicalsReduction medicalsReduction2 = new MedicalsReduction(id2, reductionPlan, medical2, 50.0);

		return Arrays.asList(medicalsReduction1, medicalsReduction2);
	}


	// Méthode de vérification des valeurs
	public void check(ReductionPlan reductionplan) {
		assertThat(reductionplan.getDescription()).isEqualTo(description);
		assertThat(reductionplan.getOperationRate()).isEqualTo(operationRate);
		assertThat(reductionplan.getMedicalRate()).isEqualTo(medicalRate);
		assertThat(reductionplan.getExamRate()).isEqualTo(examRate);
		assertThat(reductionplan.getOtherRate()).isEqualTo(otherRate);
	}





	public void checkMedicals(List<MedicalsReduction> medicals) {
		assertThat(medicals).hasSize(2);
		// Vérifiez que les codes médicaux dans les objets MedicalsReduction correspondent à ceux attendus
		assertThat(medicals.get(0).getMedical().getCode()).isEqualTo(Integer.valueOf(medical1)); // Utilisez Integer pour la comparaison
		assertThat(medicals.get(1).getMedical().getCode()).isEqualTo(Integer.valueOf(medical2)); // Utilisez Integer pour la comparaison
	}



	@Test
	public void testReductionplanUsingConstructor() {
		ReductionPlan reductionplan = setup(true);
		check(reductionplan);
	}


	@Test
	public void testReductionplanUsingSetters() {
		ReductionPlan reductionplan = setup(false);
		check(reductionplan);
	}





}
