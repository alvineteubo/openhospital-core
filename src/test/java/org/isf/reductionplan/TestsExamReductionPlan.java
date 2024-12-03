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

import org.isf.exa.model.Exam;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.ReductionPlan;

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