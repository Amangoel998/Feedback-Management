package com.cg.feedback.service;

import com.cg.feedback.dao.AdminDAO;
import com.cg.feedback.dao.LoginDAO;
import com.cg.feedback.dao.StudentDAO;
import com.cg.feedback.dao.TrainerDAO;
import com.cg.feedback.dto.AdminDTO;
import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.LoginResponse;
import com.cg.feedback.dto.StudentDTO;
import com.cg.feedback.dto.TrainerDTO;
import com.cg.feedback.exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private LoginDAO loginDao;
    private AdminDAO admDao;
    private StudentDAO stdDao;
    private TrainerDAO trnDao;

    @Autowired
    public UserServiceImpl(LoginDAO loginDao,AdminDAO admDao, StudentDAO stdDao, TrainerDAO trnDao) {
        this.loginDao = loginDao;
        this.stdDao = stdDao;
        this.trnDao = trnDao;
        this.admDao = admDao;
    }

    public LoginResponse AuthenticateUser(LoginCredentials credentials) throws CustomException {
        // try{
            if (!loginDao.existsById(credentials.getId()))
                throw new CustomException("No User with ID Found");
            LoginCredentials result = loginDao.validateUser(credentials.getId(), credentials.getPassword());
            if (result == null)
                throw new CustomException("Validation Failed");
            LoginResponse res = getUserInfo(result);
            if(res==null)
                throw new CustomException("The user info doesn't Exists");
            return res;
        // }catch(Exception e){
        //     throw new CustomException(e.getMessage());
        // }
    }

    private LoginResponse getUserInfo(LoginCredentials credentials) throws CustomException{
        switch (credentials.getRole().toLowerCase()) {
            case "student":
                StudentDTO student = stdDao.findById(credentials.getId()).get();
                if (student == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(student.getStudentId(), "student", student.getStudentName(),
                        student.getStudentEmail());
            case "trainer":
                TrainerDTO trainer = trnDao.findById(credentials.getId()).get();
                if (trainer == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(trainer.getTrainerId(), "trainer", trainer.getTrainerName(),
                        trainer.getTrainerEmail());
            case "admin":
            System.out.println(credentials);
                AdminDTO admin = admDao.findById(credentials.getId()).get();
                if (admin == null)
                    throw new CustomException("Invalid Data Store");
                return new LoginResponse(admin.getAdminId(), "admin", admin.getAdminName(), admin.getAdminEmail());
            default:
                throw new CustomException("Invalid Data");
        }
    }

    @Override
    public LoginResponse createUser(LoginCredentials credentials) throws CustomException {
        try {
            if (loginDao.existsById(credentials.getId()))
                throw new CustomException("User Credentials already Exists ID");
            LoginCredentials result = loginDao.save(credentials);
            if (result == null)
                throw new CustomException("Couldn't Create User Login");    
            LoginResponse res = getUserInfo(result);
            return res;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}