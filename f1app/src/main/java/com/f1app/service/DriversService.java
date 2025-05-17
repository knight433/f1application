package com.f1app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.ServiceConstants;
import com.f1app.model.DriversModel;
import com.f1app.repository.DriversRepo;

import jakarta.validation.Validator;



import jakarta.validation.ConstraintViolation;

@Service
public class DriversService {

    @Autowired
    private DriversRepo driversRepo;

    private Logger log = LogManager.getLogger(getClass());

    @Autowired
    private Validator validator;

    private void checkForNull(DriversModel model) throws CrudOperationException {
        if (model == null) {
            throw CrudOperationException.asNullEntity(DriversModel.class);
        }
    }

    private void validate(DriversModel model) throws CrudValidationException {
        Set<ConstraintViolation<DriversModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw CrudValidationException.asFailedValidationOperation(DriversModel.class, violations);
        }
    }

    private void checkId(long id) throws CrudValidationException {
        if (id <= 0) {
            throw CrudValidationException.asInvalidEntityId(DriversModel.class);
        }
    }

    private DriversModel save(DriversModel model) throws CrudOperationException {
        try {
            boolean isNew = model.getId() <= 0;
            DriversModel saved = driversRepo.save(model);
            log.info((isNew ? "Added" : "Updated") + " DriversModel with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            throw CrudOperationException.asFailedAddOperation(DriversModel.class, e);
        }
    }

    public DriversModel getById(Long id) throws CrudOperationException, CrudValidationException {
        checkId(id);
        Optional<DriversModel> result = driversRepo.findById(id);
        checkForNull(result.get());
        return result.get();
    }

    public List<DriversModel> getAll(int page, int size) throws CrudOperationException {
        try {
            Pageable pageable = PageRequest.of(
                page < ServiceConstants.STARTING_PAGE_NUMBER ? ServiceConstants.STARTING_PAGE_NUMBER : page,
                size <= 0 ? ServiceConstants.DEFAULT_ITEMS_PER_PAGE : size);
            return driversRepo.findAll(pageable).getContent();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(getClass(), e);
        }
    }

    public DriversModel addNewDriversModel(DriversModel model) throws CrudValidationException, CrudOperationException {
        checkForNull(model);
        //add any backend update here 
        validate(model);        return save(model);
    }

    public long getCountOfdriverss() throws CrudOperationException {
        try {
            return driversRepo.count();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(DriversModel.class, e);
        }
    }
}
