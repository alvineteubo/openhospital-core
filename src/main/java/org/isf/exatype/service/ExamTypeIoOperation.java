/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2023 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
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
package org.isf.exatype.service;

import java.util.List;

import org.isf.exatype.model.ExamType;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = OHServiceException.class)
@TranslateOHServiceException
public class ExamTypeIoOperation {

	private final ExamTypeIoOperationRepository repository;

	public ExamTypeIoOperation(ExamTypeIoOperationRepository examTypeIoOperationRepository) {
		this.repository = examTypeIoOperationRepository;
	}

	/**
	 * Return the list of {@link ExamType}s.
	 * @return the list of {@link ExamType}s.
	 * @throws OHServiceException
	 */
	public List<ExamType> getExamType() throws OHServiceException {
		return repository.findAllByOrderByDescriptionAsc();
	}

	/**
	 * Update an already existing {@link ExamType}.
	 * @param examType - the {@link ExamType} to update
	 * @return the updated {@link ExamType}.
	 * @throws OHServiceException
	 */
	public ExamType updateExamType(ExamType examType) throws OHServiceException {
		return repository.save(examType);
	}

	/**
	 * Insert a new {@link ExamType} in the DB.
	 * @param examType - the {@link ExamType} to insert.
	 * @return the newly persisted {@link ExamType}.
	 * @throws OHServiceException
	 */
	public ExamType newExamType(ExamType examType) throws OHServiceException {
		return repository.save(examType);
	}

	/**
	 * Delete the passed {@link ExamType}.
	 * @param examType - the {@link ExamType} to delete.
	 * @throws OHServiceException
	 */
	public void deleteExamType(ExamType examType) throws OHServiceException {
		repository.delete(examType);
	}

	/**
	 * This function controls the presence of a record with the same code as in the parameter.
	 * @param code - the code
	 * @return {@code true} if the code is present, {@code false} otherwise.
	 * @throws OHServiceException
	 */
	public boolean isCodePresent(String code) throws OHServiceException {
		return repository.existsById(code);
	}

	/**
	 * Find exam type by code
	 * @param code - the code
	 * @return The exam type if found, {@code null} otherwise.
	 * @throws OHServiceException
	 */
	public ExamType findByCode(String code) throws OHServiceException {
		return repository.findById(code).orElse(null);
	}
}
