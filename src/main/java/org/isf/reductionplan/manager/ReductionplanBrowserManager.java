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

package org.isf.reductionplan.manager;

import java.util.ArrayList;
import java.util.List;

import org.isf.exa.model.Exam;
import org.isf.generaldata.MessageBundle;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.ReductionPlan;
import org.isf.reductionplan.service.ReductionPlanIoOperations;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.vaccine.model.Vaccine;
import org.springframework.stereotype.Component;

@Component
public class ReductionplanBrowserManager {

	private final ReductionPlanIoOperations reductionPlanIoOperations;

	public ReductionplanBrowserManager(ReductionPlanIoOperations reductionPlanIoOperations) {
		this.reductionPlanIoOperations = reductionPlanIoOperations;
	}

	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any.
	 * @param reductionplan the {@link ReductionPlan object to validate
	 * @param insert {@code true} or updated {@code false}
	 * @throws OHServiceException
	 */
	public void validateReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		List<OHExceptionMessage> errors = new ArrayList<>();
		String description = reductionplan.getDescription();
		if (description == null || description.trim().isEmpty()) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.common.Theentereddescriptionisnotvalid.msg")));
		}
		if (!errors.isEmpty()) {
			throw new OHDataValidationException(errors);
		}
	}

	/**
	 * Returns the list of {@link ReductionPlan}s in the DB.
	 * @return the list of {@link ReductionPlan}s
	 */
	public List<ReductionPlan> getReductionplan() throws OHServiceException {
		return reductionPlanIoOperations.getReductionplan();
	}

	public List<ReductionPlan> getReductionplan(String description) throws OHServiceException {
		return reductionPlanIoOperations.getReductionPlan(description);
	}
	public List<ReductionPlan> getReductionplanByIds(List<Integer> ids) throws OHServiceException {
		return reductionPlanIoOperations.findByIdIn(ids);
	}

	/**
	 * Inserts a new {@link ReductionPlan} into the DB.
	 * @param reductionplan - the {@link ReductionPlan} object to insert
	 * @return the newly inserted {@link Vaccine} object.
	 */
	public ReductionPlan newReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		validateReductionplan(reductionplan);
		return reductionPlanIoOperations.newReductionPlan(reductionplan);
	}

	/**
	 * Updates the specified {@link ReductionPlan} object.
	 * @param reductionplan- the {@link ReductionPlan} object to update.
	 * @return the updated {@link ReductionPlan} object.
	 */
	public ReductionPlan updateReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		validateReductionplan(reductionplan);
		return reductionPlanIoOperations.updateReductionPlan(reductionplan.getId(), reductionplan);
	}

	/**
	 * Deletes a {@link ReductionPlan} in the DB.
	 * @param reductionplan - the item to delete
	 */
	public void deleteReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		reductionPlanIoOperations.deleteReductionplan(reductionplan);
	}

	/**
	 * Returns the {@link ReductionPlan} based on vaccine code.
	 * @param ids - the {@link ReductionPlan} code.
	 * @return the {@link ReductionPlan}
	 */
	public List<ReductionPlan> getById(List<Integer> ids) throws OHServiceException {
		return reductionPlanIoOperations.findByIdIn(ids);
	}

	/**
	 * Returns the list of {@link org.isf.reductionplan.model.ExamsReduction} for a given reduction plan.
	 *
	 * @param reductionPlanId the ID of the reduction plan
	 * @return the list of {@link ExamsReduction} associated with the reduction plan
	 * @throws OHServiceException if an error occurs during the retrieval
	 */
	public List<ExamsReduction> getExamsForReductionPlan(int reductionPlanId) throws OHServiceException {
		return reductionPlanIoOperations.getExamsForReductionPlan(reductionPlanId);
	}

	/**
	 * Updates the ExamsReduction list for a given ReductionPlan.
	 *
	 * @param examCode the list of new {@link ExamsReduction}
	 * @param rpID the ID of the {@link ReductionPlan} to update
	 * @return true if the operation was successful
	 * @throws OHServiceException if an error occurs during the update
	 */
	public boolean updateExamReduction(String examCode, int rpID, double reductionRate) throws OHServiceException {
		return reductionPlanIoOperations.updateExamReduction(examCode, rpID, reductionRate);
	}


	public ExamsReduction getExamsReduction(ReductionPlan reductionPlan, Exam exam) {
		return reductionPlanIoOperations.getExamsReduction(reductionPlan, exam);
	}


	/**
	 * Returns the list of {@link org.isf.reductionplan.model.MedicalsReduction} for a given reduction plan.
	 *
	 * @param reductionPlanId the ID of the reduction plan
	 * @return the list of {@link MedicalsReduction} associated with the reduction plan
	 * @throws OHServiceException if an error occurs during the retrieval
	 */
	public List<MedicalsReduction> getMedicalsForReductionPlan(int reductionPlanId) throws OHServiceException {
		return reductionPlanIoOperations.getMedicalsForReductionPlan(reductionPlanId);
	}

	/**
	 * Updates the MedicalsReduction list for a given ReductionPlan.
	 *
	 * @param medicalId the list of new {@link MedicalsReduction}
	 * @param rpID the ID of the {@link ReductionPlan} to update
	 * @return true if the operation was successful
	 * @throws OHServiceException if an error occurs during the update
	 */
	public boolean updateMedicalsReduction(Integer medicalId, int rpID, double reductionRate) throws OHServiceException {
		return reductionPlanIoOperations.updateMedicalReduction(medicalId, rpID, reductionRate);
	}


	/**
	 * Returns the list of {@link org.isf.reductionplan.model.OperationsReduction} for a given reduction plan.
	 *
	 * @param reductionPlanId the ID of the reduction plan
	 * @return the list of {@link OperationsReduction} associated with the reduction plan
	 * @throws OHServiceException if an error occurs during the retrieval
	 */
	public List<OperationsReduction> getOperationsForReductionPlan(int reductionPlanId) throws OHServiceException {
		return reductionPlanIoOperations.getOperationsForReductionPlan(reductionPlanId);
	}

	/**
	 * Updates the OperationsReduction list for a given ReductionPlan.
	 *
	 * @param operation the list of new {@link OperationsReduction}
	 * @param rpID the ID of the {@link ReductionPlan} to update
	 * @return true if the operation was successful
	 * @throws OHServiceException if an error occurs during the update
	 */
	public boolean updateOperationsReduction(String operation, int rpID, double reductionRate) throws OHServiceException {
		return reductionPlanIoOperations.updateOperationReduction(rpID, operation, reductionRate);
	}



	/**
	 * Returns the list of {@link org.isf.reductionplan.model.OtherReduction} for a given reduction plan.
	 *
	 * @param reductionPlanId the ID of the reduction plan
	 * @return the list of {@link OtherReduction} associated with the reduction plan
	 * @throws OHServiceException if an error occurs during the retrieval
	 */
	public List<OtherReduction> getOthersForReductionPlan(int reductionPlanId) throws OHServiceException {
		return reductionPlanIoOperations.getOthersForReductionPlan(reductionPlanId);
	}

	/**
	 * Updates the OthersReduction list for a given ReductionPlan.
	 *
	 * @param pricesOther the list of new {@link OtherReduction}
	 * @param rpID the ID of the {@link ReductionPlan} to update
	 * @return true if the operation was successful
	 * @throws OHServiceException if an error occurs during the update
	 */
	public boolean updateOthersReduction(int pricesOther, int rpID, double reductionRate) throws OHServiceException {
		return reductionPlanIoOperations.updateOthersReduction(pricesOther, rpID, reductionRate);
	}

	//	public boolean addExamToReductionPlan(int reductionPlanId, String examCode, double reductionRate) {
	//		return ioOperations.addExamToReductionPlan(reductionPlanId, examCode, reductionRate);
	//	}
	//
	//	public boolean addMedicalToReductionPlan(int reductionPlanId, Integer medicalId, double reductionRate) {
	//		return ioOperations.addMedicalToReductionPlan(reductionPlanId, medicalId, reductionRate);
	//	}
	//
	//	public boolean addOperationToReductionPlan(int reductionPlanId, String operationId, double reductionRate) {
	//		return ioOperations.addOperationToReductionPlan(reductionPlanId, operationId, reductionRate);
	//	}
	//
	//	public boolean addOtherToReductionPlan(int reductionPlanId, int pricesOtherId, double reductionRate) {
	//		return ioOperations.addOtherToReductionPlan(reductionPlanId, pricesOtherId, reductionRate);
	//	}





}