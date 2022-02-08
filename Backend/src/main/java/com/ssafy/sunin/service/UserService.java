package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.feed.ImageUpdate;
import com.ssafy.sunin.dto.user.UserProfile;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    private final AmazonS3 amazonS3;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public UserProfile getUserProfile(Long userId){
        User user = userRepository.findProfileByUserSeq(userId);

        return UserProfile.userProfile(user);
    }

    public UserProfile updateUserImage(ImageUpdate imageUpdate){
        User user = userRepository.findProfileByUserSeq(imageUpdate.getId());
        String file = profileUpload(imageUpdate.getImage());
        user.setUserProfileModified(file);

        return UserProfile.userProfile(user);
    }

    public UserProfile delteUserImage(Long userId){
        User user = userRepository.findProfileByUserSeq(userId);
        user.setNormalProfileImage();

        return UserProfile.userProfile(user);
    }

    private String profileUpload(MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        return String.format(url + "/%s",fileName);
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

//    public String signup(UserRequest request){
//        UserRepository.save(User.builder()
//                .userId(request.getUserId())
//                .user_password(request.getUser_password())
//                .user_name(request.getUser_name())
//                .user_nickname(request.getUser_nickname())
//                .user_tel(request.getUser_tel())
//                .user_address(request.getUser_address())
//                .build());
//        return "Success";
//    }
//
//    public User login(String userId, String user_password) {
//        Optional<User> userfind = UserRepository.findByUserId(userId);
//
//        if(userfind.get().getUser_password().equals(user_password)) {
//            User user = UserRepository.findByUserId(userId).orElseThrow();
//            return user;
//        }
//        return null;
//    }
//
//    public String deleteUser(String userId) {
//        UserRepository.delete(UserRepository.findByUserId(userId).orElseThrow(RuntimeException::new));
//        return "Success";
//    }
//
//    public List<User> listUser() throws Exception {
//        return UserRepository.findAll();
//    }
//
//    public User detailUser(String userId) {
//        User user = UserRepository.findByUserId(userId).orElseThrow();
//        return user;
//    }
//
//    public String updateUser(UserRequest request) {
//        Optional<User> user = UserRepository.findByUserId(request.getUserId());
//        user.ifPresent(selectUser->{
//            selectUser.setUser_password(request.getUser_password());
//            selectUser.setUser_name(request.getUser_name());
//            selectUser.setUser_nickname(request.getUser_nickname());
//            selectUser.setUser_address(request.getUser_address());
//            selectUser.setUser_tel(request.getUser_tel());
//            UserRepository.save(selectUser);
//        });
//        return "Success";
//    }

}

