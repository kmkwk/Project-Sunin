package com.ssafy.sunin.user;

import com.ssafy.sunin.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(UserRequest request){
        userRepository.save(User.builder()
                .userId(request.getUserId())
//				.userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userPassword(request.getUserPassword())
                .userName(request.getUserName())
                .userNickname(request.getUserNickname())
                .userTel(request.getUserTel())
                .userAddress(request.getUserAddress())
                .build());
        return "Success";
    }

    public User login(String userId, String user_password) {
        Optional<User> userfind = userRepository.findByUserId(userId);
//    	log.info("db password = {}, input password = {}", user.get().getUser_password(), user_password);
        if(userfind.get().getUserPassword().equals(user_password))
//		if(!passwordEncoder.matches(userfind.get().getUserPassword(),(user_password)))
        {
            User user = userRepository.findByUserId(userId).orElseThrow();
            return user;
        }
        return null;
    }

    public String deleteUser(String userId) {
        userRepository.delete(userRepository.findByUserId(userId).orElseThrow(RuntimeException::new));
        return "Success";
    }

    public List<User> listUser() throws Exception {
        return userRepository.findAll();
    }

    public User detailUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow();
        return user;
    }

    public String updateUser(UserRequest request) {
        Optional<User> user = userRepository.findByUserId(request.getUserId());
        user.ifPresent(selectUser->{
            selectUser.setUserPassword(request.getUserPassword());
            selectUser.setUserName(request.getUserName());
            selectUser.setUserNickname(request.getUserNickname());
            selectUser.setUserAddress(request.getUserAddress());
            selectUser.setUserTel(request.getUserTel());
            userRepository.save(selectUser);
        });
        return "Success";
    }

}

