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

@Embeddable
public class MedicalsReductionId implements Serializable {

	@Column(name = "MR_RP_ID")
	private int reductionplanId;

	@Column(name = "MR_MDSR_ID")
	private Integer medicalId;

	public MedicalsReductionId() {
	}

	public MedicalsReductionId(int reductionplanId, Integer medicalId) {
		this.reductionplanId = reductionplanId;
		this.medicalId = medicalId;
	}

	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public Integer getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Integer medicalId) {
		this.medicalId = medicalId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, medicalId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MedicalsReductionId that = (MedicalsReductionId) o;
		return reductionplanId == that.reductionplanId &&
						Objects.equals(medicalId, that.medicalId);
	}

}