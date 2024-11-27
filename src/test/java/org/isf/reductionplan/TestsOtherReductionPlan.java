package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;

import org.isf.pricesothers.model.PricesOthers;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.OtherReductionId;
import org.isf.reductionplan.model.ReductionPlan;

public class TestsOtherReductionPlan {

	private double reductionRate = 30.0;

	public OtherReduction setupOtherReduction(OtherReductionId otherReductionId, PricesOthers pricesOthers, ReductionPlan reductionPlan,
					boolean usingConstructor) {
		if (usingConstructor) {
			return new OtherReduction(otherReductionId, reductionPlan, pricesOthers, reductionRate);
		} else {
			OtherReduction otherReduction = new OtherReduction();
			otherReduction.setPricesOthers(pricesOthers);
			otherReduction.setReductionRate(reductionRate);
			otherReduction.setReductionPlan(reductionPlan);
			otherReduction.setId(otherReductionId);

			return otherReduction;
		}
	}

	public void checkOTherReductionAttributes(OtherReduction otherReduction, int expectedPricesOtherCode, int expectedReductionPlanId,
					double expectedReductionRate) {
		assertThat(otherReduction).isNotNull();
		assertThat(otherReduction.getPricesOthers().getCode()).isEqualTo(expectedPricesOtherCode);
		assertThat(otherReduction.getReductionPlan().getId()).isEqualTo(expectedReductionPlanId);
		assertThat(otherReduction.getReductionRate()).isEqualTo(expectedReductionRate);
	}
}



