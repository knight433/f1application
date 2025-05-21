package com.f1app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.model.LoginRequest;
import com.f1app.model.UserModel;
import com.f1app.service.UserService;


@RestController
@RequestMapping("/f1buzz/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger log = LogManager.getLogger(getClass());

    private Map<String, Object> sanitizeUser(UserModel user) {
    Map<String, Object> safeUser = new HashMap<>();
    safeUser.put("id", user.getId());
    safeUser.put("username", user.getUsername());
    safeUser.put("fullName", user.getFullName());
    safeUser.put("country", user.getCountry());
    safeUser.put("role", user.getRole());
    safeUser.put("isActive", user.isIsActive());
    safeUser.put("createdAt", user.getCreatedAt());
    safeUser.put("updatedAt", user.getUpdatedAt());
    return safeUser;
}
    
    
    @GetMapping("/get/all/page/{page}/size/{size}")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<UserModel> usermodels = userService.getAll(page, size);
            if (usermodels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No usermodels found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(usermodels);
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUserModel(@RequestBody UserModel usermodel) {
        if (usermodel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was no usermodel model given");
        }
        try {
            UserModel savedUserModel = userService.addNewUserModel(usermodel);
            return ResponseEntity.status(HttpStatus.OK).body(savedUserModel);
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/count")
    public ResponseEntity<?> getCountOfuser() {
        try {
            long count = userService.getCountOfusers();
            return ResponseEntity.status(HttpStatus.OK).body(count);
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserModel user = userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (user != null) {
                Map<String, Object> sanitizedUser = sanitizeUser(user);
                return ResponseEntity.status(HttpStatus.OK).body(sanitizedUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

        } catch (CrudValidationException e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    


    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserModel usermodel) {
       try {
            UserModel updatedUserModel = userService.updateUser(usermodel);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUserModel);
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    

}