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

package org.isf.reductionplan.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ExamsReductionId implements Serializable {

	@Column(name = "ER_RP_ID")
	private int reductionplanId;

	@Column(name = "ER_EXA_ID_A")
	private String examId;

	// Constructeur par défaut
	public ExamsReductionId() {}

	// Constructeur avec paramètres
	public ExamsReductionId(int reductionplanId, String examId) {
		this.reductionplanId = reductionplanId;
		this.examId = examId;
	}

	// Getters et setters
	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	// hashCode et equals
	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, examId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ExamsReductionId that = (ExamsReductionId) obj;
		return reductionplanId == that.reductionplanId &&
						Objects.equals(examId, that.examId);
	}
}