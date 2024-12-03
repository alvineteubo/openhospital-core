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
public class OtherReductionId implements Serializable {

	@Column(name = "OTR_RP_ID")
	private int reductionplanId;

	@Column(name = "OTR_OTH_ID")
	private int pricesOthersId;

	public OtherReductionId() {
	}

	public OtherReductionId(int reductionplanId, int pricesOthersId) {
		this.reductionplanId = reductionplanId;
		this.pricesOthersId = pricesOthersId;
	}

	// Getters et Setters
	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public int getPricesOthersId() {
		return pricesOthersId;
	}

	public void setPricesOthersId(int pricesOthersId) {
		this.pricesOthersId = pricesOthersId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OtherReductionId that = (OtherReductionId) o;
		return reductionplanId == that.reductionplanId &&
						pricesOthersId == that.pricesOthersId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, pricesOthersId);
	}

}