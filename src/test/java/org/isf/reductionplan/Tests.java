package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import org.assertj.core.api.Condition;
import org.isf.OHCoreTestCase;
import org.isf.exa.TestExam;
import org.isf.exa.model.Exam;
import org.isf.exa.service.ExamIoOperationRepository;
import org.isf.exatype.TestExamType;
import org.isf.exatype.model.ExamType;
import org.isf.exatype.service.ExamTypeIoOperationRepository;
import org.isf.medicals.TestMedical;
import org.isf.medicals.model.Medical;
import org.isf.medicals.service.MedicalsIoOperationRepository;
import org.isf.medtype.TestMedicalType;
import org.isf.medtype.model.MedicalType;
import org.isf.medtype.service.MedicalTypeIoOperationRepository;
import org.isf.operation.TestOperation;
import org.isf.operation.enums.OperationTarget;
import org.isf.operation.model.Operation;
import org.isf.operation.service.OperationIoOperationRepository;
import org.isf.opetype.TestOperationType;
import org.isf.opetype.model.OperationType;
import org.isf.opetype.service.OperationTypeIoOperationRepository;
import org.isf.pricesothers.TestPricesOthers;
import org.isf.reductionplan.manager.ReductionplanBrowserManager;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.isf.reductionplan.service.ExamsReductionRepository;
import org.isf.reductionplan.service.MedicalsReductionRepository;
import org.isf.reductionplan.service.OperationsReductionRepository;
import org.isf.reductionplan.service.ReductionPlanIoOperations;
import org.isf.reductionplan.service.ReductionplanIoOperationRepository;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class Tests extends OHCoreTestCase {

	private static TestsReductionplan testsReductionplan;
	private static TestsExamReductionPlan testsExamReductionPlan;
	private static TestExam testExam;
	private static TestExamType testExamType;
	private static TestsMedicalReductionPlan testsMedicalReductionPlan;
	private static TestMedicalType testMedicalType;
	private static TestMedical testMedical;
	private static TestOperation testOperation;
	private  static  TestsOperationReductionPlan testsOperationReductionPlan;
	private static TestOperationType testOperationType;
//	private  static TestPricesOthers testPricesOthers;
//	private  static TestsOtherReductionPlan testsOtherReductionPlan;


	@Autowired
	ReductionPlanIoOperations ioOperations;

	@Autowired
	ReductionplanIoOperationRepository repository;

	@Autowired
	ExamsReductionRepository examsReductionRepository;

	@Autowired
	ReductionplanBrowserManager manager;

	@Autowired
	ExamIoOperationRepository examIoOperationRepository;

	@Autowired
	ExamTypeIoOperationRepository examTypeIoOperationRepository;

	@Autowired
	MedicalsIoOperationRepository medicalsIoOperationRepository;

	@Autowired
	MedicalsReductionRepository medicalsReductionRepository;

	@Autowired
	MedicalTypeIoOperationRepository medicalTypeIoOperationRepository;

//	@Autowired
//	OperationsReductionRepository operationsReductionRepository;
//
//	@Autowired
//	OperationIoOperationRepository operationIoOperationRepository;
//
//	@Autowired
//	OperationTypeIoOperationRepository operationTypeIoOperationRepository;


	@BeforeAll
	static void setUpClass() {
		testsReductionplan = new TestsReductionplan();
		testsExamReductionPlan = new TestsExamReductionPlan();
		testsMedicalReductionPlan = new TestsMedicalReductionPlan();
		testExam = new TestExam();
		testExamType = new TestExamType();
		testMedical = new TestMedical();
		testMedicalType = new TestMedicalType();
//		testOperation = new TestOperation();
//		testPricesOthers = new TestPricesOthers();
//		testsOperationReductionPlan =  new TestsOperationReductionPlan();
//		testsOtherReductionPlan = new TestsOtherReductionPlan();
//		testOperationType =  new TestOperationType();

	}

	@BeforeEach
	void setUp() {
		cleanH2InMemoryDb();
	}

	@Test
	void testReductionplanGets() throws Exception {
		// given:
		int id = setupTestReductionplan(false);

		// then:
		checkReductionplanIntoDb(id);
	}

	@Test
	void testReductionplanSets() throws Exception {
		// given:
		int id = setupTestReductionplan(true);

		// then:
		checkReductionplanIntoDb(id);
	}

	@Test
	void testIoReductionplanGetAll() throws Exception {
		// given:
		ReductionPlan reductionplan = testsReductionplan.setup(false);
		repository.save(reductionplan);
		List<ReductionPlan> foundReductionPlan = ioOperations.getReductionplan();
		assertThat(foundReductionPlan).isNotNull();
		assertThat(foundReductionPlan.size()).isGreaterThan(0);
		assertThat(foundReductionPlan.size()).isEqualTo(1);
	}

	@Test
	void testFindById() throws Exception {
		// GIVEN: Save multiple ReductionPlans
		ReductionPlan reduction1 = new ReductionPlan("Plan 1", 10, 20, 30, 40);
		ReductionPlan reduction2 = new ReductionPlan("Plan 2", 15, 25, 35, 45);
		repository.save(reduction1);
		repository.save(reduction2);

		// Retrieve the generated IDs
		List<Integer> ids = List.of(reduction1.getId(), reduction2.getId());

		// WHEN: Call the findById method
		List<ReductionPlan> result = manager.getReductionplanByIds(ids);

		// THEN: Verify that the results are correct
		assertThat(result).isNotNull();
		assertThat(result.size()).isEqualTo(2); // Both objects should be retrieved
		assertThat(result).extracting(ReductionPlan::getDescription)
						.containsExactlyInAnyOrder("Plan 1", "Plan 2");
	}

	@Test
	void testMgrGetReductionplanByDescription() throws Exception {
		// given: Initialize and save a ReductionPlan
		ReductionPlan reductionplan = testsReductionplan.setup(false); // Use setters
		repository.save(reductionplan); // Save the entity in the database

		// when: Use the manager method to search by description
		List<ReductionPlan> result = manager.getReductionplan(reductionplan.getDescription());

		// then: Verify that the expected ReductionPlan is in the results
		assertThat(result).isNotEmpty();
		assertThat(result.get(0).getDescription()).isEqualTo(reductionplan.getDescription());
	}

	@Test
	void testMgrUpdateReductionplan() throws Exception {
		int id = setupTestReductionplan(false);
		ReductionPlan foundReductionPlan = repository.findById(id).orElse(null);
		assertThat(foundReductionPlan).isNotNull();
		foundReductionPlan.setDescription("Updated Manager Description");
		ReductionPlan updatedReductionPlan = manager.updateReductionplan(foundReductionPlan);
		assertThat(updatedReductionPlan.getDescription()).isEqualTo("Updated Manager Description");
	}

	@Test
	void testMgrNewReductionplan() throws Exception {
		// given:
		ReductionPlan reductionplan = testsReductionplan.setup(true);

		// when:
		ReductionPlan newReductionPlan = manager.newReductionplan(reductionplan);

		// then:
		assertThat(newReductionPlan.getId()).isGreaterThan(0); // Verify that the ID is generated
		checkReductionplanIntoDb(newReductionPlan.getId());
	}

	@Test
	void testMgrDeleteReductionplan() throws Exception {
		// given:
		int id = setupTestReductionplan(false);
		ReductionPlan foundReductionPlan = repository.findById(id).orElse(null);
		assertThat(foundReductionPlan).isNotNull();

		// when:
		manager.deleteReductionplan(foundReductionPlan);

		// then:
		assertThat(repository.existsById(id)).isFalse();
	}

	@Test
	void testMgrValidationCodeEmpty() throws Exception {
		assertThatThrownBy(() -> {
			// Test without setting an rpId, the ID will be automatically generated
			ReductionPlan reductionplan = testsReductionplan.setup(true);
			reductionplan.setDescription("");  // Validate the empty description field
			manager.newReductionplan(reductionplan);
		}).isInstanceOf(OHDataValidationException.class)
						.has(new Condition<>(e -> ((OHServiceException) e).getMessages().size() == 1, "Expecting single validation error"));
	}

	@Test
	void testMgrValidationDescriptionEmpty() throws Exception {
		assertThatThrownBy(() -> {
			ReductionPlan reductionplan = testsReductionplan.setup(true);
			reductionplan.setDescription("");  // Validate the empty description
			manager.newReductionplan(reductionplan);
		}).isInstanceOf(OHDataValidationException.class)
						.has(new Condition<>(e -> ((OHServiceException) e).getMessages().size() == 1, "Expecting single validation error"));
	}

	@Test
	void setTestsReductionplanToString() throws Exception {
		ReductionPlan reductionplan = new ReductionPlan("TestDescription", 0.00f, 0.1f, 0.02f, 1.3f);
		assertThat(reductionplan).hasToString("TestDescription");
	}

	@Test
	void testReductionplanEquals() throws Exception {
		ReductionPlan reductionplan = new ReductionPlan("TestDescription", 0.0f, 0.05f, 0.06f, 0.07f);

		assertThat(reductionplan)
						.isEqualTo(reductionplan)
						.isNotNull()
						.isNotEqualTo("someString");

		ReductionPlan reductionPlan1 = new ReductionPlan("TestDescription", 0.0f, 0.05f, 0.06f, 0.07f);
		assertThat(reductionplan).isNotEqualTo(reductionPlan1);

		reductionPlan1.setId(reductionplan.getId());
		assertThat(reductionplan).isEqualTo(reductionPlan1);
	}

	@Test
	void testReductionplanHashCode() throws Exception {
		ReductionPlan reductionplan = testsReductionplan.setup(true);
		reductionplan.setId(1);
		int hashCode = reductionplan.hashCode();
		assertThat(hashCode).isEqualTo(23 * 133 + 1); // Check computed value
		assertThat(reductionplan.hashCode()).isEqualTo(hashCode);
	}

	private int setupTestReductionplan(boolean usingSet) throws Exception {
		ReductionPlan reductionplan = testsReductionplan.setup(usingSet);
		repository.saveAndFlush(reductionplan);
		return reductionplan.getId();
	}

	private void checkReductionplanIntoDb(int id) throws OHServiceException {
		ReductionPlan foundReductionPlan = repository.findById(id).orElse(null);
		assertThat(foundReductionPlan).isNotNull();
		testsReductionplan.check(foundReductionPlan);
	}

	@Test
	public void testGetExamsForReductionPlan_ValidPlan() throws OHServiceException {
		// Étape 1 : Créer et sauvegarder un plan de réduction
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		// Étape 2 : Créer et sauvegarder un type d'examen
		ExamType examType = new ExamType("BT", "Blood Test");
		assertThat(examType.getCode()).isEqualTo("BT");
		examType = examTypeIoOperationRepository.saveAndFlush(examType);

		// Étape 3 : Créer et sauvegarder un examen lié au type
		Exam exam = new Exam("BT001", "Blood Sugar Test", examType, 10, "Default Result");
		assertThat(exam.getCode()).isEqualTo("BT001");
		assertThat(exam.getExamtype().getCode()).isEqualTo("BT");
		exam = examIoOperationRepository.saveAndFlush(exam);

		// Étape 4 : Créer et sauvegarder une réduction d'examen
		ExamsReduction examsReduction = new ExamsReduction(
						new ExamsReductionId(reductionPlan.getId(), exam.getCode()),
						reductionPlan,
						exam,
						50.0
		);
		examsReductionRepository.saveAndFlush(examsReduction);

		// Étape 5 : Tester la récupération des réductions liées au plan
		List<ExamsReduction> reductions = examsReductionRepository.findByReductionPlan(reductionPlan);

		// Vérifications
		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(50.0);
	}

	@Test
	void testUpdateExamReduction_Success() throws OHServiceException, OHException {
		// Étape 1 : Configuration du plan de réduction
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		// Étape 2 : Configuration de l'examen
		ExamType examType = testExamType.setup(false);
		examTypeIoOperationRepository.saveAndFlush(examType);

		Exam exam = testExam.setup(examType, 1, false);
		exam = examIoOperationRepository.saveAndFlush(exam);

		// Étape 3 : Configuration de la relation ExamsReduction
		ExamsReduction examsReduction = new ExamsReduction();
		ExamsReductionId examsReductionId = new ExamsReductionId(reductionPlan.getId(), exam.getCode());
		examsReduction.setId(examsReductionId);
		examsReduction.setReductionPlan(reductionPlan);
		examsReduction.setExam(exam);
		examsReduction.setReductionRate(50.0);

		// Liez et sauvegardez correctement
		reductionPlan.addExamsReduction(examsReduction);
		repository.saveAndFlush(reductionPlan);

		// Étape 4 : Mise à jour du taux de réduction
		double newReductionRate = 30.0;
		boolean result = manager.updateExamReduction(exam.getCode(), reductionPlan.getId(), newReductionRate);

		// Étape 5 : Vérification des résultats
		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getExamsReduction()).hasSize(1);

		ExamsReduction updatedExamsReduction = updatedReductionPlan.getExamsReduction().get(0);
		assertThat(updatedExamsReduction.getReductionRate()).isEqualTo(newReductionRate);
	}

	@Test
	void testUpdateMedicalReductions_Success() throws OHServiceException, OHException {
		// Étape 1 : Configuration du plan de réduction
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		// Étape 2 : Configuration de l'examen
		MedicalType medicalType = testMedicalType.setup(false);
		medicalTypeIoOperationRepository.saveAndFlush(medicalType);

		Medical medical = testMedical.setup(medicalType, false);
		medical = medicalsIoOperationRepository.saveAndFlush(medical);

		// Étape 3 : Configuration de la relation ExamsReduction
		MedicalsReduction medicalsReduction = new MedicalsReduction();
		MedicalsReductionId medicalsReductionId = new MedicalsReductionId(reductionPlan.getId(), medical.getCode());
		medicalsReduction.setId(medicalsReductionId);
		medicalsReduction.setReductionPlan(reductionPlan);
		medicalsReduction.setMedical(medical);
		medicalsReduction.setReductionRate(30.0);

		// Liez et sauvegardez correctement
		reductionPlan.addMedicalsReduction(medicalsReduction);
		repository.saveAndFlush(reductionPlan);

		// Étape 4 : Mise à jour du taux de réduction
		double newReductionRate = 40.0;
		boolean result = manager.updateMedicalsReduction(medical.getCode(), reductionPlan.getId(), newReductionRate);

		// Étape 5 : Vérification des résultats
		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getMedicalsReduction()).hasSize(1);

		MedicalsReduction updateMedicalReduction = updatedReductionPlan.getMedicalsReduction().get(0);
		assertThat(updateMedicalReduction.getReductionRate()).isEqualTo(newReductionRate);
	}
	/*// Test pour valider la liste des MedicalsReduction
	@org.junit.Test
	public void testMedicalsReductionList() {
		Integer medicalCode1 = 123;
		Integer medicalCode2 = 124;
		int reductionPlanId = 1;
		double reductionRate = 30.0;

		// Configuration de deux MedicalsReduction
		MedicalsReduction medicalsReduction1 = setupMedicalReduction(medicalCode1, reductionPlanId);
		MedicalsReduction medicalsReduction2 = setupMedicalReduction(medicalCode2, reductionPlanId);

		List<MedicalsReduction> medicalsReductions = List.of(medicalsReduction1, medicalsReduction2);

		// Vérification
		assertThat(medicalsReductions).hasSize(2);
		checkMedicalReductionAttributes(medicalsReductions.get(0), medicalCode1, reductionPlanId, reductionRate);
		checkMedicalReductionAttributes(medicalsReductions.get(1), medicalCode2, reductionPlanId, reductionRate);
	}
	*/
	@Test
	public void testGetMedicalsForReductionPlan_ValidPlan() throws OHServiceException, OHException {
		// Étape 1 : Créer et sauvegarder un plan de réduction
		ReductionPlan reductionPlan = testsReductionplan.setup(false);
		repository.saveAndFlush(reductionPlan);

		// Étape 2 : Créer et sauvegarder un type médical
		MedicalType medicalType = new MedicalType("BT", "Blood Test");
		assertThat(medicalType.getCode()).isEqualTo("BT");
		medicalTypeIoOperationRepository.saveAndFlush(medicalType);
		assertThat(medicalType).isNotNull();
		assertThat(medicalType.getCode()).isEqualTo("BT");
		// Étape 3 : Créer et sauvegarder un examen lié au type
		Medical medical = testMedical.setup(medicalType, true);
		// Sauvegarde de l'objet médical
		medical = medicalsIoOperationRepository.save(medical);  // Sauvegarde de l'entité médicale
		assertThat(medical.getCode()).isNotNull();
		assertThat(medical.getCode()).isEqualTo(1);
		// Étape 4 : Créer et sauvegarder une réduction d'examen
		MedicalsReduction medicalsReduction = testsMedicalReductionPlan.setupMedicalReduction(new MedicalsReductionId(reductionPlan.getId(), medical.getCode()), medical,reductionPlan, true);
		MedicalsReduction medicalsReduction1 = medicalsReductionRepository.save(medicalsReduction);
		assertThat(medicalsReduction1).isNotNull();
		// Étape 5 : Tester la récupération des réductions liées au plan
		List<MedicalsReduction> reductions = medicalsReductionRepository.findByReductionPlan(reductionPlan);
		// Vérifications
		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(30.0);

		// Étape 6 : Mise à jour du taux de réduction
		double newReductionRate = 20.0;
		medicalsReduction.setReductionRate(newReductionRate);

		// Sauvegarde après mise à jour du taux de réduction
		medicalsReductionRepository.saveAndFlush(medicalsReduction);

		// Étape 7 : Vérification de la mise à jour
		MedicalsReduction updatedReduction = medicalsReductionRepository.findById(medicalsReduction.getId()).orElse(null);
		assertThat(updatedReduction).isNotNull();  // Vérifier que la réduction a été retrouvée
		assertThat(updatedReduction.getReductionRate()).isEqualTo(newReductionRate);  // Vérifier que le taux a été mis à jour

		// Étape 8 : Mise à jour de l'entité `Medical` (ajout du champ @Version)
		// Récupérer l'entité depuis la base de données avant de la mettre à jour
		medical = medicalsIoOperationRepository.findById(medical.getCode()).orElse(null);

		// Appliquer les modifications
		assert medical != null;
		medical.setProdCode("PROD124");  // Exemple de modification du code produit
		medical.setDescription("Updated Description");  // Exemple de modification de la description

		// Sauvegarder après mise à jour
		medical = medicalsIoOperationRepository.saveAndFlush(medical);

		// Vérification après mise à jour
		Medical updatedMedical = medicalsIoOperationRepository.findById(medical.getCode()).orElse(null);
		assertThat(updatedMedical).isNotNull();  // Vérifier que l'entité a été mise à jour
		assertThat(updatedMedical.getProdCode()).isEqualTo("PROD124");  // Vérifier que le code produit a été mis à jour
		assertThat(updatedMedical.getDescription()).isEqualTo("Updated Description");  // Vérifier que la description a été mise à jour
	}



