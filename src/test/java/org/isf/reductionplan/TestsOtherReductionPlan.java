/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2024 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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


