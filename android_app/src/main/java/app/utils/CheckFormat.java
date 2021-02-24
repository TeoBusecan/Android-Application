package app.utils;


import org.springframework.stereotype.Service;

@Service
public class CheckFormat {
    public Boolean checkEmail(String email) {
        if (!email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)@[A-Za-z0-9-]+(.[A-Za-z0-9]+)(.[A-Za-z]{2,})$")) {
            return false;
        }
        return true;
    }

    public Boolean checkPassword(String password) {
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*!?<>&+=])(?=\\S+$).{8,}$")) {
            return false;
        }
        return true;
    }

}
