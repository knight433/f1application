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
import com.f1app.model.TeamModel;
import com.f1app.repository.TeamRepo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    private Logger log = LogManager.getLogger(getClass());

    @Autowired
    private Validator validator;

    private void checkForNull(TeamModel model) throws CrudOperationException {
        if (model == null) {
            throw CrudOperationException.asNullEntity(TeamModel.class);
        }
    }

    private void validate(TeamModel model) throws CrudValidationException {
        Set<ConstraintViolation<TeamModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw CrudValidationException.asFailedValidationOperation(TeamModel.class, violations);
        }
    }

    private void checkId(long id) throws CrudValidationException {
        if (id <= 0) {
            throw CrudValidationException.asInvalidEntityId(TeamModel.class);
        }
    }

    private TeamModel save(TeamModel model) throws CrudOperationException {
        try {
            boolean isNew = model.getId() <= 0;
            TeamModel saved = teamRepo.save(model);
            log.info((isNew ? "Added" : "Updated") + " TeamModel with ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            throw CrudOperationException.asFailedAddOperation(TeamModel.class, e);
        }
    }

    public TeamModel getById(Long id) throws CrudOperationException, CrudValidationException {
        checkId(id);
        Optional<TeamModel> result = teamRepo.findById(id);
        checkForNull(result.get());
        return result.get();
    }

    public List<TeamModel> getAll(int page, int size) throws CrudOperationException {
        try {
            Pageable pageable = PageRequest.of(
                page < ServiceConstants.STARTING_PAGE_NUMBER ? ServiceConstants.STARTING_PAGE_NUMBER : page,
                size <= 0 ? ServiceConstants.DEFAULT_ITEMS_PER_PAGE : size);
            return teamRepo.findAll(pageable).getContent();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(getClass(), e);
        }
    }

    public TeamModel addNewTeamModel(TeamModel model) throws CrudValidationException, CrudOperationException {
        checkForNull(model);
        //add any backend update here 
        validate(model);        return save(model);
    }

    public long getCountOfteams() throws CrudOperationException {
        try {
            return teamRepo.count();
        } catch (Exception e) {
            throw CrudOperationException.asFailedGetOperation(TeamModel.class, e);
        }
    }
}
