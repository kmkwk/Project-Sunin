package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.dto.FollowerVO;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final SocialUserRepository userRepository;

    @Override
    public Long addFollower(FollowerVO followerVO) {
//        Optional<User> user = userRepository.findById(followerVO.getUserId());
//        Long count = user.stream().count();
//        User users = user.get();
//        User followerMember = userRepository.findByUserSeq(followerVO.getFollowerMember());
////        User followerMember = userRepository.findByNo(followerVO.getFollowerMember());
//        if(followerRepository.getUser(users,followerMember).size() == 0){
//            // Todo: 이미 팔로워한 유저인지 체크해야함
//            Follower follower = Follower.builder()
//                    .user(users)
//                    .followerMember(followerMember)
//                    .build();
//            count++;
//            followerRepository.save(follower);
//        }
//
//        return count;
        return 0L;
    }

    @Override
    public void deleteFollower(FollowerVO followerVO) {
        Follower follower = followerRepository.getUser(followerVO);
        followerRepository.deleteFollower(follower);

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
