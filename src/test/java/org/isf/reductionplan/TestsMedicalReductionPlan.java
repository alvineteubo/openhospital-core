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