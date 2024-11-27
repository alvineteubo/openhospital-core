package org.isf.reductionplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import org.isf.operation.model.Operation;
import org.isf.operation.service.OperationIoOperationRepository;
import org.isf.opetype.TestOperationType;
import org.isf.opetype.model.OperationType;
import org.isf.opetype.service.OperationTypeIoOperationRepository;
import org.isf.pricesothers.TestPricesOthers;
import org.isf.pricesothers.model.PricesOthers;
import org.isf.pricesothers.service.PriceOthersIoOperationRepository;
import org.isf.reductionplan.manager.ReductionplanBrowserManager;
import org.isf.reductionplan.model.ExamsReduction;
import org.isf.reductionplan.model.ExamsReductionId;
import org.isf.reductionplan.model.MedicalsReduction;
import org.isf.reductionplan.model.MedicalsReductionId;
import org.isf.reductionplan.model.OperationsReduction;
import org.isf.reductionplan.model.OperationsReductionId;
import org.isf.reductionplan.model.OtherReduction;
import org.isf.reductionplan.model.OtherReductionId;
import org.isf.reductionplan.model.ReductionPlan;
import org.isf.reductionplan.service.ExamsReductionRepository;
import org.isf.reductionplan.service.MedicalsReductionRepository;
import org.isf.reductionplan.service.OperationsReductionRepository;
import org.isf.reductionplan.service.OthersReductionRepository;
import org.isf.reductionplan.service.ReductionPlanIoOperations;
import org.isf.reductionplan.service.ReductionplanIoOperationRepository;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHException;
import org.isf.utils.exception.OHServiceException;
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
	private static TestsOperationReductionPlan testsOperationReductionPlan;
	private static TestOperationType testOperationType;
	private static TestPricesOthers testPricesOthers;
	private static TestsOtherReductionPlan testsOtherReductionPlan;

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

	@Autowired
	OperationsReductionRepository operationsReductionRepository;

	@Autowired
	OperationIoOperationRepository operationIoOperationRepository;

	@Autowired
	OperationTypeIoOperationRepository operationTypeIoOperationRepository;

	@Autowired
	PriceOthersIoOperationRepository priceOthersIoOperationRepository;

	@Autowired
	OthersReductionRepository othersReductionRepository;

	@BeforeAll
	static void setUpClass() {
		testsReductionplan = new TestsReductionplan();
		testsExamReductionPlan = new TestsExamReductionPlan();
		testsMedicalReductionPlan = new TestsMedicalReductionPlan();
		testExam = new TestExam();
		testExamType = new TestExamType();
		testMedical = new TestMedical();
		testMedicalType = new TestMedicalType();
		testOperation = new TestOperation();
		testPricesOthers = new TestPricesOthers();
		testsOperationReductionPlan = new TestsOperationReductionPlan();
		testsOtherReductionPlan = new TestsOtherReductionPlan();
		testOperationType = new TestOperationType();

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
	public void testReductionplanUsingConstructor() {
		ReductionPlan reductionplan = testsReductionplan.setup(true);
		testsReductionplan.check(reductionplan);
	}


	@Test
	public void testReductionplanUsingSetters() {
		ReductionPlan reductionplan = testsReductionplan.setup(false);
		testsReductionplan.check(reductionplan);
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
		reductionplan.setId(1);

		ReductionPlan reductionPlan1 = new ReductionPlan("TestDescription", 0.0f, 0.05f, 0.06f, 0.07f);
		reductionPlan1.setId(1);

		assertThat(reductionplan).isEqualTo(reductionPlan1);
	}

	@Test
	void testReductionplanHashCode() {
		ReductionPlan reductionplan = new ReductionPlan();
		reductionplan.setId(1);

		// Le hashCode actuel utilise uniquement l'ID.
		int expectedHashCode = Objects.hash(1);

		assertThat(reductionplan.hashCode()).isEqualTo(expectedHashCode);
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

		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		ExamType examType = new ExamType("BT", "Blood Test");
		assertThat(examType.getCode()).isEqualTo("BT");
		examType = examTypeIoOperationRepository.saveAndFlush(examType);

		Exam exam = new Exam("BT001", "Blood Sugar Test", examType, 10, "Default Result");
		assertThat(exam.getCode()).isEqualTo("BT001");
		assertThat(exam.getExamtype().getCode()).isEqualTo("BT");
		exam = examIoOperationRepository.saveAndFlush(exam);

		ExamsReduction examsReduction = new ExamsReduction(
						new ExamsReductionId(reductionPlan.getId(), exam.getCode()),
						reductionPlan,
						exam,
						50.0
		);
		examsReductionRepository.saveAndFlush(examsReduction);

		List<ExamsReduction> reductions = examsReductionRepository.findByReductionPlan(reductionPlan);

		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(50.0);
	}

	@Test
	void testUpdateExamReduction_Success() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		ExamType examType = testExamType.setup(false);
		examTypeIoOperationRepository.saveAndFlush(examType);

		Exam exam = testExam.setup(examType, 1, false);
		exam = examIoOperationRepository.saveAndFlush(exam);

		ExamsReduction examsReduction = new ExamsReduction();
		ExamsReductionId examsReductionId = new ExamsReductionId(reductionPlan.getId(), exam.getCode());
		examsReduction.setId(examsReductionId);
		examsReduction.setReductionPlan(reductionPlan);
		examsReduction.setExam(exam);
		examsReduction.setReductionRate(50.0);

		reductionPlan.addExamsReduction(examsReduction);
		repository.saveAndFlush(reductionPlan);

		double newReductionRate = 30.0;
		boolean result = manager.updateExamReduction(exam.getCode(), reductionPlan.getId(), newReductionRate);

		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getExamsReduction()).hasSize(1);

		ExamsReduction updatedExamsReduction = updatedReductionPlan.getExamsReduction().get(0);
		assertThat(updatedExamsReduction.getReductionRate()).isEqualTo(newReductionRate);
	}

	@Test
	void testUpdateMedicalReductions_Success() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		MedicalType medicalType = testMedicalType.setup(false);
		medicalTypeIoOperationRepository.saveAndFlush(medicalType);

		Medical medical = testMedical.setup(medicalType, false);
		medical = medicalsIoOperationRepository.saveAndFlush(medical);

		MedicalsReduction medicalsReduction = new MedicalsReduction();
		MedicalsReductionId medicalsReductionId = new MedicalsReductionId(reductionPlan.getId(), medical.getCode());
		medicalsReduction.setId(medicalsReductionId);
		medicalsReduction.setReductionPlan(reductionPlan);
		medicalsReduction.setMedical(medical);
		medicalsReduction.setReductionRate(30.0);

		reductionPlan.addMedicalsReduction(medicalsReduction);
		repository.saveAndFlush(reductionPlan);

		double newReductionRate = 40.0;
		boolean result = manager.updateMedicalsReduction(medical.getCode(), reductionPlan.getId(), newReductionRate);

		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getMedicalsReduction()).hasSize(1);

		MedicalsReduction updateMedicalReduction = updatedReductionPlan.getMedicalsReduction().get(0);
		assertThat(updateMedicalReduction.getReductionRate()).isEqualTo(newReductionRate);
	}

	@Test
	public void testGetMedicalsForReductionPlan_ValidPlan() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = testsReductionplan.setup(false);
		repository.saveAndFlush(reductionPlan);

		MedicalType medicalType = new MedicalType("BT", "Blood Test");
		assertThat(medicalType.getCode()).isEqualTo("BT");
		medicalTypeIoOperationRepository.saveAndFlush(medicalType);
		assertThat(medicalType).isNotNull();
		assertThat(medicalType.getCode()).isEqualTo("BT");

		Medical medical = testMedical.setup(medicalType, true);
		medical = medicalsIoOperationRepository.save(medical);
		assertThat(medical.getCode()).isNotNull();
		assertThat(medical.getCode()).isEqualTo(1);

		MedicalsReduction medicalsReduction = testsMedicalReductionPlan.setupMedicalReduction(
						new MedicalsReductionId(reductionPlan.getId(), medical.getCode()), medical, reductionPlan, true);
		MedicalsReduction medicalsReduction1 = medicalsReductionRepository.save(medicalsReduction);
		assertThat(medicalsReduction1).isNotNull();

		List<MedicalsReduction> reductions = medicalsReductionRepository.findByReductionPlan(reductionPlan);
		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(30.0);

		double newReductionRate = 20.0;
		medicalsReduction.setReductionRate(newReductionRate);
		medicalsReductionRepository.saveAndFlush(medicalsReduction);

		MedicalsReduction updatedReduction = medicalsReductionRepository.findById(medicalsReduction.getId()).orElse(null);
		assertThat(updatedReduction).isNotNull();
		assertThat(updatedReduction.getReductionRate()).isEqualTo(newReductionRate);

		medical = medicalsIoOperationRepository.findById(medical.getCode()).orElse(null);
		assert medical != null;
		medical.setProdCode("PROD124");
		medical.setDescription("Updated Description");

		medical = medicalsIoOperationRepository.saveAndFlush(medical);

		Medical updatedMedical = medicalsIoOperationRepository.findById(medical.getCode()).orElse(null);
		assertThat(updatedMedical).isNotNull();
		assertThat(updatedMedical.getProdCode()).isEqualTo("PROD124");
		assertThat(updatedMedical.getDescription()).isEqualTo("Updated Description");
	}

	@Test
	public void testGetOperationForReductionPlan_ValidPlan() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = testsReductionplan.setup(false);
		repository.saveAndFlush(reductionPlan);

		OperationType operationType = new OperationType("OP", "Stomach Operation");
		operationTypeIoOperationRepository.saveAndFlush(operationType);

		Operation operation = testOperation.setup(operationType, true);
		operation.setCode("ZZ");
		operation = operationIoOperationRepository.saveAndFlush(operation);
		assertThat(operation.getCode()).isEqualTo("ZZ");

		OperationsReduction operationsReduction = testsOperationReductionPlan.setupOperationReduction(
						new OperationsReductionId(reductionPlan.getId(), operation.getCode()), operation, reductionPlan, true);
		operationsReductionRepository.saveAndFlush(operationsReduction);

		List<OperationsReduction> reductions = operationsReductionRepository.findByReductionPlan(reductionPlan);
		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(30.0);
	}

	@Test
	void testUpdateOperationReductions_Success() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		OperationType operationType = testOperationType.setup(false);
		operationTypeIoOperationRepository.saveAndFlush(operationType);

		Operation operation = testOperation.setup(operationType, false);
		operation = operationIoOperationRepository.saveAndFlush(operation);

		OperationsReduction operationsReduction = new OperationsReduction();
		OperationsReductionId operationsReductionId = new OperationsReductionId(reductionPlan.getId(), operation.getCode());
		operationsReduction.setId(operationsReductionId);
		operationsReduction.setReductionPlan(reductionPlan);
		operationsReduction.setOperation(operation);
		operationsReduction.setReductionRate(30.0);

		reductionPlan.addOperationsReduction(operationsReduction);
		repository.saveAndFlush(reductionPlan);

		double newReductionRate = 40.0;
		boolean result = manager.updateOperationsReduction(operation.getCode(), reductionPlan.getId(), newReductionRate);
		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getOperationsReductions()).hasSize(1);

		OperationsReduction updateOperationReduction = updatedReductionPlan.getOperationsReductions().get(0);
		assertThat(updateOperationReduction.getReductionRate()).isEqualTo(newReductionRate);
	}

	@Test
	public void testGetOthersForReductionPlan_ValidPlan() throws OHServiceException, OHException {
		ReductionPlan reductionPlan = testsReductionplan.setup(false);
		repository.saveAndFlush(reductionPlan);

		PricesOthers pricesOthers = testPricesOthers.setup(true);
		pricesOthers.setCode("zz".toUpperCase()); // Conversion explicite en majuscules
		pricesOthers = priceOthersIoOperationRepository.saveAndFlush(pricesOthers);
		assertThat(pricesOthers.getCode()).isEqualTo("ZZ");

		OtherReduction otherReduction = testsOtherReductionPlan.setupOtherReduction(
						new OtherReductionId(reductionPlan.getId(), pricesOthers.getId()), pricesOthers, reductionPlan, true);
		othersReductionRepository.saveAndFlush(otherReduction);

		List<OtherReduction> reductions = othersReductionRepository.findByReductionPlan(reductionPlan);
		assertThat(reductions).isNotNull();
		assertThat(reductions).hasSize(1);
		assertThat(reductions.get(0).getReductionRate()).isEqualTo(30.0);
	}

	@Test
	void testUpdateOthersReductions_Success() throws OHServiceException, OHException {

		ReductionPlan reductionPlan = new ReductionPlan("Test Reduction Plan", 10.0, 20.0, 30.0, 40.0);
		reductionPlan = repository.saveAndFlush(reductionPlan);

		PricesOthers pricesOthers = testPricesOthers.setup(false);
		pricesOthers = priceOthersIoOperationRepository.saveAndFlush(pricesOthers);

		OtherReduction otherReduction = new OtherReduction();
		OtherReductionId otherReductionId = new OtherReductionId(reductionPlan.getId(), pricesOthers.getId());
		otherReduction.setId(otherReductionId);
		otherReduction.setReductionPlan(reductionPlan);
		otherReduction.setPricesOthers(pricesOthers);
		otherReduction.setReductionRate(30.0);

		reductionPlan.addOtherReduction(otherReduction);
		repository.saveAndFlush(reductionPlan);

		double newReductionRate = 40.0;
		boolean result = manager.updateOthersReduction(pricesOthers.getId(), reductionPlan.getId(), newReductionRate);

		assertThat(result).isTrue();

		ReductionPlan updatedReductionPlan = repository.findById(reductionPlan.getId()).orElse(null);
		assertThat(updatedReductionPlan).isNotNull();
		assertThat(updatedReductionPlan.getOtherReductions()).hasSize(1);

		OtherReduction updateOtherReduction = updatedReductionPlan.getOtherReductions().get(0);
		assertThat(updateOtherReduction.getReductionRate()).isEqualTo(newReductionRate);
	}

//	@Test
//	void testAddExamToReductionPlan() throws OHException {
//		ReductionPlan reductionPlan = testsReductionplan.setup(false);
//		repository.saveAndFlush(reductionPlan);
//
//
//		ExamType examType = testExamType.setup(true);
//		examTypeIoOperationRepository.saveAndFlush(examType);
//
//
//		Exam exam = testExam.setup(examType, 2, true);
//		examIoOperationRepository.saveAndFlush(exam); // Sauvegarde de l'examen dans la base
//
//
//		boolean result = repository.addExamToReductionPlan(reductionPlan.getId(), exam.getCode(), 30.0);
//
//
//		assertThat(result).isTrue();
//
//
//		List<ExamsReduction> reductions = examsReductionRepository.findAll();
//		assertThat(reductions).hasSize(1);
//
//
//		ExamsReduction savedReduction = reductions.get(0);
//		testsExamReductionPlan.checkExamReductionAttributes(
//						savedReduction,
//						exam.getCode(),
//						reductionPlan.getId(),
//						30.0
//		);
//	}

}







