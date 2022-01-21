package com.ssafy.sunin.user;

import com.ssafy.sunin.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository UserRepository;

    public String signup(UserRequest request){
        UserRepository.save(User.builder()
                .userId(request.getUserId())
                .user_password(request.getUser_password())
                .user_name(request.getUser_name())
                .user_nickname(request.getUser_nickname())
                .user_tel(request.getUser_tel())
                .user_address(request.getUser_address())
                .build());
        return "Success";
    }

    public User login(String userId, String user_password) {
        Optional<User> userfind = UserRepository.findByUserId(userId);
//    	log.info("db password = {}, input password = {}", user.get().getUser_password(), user_password);
        if(userfind.get().getUser_password().equals(user_password)) {
            User user = UserRepository.findByUserId(userId).orElseThrow();
            return user;
        }
        return null;
    }

    public String deleteUser(String userId) {
        UserRepository.delete(UserRepository.findByUserId(userId).orElseThrow(RuntimeException::new));
        return "Success";
    }

    public List<User> listUser() throws Exception {
        return UserRepository.findAll();
    }

    public User detailUser(String userId) {
        User user = UserRepository.findByUserId(userId).orElseThrow();
        return user;
    }

    public String updateUser(UserRequest request) {
        Optional<User> user = UserRepository.findByUserId(request.getUserId());
        user.ifPresent(selectUser->{
            selectUser.setUser_password(request.getUser_password());
            selectUser.setUser_name(request.getUser_name());
            selectUser.setUser_nickname(request.getUser_nickname());
            selectUser.setUser_address(request.getUser_address());
            selectUser.setUser_tel(request.getUser_tel());
            UserRepository.save(selectUser);
        });
        return "Success";
    }

}

