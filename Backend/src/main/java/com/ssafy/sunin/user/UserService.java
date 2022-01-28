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
                .userPassword(request.getUserPassword())
                .userName(request.getUserName())
                .userNickname(request.getUserNickname())
                .userTel(request.getUserTel())
                .userAddress(request.getUserAddress())
                .build());
        return "Success";
    }

    public User login(String userId, String user_password) {
        Optional<User> userfind = UserRepository.findByUserId(userId);

        if(userfind.get().getUserPassword().equals(user_password)) {
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
            selectUser.setUserPassword(request.getUserPassword());
            selectUser.setUserName(request.getUserName());
            selectUser.setUserNickname(request.getUserNickname());
            selectUser.setUserAddress(request.getUserAddress());
            selectUser.setUserTel(request.getUserTel());
            UserRepository.save(selectUser);
        });
        return "Success";
    }

}

