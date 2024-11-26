package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.isf.pricesothers.model.PricesOthers;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.ReductionPlan;
import org.junit.Test;

public class TestsOtherReductionPlan {

	private int otherCode = 122;
	private double reductionRate = 30.0;
	private int reductionPlanId = 1;

	public OtherReduction setupOtherReduction(int otherCode, int reductionPlanId, double reductionRate) {
		OtherReduction otherReduction = new OtherReduction();

		PricesOthers pricesOthers = new PricesOthers();
		pricesOthers.setId(otherCode);

		ReductionPlan reductionPlan = new ReductionPlan();
		reductionPlan.setId(reductionPlanId);

		otherReduction.setPricesOthers(pricesOthers);
		otherReduction.setReductionPlan(reductionPlan);
		otherReduction.setReductionRate(reductionRate);

		return otherReduction;
	}

	public void checkOtherReductionAttributes(OtherReduction otherReduction, int expectedOtherCode, int expectedReductionPlanId,
					double expectedReductionRate) {
		assertThat(otherReduction).isNotNull();
		assertThat(otherReduction.getPricesOthers().getId()).isEqualTo(expectedOtherCode); // Corrigé ici
		assertThat(otherReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(otherReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}


	// Test pour valider la liste des OtherReduction
	@Test
	public void testOtherReductionList() {
		int otherCode1 = 122;
		int otherCode2 = 124;
		int reductionPlanId = 1;
		double reductionRate = 30.0;

		// Configuration de deux OtherReduction
		OtherReduction otherReduction1 = setupOtherReduction(otherCode1, reductionPlanId, reductionRate);
		OtherReduction otherReduction2 = setupOtherReduction(otherCode2, reductionPlanId, reductionRate);

		List<OtherReduction> reductions = List.of(otherReduction1, otherReduction2);

		// Vérification
		assertThat(reductions).hasSize(2);
		checkOtherReductionAttributes(reductions.get(0), otherCode1, reductionPlanId, reductionRate);
		checkOtherReductionAttributes(reductions.get(1), otherCode2, reductionPlanId, reductionRate);
	}
}



