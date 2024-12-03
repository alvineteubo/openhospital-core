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

package org.isf.reductionplan.service;

import java.util.List;
import java.util.Optional;

import org.isf.exa.model.Exam;
import org.isf.exa.service.ExamIoOperationRepository;
import org.isf.medicals.model.Medical;
import org.isf.medicals.service.MedicalsIoOperationRepository;
import org.isf.operation.model.Operation;
import org.isf.operation.service.OperationIoOperationRepository;
import org.isf.patient.service.PatientIoOperationRepository;
import org.isf.pricesothers.model.PricesOthers;
import org.isf.pricesothers.service.PriceOthersIoOperationRepository;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.OtherReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mwithi
 */
@Service
@Transactional(rollbackFor = OHServiceException.class)
@TranslateOHServiceException
public class ReductionPlanIoOperations {

	private final ReductionplanIoOperationRepository reductionplanIoOperationRepository;
	private final ExamsReductionRepository examsReductionRepository;
	private final MedicalsReductionRepository medicalsReductionRepository;
	private final OperationsReductionRepository operationsReductionRepository;
	private final OthersReductionRepository othersReductionRepository;
	private final ExamIoOperationRepository examIoOperationRepository;
	private final MedicalsIoOperationRepository medicalsIoOperationRepository;
	private final OperationIoOperationRepository operationIoOperationRepository;
	private final PriceOthersIoOperationRepository priceOthersIoOperationRepository;
	private final PatientIoOperationRepository patientIoOperationRepository;
	;

	public ReductionPlanIoOperations(ReductionplanIoOperationRepository reductionplanIoOperationRepository, ExamsReductionRepository examsReductionRepository,
					MedicalsReductionRepository medicalsReductionRepository, OperationsReductionRepository operationsReductionRepository,
					OthersReductionRepository othersReductionRepository, ExamIoOperationRepository examIoOperationRepository,
					MedicalsIoOperationRepository medicalsIoOperationRepository, OperationIoOperationRepository operationIoOperationRepository,
					PriceOthersIoOperationRepository priceOthersIoOperationRepository, PatientIoOperationRepository patientIoOperationRepository
	) {
		this.reductionplanIoOperationRepository = reductionplanIoOperationRepository;
		this.examsReductionRepository = examsReductionRepository;
		this.medicalsReductionRepository = medicalsReductionRepository;
		this.operationsReductionRepository = operationsReductionRepository;
		this.othersReductionRepository = othersReductionRepository;
		this.examIoOperationRepository = examIoOperationRepository;
		this.medicalsIoOperationRepository = medicalsIoOperationRepository;
		this.operationIoOperationRepository = operationIoOperationRepository;
		this.priceOthersIoOperationRepository = priceOthersIoOperationRepository;
		this.patientIoOperationRepository = patientIoOperationRepository;
	}

	/**
	 * Return the list of {@link ReductionPlan}s in the DB
	 * @return the list of {@link ReductionPlan}s
	 * @throws OHServiceException
	 */
	public List<ReductionPlan> getReductionplan() throws OHServiceException {
		return reductionplanIoOperationRepository.findAll();
	}

	/**
	 * Return the list of {@link ReductionPlan}s in the DB
	 * @return the list of {@link ReductionPlan}s
	 * @throws OHServiceException
	 */

	public List<ReductionPlan> findByIdIn(List<Integer> ids) {
		return reductionplanIoOperationRepository.findByIdIn(ids);
	}

	public List<ReductionPlan> getReductionPlan(String description) throws OHServiceException {

		List<ReductionPlan> reductionPlans = reductionplanIoOperationRepository.findByDescription(description);
		return reductionPlans;
	}

	public ReductionPlan newReductionPlan(ReductionPlan reductionPlan) throws OHServiceException {
		return reductionplanIoOperationRepository.save(reductionPlan);
	}