//	@Test
//	public void testGetOperationsForReductionPlan_ValidPlan() throws OHServiceException {
//		// Étape 1 : Créer et sauvegarder un plan de réduction
//		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
//		reductionPlan = repository.saveAndFlush(reductionPlan);
//
//		// Étape 2 : Créer et sauvegarder un type d'examen
//		OperationType operationType = new OperationType("SURGERY", "Surgical Operation");
//		assertThat(operationType.getCode()).isEqualTo("SURGERY"); // Correction ici
//		operationType = operationTypeIoOperationRepository.saveAndFlush(operationType);
//
//		// Création d'un OperationTarget
//		OperationTarget target = OperationTarget.admission;
//
//		// Création de l'instance de Operation
//		Operation operation = new Operation("OP123", "Cataract Surgery", operationType, 2, target);
//		assertThat(operation.getCode()).isEqualTo("OP123"); // Correction ici
//		assertThat(operation.getType().getCode()).isEqualTo("SURGERY"); // Vérifier le code de type d'opération
//		operation = operationIoOperationRepository.saveAndFlush(operation);
//
//		// Étape 4 : Créer et sauvegarder une réduction d'examen
//		OperationsReduction operationsReduction = new OperationsReduction(
//						new OperationsReductionId(reductionPlan.getId(), operation.getCode()),
//						reductionPlan,
//						operation,
//						50.0
//		);
//		operationsReductionRepository.saveAndFlush(operationsReduction);
//
//		// Étape 5 : Tester la récupération des réductions liées au plan
//		List<OperationsReduction> reductions = operationsReductionRepository.findByReductionPlan(reductionPlan);
//
//		// Vérifications
//		assertThat(reductions).isNotNull();
//		assertThat(reductions).hasSize(1);
//		assertThat(reductions.get(0).getReductionRate()).isEqualTo(50.0);
//	}
//
//
//	@Test
//	public void testUpdateOperationReductionPlan_Success() throws OHServiceException, OHException {
//		// Étape 1 : Créer et sauvegarder un plan de réduction
//		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
//		reductionPlan = repository.saveAndFlush(reductionPlan);
//
//		// Étape 2 : Créer et sauvegarder un type d'opération
//		OperationType operationType = new OperationType("SURGERY", "Surgical Operation");
//		operationType = operationTypeIoOperationRepository.saveAndFlush(operationType);  // Sauvegarder dans la base
//		assertThat(operationType.getCode()).isEqualTo("SURGERY"); // Vérifier le code du type d'opération
//
//		// Création d'un OperationTarget
//		OperationTarget target = OperationTarget.admission;
//
//		Operation operation = testOperation.setup(operationType, false);
//		operation = operationIoOperationRepository.saveAndFlush(operation);
//
//		// Étape 3 : Configuration de la relation ExamsReduction
//		OperationsReduction operationsReduction = new OperationsReduction();
//		OperationsReductionId operationsReductionId = new OperationsReductionId(reductionPlan.getId(), operation.getCode());
//		operationsReduction.setId(operationsReductionId);
//		operationsReduction.setReductionPlan(reductionPlan);
//		operationsReduction.setOperation(operation);
//		operationsReduction.setReductionRate(30.0);
//
//		// Liez et sauvegardez correctement
//		reductionPlan.addOperationsReduction(operationsReduction);
//		repository.saveAndFlush(reductionPlan);
//
//		// Étape 4 : Mise à jour du taux de réduction
//		double newReductionRate = 40.0;
//		boolean result = manager.updateOperationsReduction(operation.getCode(), reductionPlan.getId(), newReductionRate);
//
//		// Étape 5 : Vérification des résultats
//		assertThat(result).isTrue();
//
//		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
//		assertThat(updatedReductionPlan).isNotNull();
//		assertThat(updatedReductionPlan.getOperationsReductions()).hasSize(1);
//
//		OperationsReduction updateOperationReduction  = updatedReductionPlan.gettO().get(0);
//		assertThat(updateOperationReduction.getReductionRate()).isEqualTo(newReductionRate);// Vérification du nouveau taux
//	}



}







