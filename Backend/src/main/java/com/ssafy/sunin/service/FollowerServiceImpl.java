package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.follower.FollowerUser;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Override
    public Long addFollower(FollowerUser followerUser) {
        Optional<User> user = userRepository.findById(followerUser.getUserId());
        Long count = user.stream().count();
        User users = user.get();
        User followerMember = userRepository.findFollowerByUserSeq(followerUser.getFollowerMember());

        if(followerRepository.getUser(followerUser) == null){
            Follower follower = Follower.builder()
                    .user(users)
                    .followerMember(followerMember)
                    .createDate(followerMember.getCreatedAt())
                    .build();
            count++;
            followerRepository.save(follower);
        }

        return count;
    }

    @Override
    public void deleteFollower(FollowerUser followerUser) {
        Follower follower = followerRepository.getUser(followerUser);
        followerRepository.deleteFollower(follower);

    }

    @Override
    public int countFollower(Long userId) {
        return followerRepository.getFollowerCount(userId);
    }

    @Override
    public int countFollowing(Long followerMember) {
        return followerRepository.getFollowingCount(followerMember);
    }
}