	public ReductionPlan updateReductionPlan(int rpId, ReductionPlan updatedReductionPlan) throws OHServiceException {

		Optional<ReductionPlan> existingPlanOpt = reductionplanIoOperationRepository.findById(rpId);
		if (existingPlanOpt.isPresent()) {
			ReductionPlan existingPlan = existingPlanOpt.get();
			existingPlan.setDescription(updatedReductionPlan.getDescription());
			existingPlan.setOperationRate(updatedReductionPlan.getOperationRate());
			existingPlan.setMedicalRate(updatedReductionPlan.getMedicalRate());
			existingPlan.setExamRate(updatedReductionPlan.getExamRate());
			existingPlan.setOtherRate(updatedReductionPlan.getOtherRate());
			return reductionplanIoOperationRepository.save(existingPlan);
		}
		throw new OHServiceException(new OHExceptionMessage(null));
	}

	public void deleteReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		reductionplanIoOperationRepository.delete(reductionplan);
	}

	public ExamsReduction getExamsReduction(ReductionPlan reductionPlan, Exam exam) {
		return examsReductionRepository.findByReductionPlanAndExam(reductionPlan, exam);
	}
	public List<ExamsReduction> getExamsForReductionPlan(int reductionPlanId) throws OHServiceException {
		Optional<ReductionPlan> reductionPlanOpt = reductionplanIoOperationRepository.findById(reductionPlanId);
		if (reductionPlanOpt.isPresent()) {
			ReductionPlan reductionPlan = reductionPlanOpt.get();
			return examsReductionRepository.findByReductionPlan(reductionPlan);
		} else {
			throw new OHServiceException(
							new OHExceptionMessage("ReductionPlan with ID " + reductionPlanId + " not found")
			);
		}
	}

	public MedicalsReduction getMedicalsReduction(ReductionPlan reductionPlan, Medical medical) {
		return medicalsReductionRepository.findByReductionPlanAndMedical(reductionPlan, medical);
	}

	public List<MedicalsReduction> getMedicalsForReductionPlan(int reductionPlanId) throws OHServiceException {
		Optional<ReductionPlan> reductionPlanOpt = reductionplanIoOperationRepository.findById(reductionPlanId);
		if (reductionPlanOpt.isPresent()) {
			ReductionPlan reductionPlan = reductionPlanOpt.get();
			return medicalsReductionRepository.findByReductionPlan(reductionPlan);
		} else {
			throw new OHServiceException(
							new OHExceptionMessage("ReductionPlan with ID " + reductionPlanId + " not found")
			);
		}
	}

	public OperationsReduction getOperationsReduction(ReductionPlan reductionPlan, Operation operation) {
		return operationsReductionRepository.findByReductionPlanAndOperation(reductionPlan, operation);
	}

	public List<OperationsReduction> getOperationsForReductionPlan(int reductionPlanId) throws OHServiceException {
		Optional<ReductionPlan> reductionPlanOpt = reductionplanIoOperationRepository.findById(reductionPlanId);
		if (reductionPlanOpt.isPresent()) {
			ReductionPlan reductionPlan = reductionPlanOpt.get();
			return operationsReductionRepository.findByReductionPlan(reductionPlan);
		} else {
			throw new OHServiceException(
							new OHExceptionMessage("ReductionPlan with ID " + reductionPlanId + " not found")
			);
		}
	}

	public OtherReduction getMedicalsReduction(ReductionPlan reductionPlan, PricesOthers pricesOthers) {
		return othersReductionRepository.findByReductionPlanAndPricesOthers(reductionPlan, pricesOthers);
	}

	public List<OtherReduction> getOthersForReductionPlan(int reductionPlanId) throws OHServiceException {
		Optional<ReductionPlan> reductionPlanOpt = reductionplanIoOperationRepository.findById(reductionPlanId);
		if (reductionPlanOpt.isPresent()) {
			ReductionPlan reductionPlan = reductionPlanOpt.get();
			return othersReductionRepository.findByReductionPlan(reductionPlan);
		} else {
			throw new OHServiceException(
							new OHExceptionMessage("ReductionPlan with ID " + reductionPlanId + " not found")
			);
		}
	}
	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param operationId the list of ExamsReduction to update.
	 * @param reductionPlanId ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateOperationReduction(int reductionPlanId, String operationId, double reductionRate) {
		ReductionPlan reductionPlan = reductionplanIoOperationRepository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			return false;
		}

		Operation operation = operationIoOperationRepository.findById(operationId).orElse(null);
		if (operationId == null) {
			return false;
		}

		OperationsReductionId operationsReductionId = new OperationsReductionId(reductionPlanId, operationId);
		OperationsReduction operationsReduction = operationsReductionRepository.findById(operationsReductionId).orElse(null);

		if (operationsReduction != null) {
			operationsReduction.setReductionRate(reductionRate);
			operationsReductionRepository.save(operationsReduction);
			return true;
		}
		return false;
	}

	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param pricesOtherId the list of ExamsReduction to update.
	 * @param reductionPlanId the ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateOthersReduction(int reductionPlanId, int pricesOtherId, double reductionRate) {
		ReductionPlan reductionPlan = reductionplanIoOperationRepository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			return false;
		}

		PricesOthers pricesOthers = priceOthersIoOperationRepository.findById(pricesOtherId).orElse(null);
		if (pricesOthers == null) {
			return false;
		}

		OtherReductionId otherReductionId = new OtherReductionId(reductionPlanId, pricesOtherId);
		OtherReduction otherReduction = othersReductionRepository.findById(otherReductionId).orElse(null);

		if (otherReduction != null) {
			otherReduction.setReductionRate(reductionRate);
			othersReductionRepository.save(otherReduction);
			return true;
		}
		return false;
	}

	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param medicalId the list of ExamsReduction to update.
	 * @param reductionPlanId the ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateMedicalReduction(int reductionPlanId, Integer medicalId, double reductionRate) {
		ReductionPlan reductionPlan = reductionplanIoOperationRepository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			return false;
		}

		// Charger l'objet Medical
		Medical medical = medicalsIoOperationRepository.findById(medicalId).orElse(null);
		if (medical == null) {
			return false;
		}

		// Recherche de la réduction médicale existante avec l'ID composite
		MedicalsReductionId medicalsReductionId = new MedicalsReductionId(reductionPlanId, medicalId);
		MedicalsReduction medicalsReduction = medicalsReductionRepository.findById(medicalsReductionId).orElse(null);

		if (medicalsReduction != null) {
			// Mise à jour du taux de réduction
			medicalsReduction.setReductionRate(reductionRate);
			// Sauvegarde de la modification
			medicalsReductionRepository.save(medicalsReduction);
			return true;
		}
		return false;
	}

	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param examCode the list of ExamsReduction to update.
	 * @param rpID the ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateExamReduction(String examCode, int rpID, double reductionRate) throws OHServiceException {
		try {
			ReductionPlan reductionPlan = reductionplanIoOperationRepository.findById(rpID)
							.orElseThrow(() -> new OHServiceException(
											new OHExceptionMessage("Reduction Plan Not Found",
															"The Reduction Plan with ID " + rpID + " was not found.",
															OHSeverityLevel.ERROR)
							));
			Exam exam = examIoOperationRepository.findById(examCode)
							.orElseThrow(() -> new OHServiceException(
											new OHExceptionMessage("Exam Not Found",
															"The Exam with code " + examCode + " was not found.",
															OHSeverityLevel.ERROR)
							));
			ExamsReduction examsReduction = getExamsReduction(reductionPlan, exam);

			if (examsReduction == null) {
				throw new OHServiceException(
								new OHExceptionMessage("Exams Reduction Not Found",
												"No ExamsReduction found for the provided Reduction Plan and Exam.",
												OHSeverityLevel.ERROR)
				);
			}
			examsReduction.setReductionRate(reductionRate);
			examsReductionRepository.save(examsReduction);

			return true;

		} catch (OHServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new OHServiceException(
							e,
							new OHExceptionMessage("Unexpected Error",
											"An unexpected error occurred while updating the exam reduction: " + e.getMessage(),
											OHSeverityLevel.ERROR)
			);
		}

		//	// Ajout d'un examen pour un plan de réduction
		//	public boolean addExamToReductionPlan(int reductionPlanId, String examCode, double reductionRate) {
		//		// Charger le plan de réduction
		//		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		//		if (reductionPlan == null) {
		//			return false; // Si le plan de réduction n'existe pas, retourner false
		//		}
		//
		//		// Charger l'examen
		//		Exam exam = examIoOperationRepository.findById(examCode).orElse(null);
		//		if (exam == null) {
		//			return false; // Si l'examen n'existe pas, retourner false
		//		}
		//
		//		// Créer l'objet ExamsReduction
		//		ExamsReduction examsReduction = new ExamsReduction();
		//		examsReduction.setReductionPlan(reductionPlan);
		//		examsReduction.setExam(exam);
		//		examsReduction.setReductionRate(reductionRate);
		//
		//		// Sauvegarder l'examen
		//		examsReductionRepository.save(examsReduction);
		//		return true;
		//	}
		//
		//	// Ajout d'une réduction pour un traitement médical
		//	public boolean addMedicalToReductionPlan(int reductionPlanId, Integer medicalId, double reductionRate) {
		//		// Charger le plan de réduction
		//		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		//		if (reductionPlan == null) {
		//			return false; // Si le plan de réduction n'existe pas, retourner false
		//		}
		//
		//		// Charger l'objet Medical
		//		Medical medical = medicalsIoOperationRepository.findById(medicalId).orElse(null);
		//		if (medical == null) {
		//			return false; // Si le traitement médical n'existe pas, retourner false
		//		}
		//
		//		// Créer l'objet MedicalsReduction
		//		MedicalsReduction medicalsReduction = new MedicalsReduction();
		//		medicalsReduction.setReductionPlan(reductionPlan);
		//		medicalsReduction.setMedical(medical);
		//		medicalsReduction.setReductionRate(reductionRate);
		//
		//		// Sauvegarder la réduction médicale
		//		medicalsReductionRepository.save(medicalsReduction);
		//		return true;
		//	}
		//
		//	// Ajout d'une réduction pour une opération
		//	public boolean addOperationToReductionPlan(int reductionPlanId, String operationId, double reductionRate) {
		//		// Charger le plan de réduction
		//		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		//		if (reductionPlan == null) {
		//			return false; // Si le plan de réduction n'existe pas, retourner false
		//		}
		//
		//		// Charger l'objet Operation
		//		Operation operation = operationIoOperationRepository.findById(operationId).orElse(null);
		//		if (operation == null) {
		//			return false; // Si l'opération n'existe pas, retourner false
		//		}
		//
		//		// Créer l'objet OperationsReduction
		//		OperationsReduction operationsReduction = new OperationsReduction();
		//		operationsReduction.setReductionPlan(reductionPlan);
		//		operationsReduction.setOperation(operation);
		//		operationsReduction.setReductionRate(reductionRate);
		//
		//		// Sauvegarder la réduction d'opération
		//		operationsReductionRepository.save(operationsReduction);
		//		return true;
		//	}
		//
		//	// Ajout d'une réduction pour des prix autres
		//	public boolean addOtherToReductionPlan(int reductionPlanId, int pricesOtherId, double reductionRate) {
		//		// Charger le plan de réduction
		//		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		//		if (reductionPlan == null) {
		//			return false; // Si le plan de réduction n'existe pas, retourner false
		//		}
		//
		//		// Charger l'objet PricesOthers
		//		PricesOthers pricesOthers = priceOthersIoOperationRepository.findById(pricesOtherId).orElse(null);
		//		if (pricesOthers == null) {
		//			return false; // Si le prix autre n'existe pas, retourner false
		//		}
		//
		//		// Créer l'objet OtherReduction
		//		OtherReduction otherReduction = new OtherReduction();
		//		otherReduction.setReductionPlan(reductionPlan);
		//		otherReduction.setPricesOthers(pricesOthers);
		//		otherReduction.setReductionRate(reductionRate);
		//
		//		// Sauvegarder la réduction autre
		//		othersReductionRepository.save(otherReduction);
		//		return true;
		//	}
	}

}