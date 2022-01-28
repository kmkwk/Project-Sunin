package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.FollowerVO;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Override
    public Long addFollower(FollowerVO followerVO) {
        Optional<User> user = userRepository.findById(followerVO.getUserId());
        Long count = user.stream().count();
        User users = user.get();
        User followerMember = userRepository.findByNo(followerVO.getFollowerMember());
        if(followerRepository.getUser(users,followerMember).size() == 0){
            // Todo: 이미 팔로워한 유저인지 체크해야함
            Follower follower = Follower.builder()
                    .user(users)
                    .followerMember(followerMember)
                    .build();
            count++;
            followerRepository.save(follower);
        }

        return count;
    }

    @Override
    public void deleteFollower(FollowerVO followerVO) {
        User user = userRepository.findByNo(followerVO.getUserId());
        User followerMember = userRepository.findByNo(followerVO.getFollowerMember());
        followerRepository.deleteFollower(user,followerMember);
    }

    @Override
    public Long countFollower(Long id) {
        return followerRepository.getFollowerCount(id);
    }

    @Override
    public Long countFollowing(Long id) {
        return followerRepository.getFollowingCount(id);
    }
}
