package app.service;

import app.dto.ChangePasswordDTO;
import app.dto.UserDTO;
import app.repository.UserRepository;
import app.dto.UserLogin;
import app.entity.User;
import app.utils.CheckFormat;
import app.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CheckFormat checkFormat;

    private static  String salt = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

    @Autowired
    private UserRepository userRepository;

    public String loginUser(UserLogin logInUser) {
        User user = userRepository.findUserByUsername(logInUser.getEmail());
        //System.out.println(user.getUsername());
        boolean newpasswordMatch = PasswordUtils.verifyUserPassword(logInUser.getPassword(), user.getPassword(), salt);
        if(user!=null && (user.getPassword().equals(logInUser.getPassword()) || newpasswordMatch)) {
            UserLogin userLogin = new UserLogin(user.getUsername(), user.getPassword());
            System.out.println("we are fine");
            return "true";
        }
        else{
            return  "false";
        }
    }

    public UserDTO getUser(String username) {
        User user = new User();
        user = userRepository.findUserByUsername(username);
        UserDTO userLogin = new UserDTO(user.getIduser(),user.getUsername(),user.getPassword());
        return userLogin;
    }

    public UserDTO update(int id, UserDTO userDTO) {
        User user = userRepository.getOne(id);
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getEmail());
        userRepository.save(user);
        return userDTO;
    }

    public boolean changePass(int id, ChangePasswordDTO changePassword) {
        User user = userRepository.getOne(id);
        if (user == null) {
            return false;
        }
        System.out.println(salt);
      //  boolean newpasswordMatch = PasswordUtils.verifyUserPassword(changePassword.getNewPassword(), user.getPassword(), salt);
//        if (user.getPassword().equals(changePassword.getNewPassword())) {
//            System.out.println(changePassword.getNewPassword());
//            System.out.println(user.getPassword());
//            return "You must use a different password";
//        }
//        boolean oldpasswordMatch = PasswordUtils.verifyUserPassword(changePassword.getOldPassword(), user.getPassword(), salt);
//        if (!oldpasswordMatch) {
//            return "Incorrect old password";
//        }
//        if (!this.checkFormat.checkPassword(changePassword.getNewPassword())) {
//            return "Password must contain at least 1 upper case letter, 1 lower case leter, 1 digit, 1 special caracter(@,#,$,%,^,*,!,?,<,>,&,+,=), must have at least 8 characters and can't contain 'space' ";
//        }

        System.out.println(changePassword.getNewPassword());
        String mySecurePassword2 = PasswordUtils.generateSecurePassword(changePassword.getNewPassword(), salt);
        user.setPassword(mySecurePassword2);
        userRepository.save(user);
        return true;

    }



}
