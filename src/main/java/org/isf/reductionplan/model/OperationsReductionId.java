package org.isf.reductionplan.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OperationsReductionId implements Serializable {

	@Column(name = "OPR_RP_ID")
	private int reductionplanId;

	@Column(name = "OPR_OPE_ID_A")
	private String operationId;


	public OperationsReductionId() {
	}

	public OperationsReductionId(int reductionplanId, String operationId) {
		this.reductionplanId = reductionplanId;
		this.operationId = operationId;
	}

	public int getReductionplanId() {
		return reductionplanId;
	}

	public void setReductionplanId(int reductionplanId) {
		this.reductionplanId = reductionplanId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reductionplanId, operationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		OperationsReductionId that = (OperationsReductionId) obj;
		return reductionplanId == that.reductionplanId &&
						Objects.equals(operationId, that.operationId);
	}


}
