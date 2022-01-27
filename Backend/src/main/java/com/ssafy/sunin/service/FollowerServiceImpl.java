package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.dto.FollowerRequest;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final EntityManager em;

    @Override
    public Long addFollower(FollowerRequest followerRequest) {
//         userid랑 follower 할 멤버 userid 필요 저장
        Optional<User> user = userRepository.findById(followerRequest.getId());
        Long count = user.stream().count();
        count++;

        Optional<User> followerMember = userRepository.findById(followerRequest.getFollowerMember());
        // Todo: 이미 팔로워한 유저인지 체크해야함
        Follower follower = Follower.builder()
                .user(user.get())
                .followerMember(followerMember.get())
                .build();

        followerRepository.save(follower);
        return count;
    }

    @Override
    public void deleteFollower(FollowerRequest followerRequest) {
//        Long id = followerRepository.findByUserIdAndFollwerMember(followerRequest);
//        followerRepository.deleteById(id);
    }

    // Todo: 나의 팔로워 수 조회
    @Override
    public Long countFollower(Long id) {
        return followerRepository.getFollowerCount(id);
    }

    // Todo: 나의 팔로잉 수 조회
    @Override
    public Long countFollowing(Long id) {
        return followerRepository.getFollowingCount(id);
    }
}