package com.ssafy.sunin.user;

import com.ssafy.sunin.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String login(String userId, String user_password) {
        Optional<User> user = UserRepository.findByUserId(userId);
//    	log.info("db password = {}, input password = {}", user.get().getUser_password(), user_password);
        if(user.get().getUser_password().equals(user_password)) {
            return "Success";
        }
        return "Failed";
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

