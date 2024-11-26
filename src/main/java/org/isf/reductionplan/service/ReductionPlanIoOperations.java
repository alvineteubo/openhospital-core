package org.isf.reductionplan.service;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

import java.util.List;
import java.util.Optional;

import org.bouncycastle.crypto.macs.OldHMac;
import org.isf.exa.model.Exam;
import org.isf.exa.service.ExamIoOperationRepository;
import org.isf.medicals.model.Medical;
import org.isf.medicals.service.MedicalsIoOperationRepository;
import org.isf.operation.model.Operation;
import org.isf.operation.service.OperationIoOperationRepository;
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

	private final ReductionplanIoOperationRepository repository;
	private final ExamsReductionRepository examsReductionRepository;
	private final MedicalsReductionRepository medicalsReductionRepository;
	private final OperationsReductionRepository operationsReductionRepository;
	private final OthersReductionRepository othersReductionRepository;
	private final ExamIoOperationRepository examIoOperationRepository;
	private final MedicalsIoOperationRepository medicalsIoOperationRepository;
	private final OperationIoOperationRepository operationIoOperationRepository;
	private final PriceOthersIoOperationRepository priceOthersIoOperationRepository;

	public ReductionPlanIoOperations(ReductionplanIoOperationRepository reductionplanIoOperationRepository, ExamsReductionRepository examsReductionRepository,
					MedicalsReductionRepository medicalsReductionRepository, OperationsReductionRepository operationsReductionRepository,
					OthersReductionRepository othersReductionRepository, ExamIoOperationRepository examIoOperationRepository,
					MedicalsIoOperationRepository medicalsIoOperationRepository, OperationIoOperationRepository operationIoOperationRepository,
					PriceOthersIoOperationRepository priceOthersIoOperationRepository
	) {
		this.repository = reductionplanIoOperationRepository;
		this.examsReductionRepository = examsReductionRepository;
		this.medicalsReductionRepository = medicalsReductionRepository;
		this.operationsReductionRepository = operationsReductionRepository;
		this.othersReductionRepository = othersReductionRepository;
		this.examIoOperationRepository = examIoOperationRepository;
		this.medicalsIoOperationRepository = medicalsIoOperationRepository;
		this.operationIoOperationRepository = operationIoOperationRepository;
		this.priceOthersIoOperationRepository = priceOthersIoOperationRepository;
	}

	public ExamsReduction getExamsReduction(ReductionPlan reductionPlan, Exam exam) {
		return examsReductionRepository.findByReductionPlanAndExam(reductionPlan, exam);
	}
	public List<ExamsReduction> getExamsForReductionPlan(int reductionPlanId) throws OHServiceException {
		Optional<ReductionPlan> reductionPlanOpt = repository.findById(reductionPlanId);
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
		Optional<ReductionPlan> reductionPlanOpt = repository.findById(reductionPlanId);
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
		Optional<ReductionPlan> reductionPlanOpt = repository.findById(reductionPlanId);
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
		Optional<ReductionPlan> reductionPlanOpt = repository.findById(reductionPlanId);
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
		// Charger l'objet ReductionPlan
		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			// Si le plan de réduction n'existe pas, retourner false
			return false;
		}

		// Charger l'objet Medical
		Operation operation = operationIoOperationRepository.findById(operationId).orElse(null);
		if (operationId == null) {
			// Si le Medical n'existe pas, retourner false
			return false;
		}

		// Recherche de la réduction médicale existante avec l'ID composite
		OperationsReductionId operationsReductionId = new OperationsReductionId(reductionPlanId, operationId);
		OperationsReduction operationsReduction = operationsReductionRepository.findById(operationsReductionId).orElse(null);

		if (operationsReduction != null) {
			// Mise à jour du taux de réduction
			operationsReduction.setReductionRate(reductionRate);
			// Sauvegarde de la modification
			operationsReductionRepository.save(operationsReduction);
			return true;
		}
		return false;  // Si la réduction médicale n'a pas été trouvée, retourner false
	}

	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param pricesOtherId the list of ExamsReduction to update.
	 * @param reductionPlanId the ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateOthersReduction(int reductionPlanId, int pricesOtherId, double reductionRate) {
		// Charger l'objet ReductionPlan
		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			// Si le plan de réduction n'existe pas, retourner false
			return false;
		}

		// Charger l'objet PricesOthers
		PricesOthers pricesOthers = priceOthersIoOperationRepository.findById(pricesOtherId).orElse(null);
		if (pricesOthers == null) {
			// Si le PricesOthers n'existe pas, retourner false
			return false;
		}

		// Recherche de la réduction avec l'ID composite
		OtherReductionId otherReductionId = new OtherReductionId(reductionPlanId, pricesOtherId);
		OtherReduction otherReduction = othersReductionRepository.findById(otherReductionId).orElse(null);

		if (otherReduction != null) {
			// Mise à jour du taux de réduction
			otherReduction.setReductionRate(reductionRate);
			// Sauvegarde de la modification
			othersReductionRepository.save(otherReduction);
			return true;
		}
		return false;  // Si la réduction n'a pas été trouvée, retourner false
	}

	/**
	 * Update the exam reduction list for the given ReductionPlan ID.
	 * @param medicalId the list of ExamsReduction to update.
	 * @param reductionPlanId the ID of the ReductionPlan.
	 * @return true if the update was successful.
	 * @throws OHServiceException if an error occurs during the update.
	 */
	public boolean updateMedicalReduction(int reductionPlanId, Integer medicalId, double reductionRate) {
		// Charger l'objet ReductionPlan
		ReductionPlan reductionPlan = repository.findById(reductionPlanId).orElse(null);
		if (reductionPlan == null) {
			// Si le plan de réduction n'existe pas, retourner false
			return false;
		}

		// Charger l'objet Medical
		Medical medical = medicalsIoOperationRepository.findById(medicalId).orElse(null);
		if (medical == null) {
			// Si le Medical n'existe pas, retourner false
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
		return false;  // Si la réduction médicale n'a pas été trouvée, retourner false
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
			// Charger le plan de réduction par son ID
			ReductionPlan reductionPlan = repository.findById(rpID)
							.orElseThrow(() -> new OHServiceException(
											new OHExceptionMessage("Reduction Plan Not Found",
															"The Reduction Plan with ID " + rpID + " was not found.",
															OHSeverityLevel.ERROR)
							));

			// Charger l'examen par son code
			Exam exam = examIoOperationRepository.findById(examCode)
							.orElseThrow(() -> new OHServiceException(
											new OHExceptionMessage("Exam Not Found",
															"The Exam with code " + examCode + " was not found.",
															OHSeverityLevel.ERROR)
							));

			// Récupérer l'examen de réduction
			ExamsReduction examsReduction = getExamsReduction(reductionPlan, exam);

			if (examsReduction == null) {
				throw new OHServiceException(
								new OHExceptionMessage("Exams Reduction Not Found",
												"No ExamsReduction found for the provided Reduction Plan and Exam.",
												OHSeverityLevel.ERROR)
				);
			}

			// Mettre à jour le taux de réduction
			examsReduction.setReductionRate(reductionRate);

			// Sauvegarder les changements
			examsReductionRepository.save(examsReduction);

			return true;

		} catch (OHServiceException e) {
			// Propager les exceptions spécifiques avec messages détaillés
			throw e;
		} catch (Exception e) {
			// Gestion des erreurs génériques
			throw new OHServiceException(
							e,
							new OHExceptionMessage("Unexpected Error",
											"An unexpected error occurred while updating the exam reduction: " + e.getMessage(),
											OHSeverityLevel.ERROR)
			);
		}
	}

	//		Optional<ReductionPlan> optionalReductionPlan = repository.findById(rpID);
	//		if (optionalReductionPlan.isEmpty()) {
	//			throw new OHServiceException(new OHExceptionMessage("ReductionPlan not found for ID: " + rpID));
	//		}
	//
	//		ReductionPlan reductionPlan = optionalReductionPlan.get();
	//
	//
	//
	//		examsReductionRepository.deleteByReductionPlan(reductionPlan);
	//
	//		for (ExamsReduction examReduction : examReductions) {
	//			String examCode = examReduction.getExam().getCode();
	//			Optional<Exam> optionalExam = examIoOperationRepository.findById(examCode);
	//			if (optionalExam.isEmpty()) {
	//				throw new OHServiceException(new OHExceptionMessage("Exam not found for code: " + examCode));
	//			}
	//			Exam exam = optionalExam.get();
	//
	//			examReduction.setReductionPlan(reductionPlan);
	//			examReduction.setExam(exam);
	//			examsReductionRepository.save(examReduction);
	//		}
	//
	//		return true;

	/**
	 * Return the list of {@link ReductionPlan}s in the DB
	 * @return the list of {@link ReductionPlan}s
	 * @throws OHServiceException
	 */
	public List<ReductionPlan> getReductionplan() throws OHServiceException {
		return repository.findAll();
	}

	/**
	 * Return the list of {@link ReductionPlan}s in the DB
	 * @return the list of {@link ReductionPlan}s
	 * @throws OHServiceException
	 */

	public List<ReductionPlan> findByIdIn(List<Integer> ids) {
		return repository.findByIdIn(ids);
	}

	public List<ReductionPlan> getReductionPlan(String description) throws OHServiceException {

		List<ReductionPlan> reductionPlans = repository.findByDescription(description);
		return reductionPlans;
	}

	public ReductionPlan newReductionPlan(ReductionPlan reductionPlan) throws OHServiceException {
		return repository.save(reductionPlan);
	}

	public ReductionPlan updateReductionPlan(int rpId, ReductionPlan updatedReductionPlan) throws OHServiceException {

		Optional<ReductionPlan> existingPlanOpt = repository.findById(rpId);
		if (existingPlanOpt.isPresent()) {
			ReductionPlan existingPlan = existingPlanOpt.get();
			existingPlan.setDescription(updatedReductionPlan.getDescription());
			existingPlan.setOperationRate(updatedReductionPlan.getOperationRate());
			existingPlan.setMedicalRate(updatedReductionPlan.getMedicalRate());
			existingPlan.setExamRate(updatedReductionPlan.getExamRate());
			existingPlan.setOtherRate(updatedReductionPlan.getOtherRate());
			return repository.save(existingPlan);
		}
		throw new OHServiceException(new OHExceptionMessage(null));
	}

	public void deleteReductionplan(ReductionPlan reductionplan) throws OHServiceException {
		repository.delete(reductionplan);
	}

}
