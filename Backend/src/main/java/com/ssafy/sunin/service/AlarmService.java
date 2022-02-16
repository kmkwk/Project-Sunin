package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Alarm;
import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.follower.FollowerUser;
import com.ssafy.sunin.payload.response.alarm.AlarmResponse;
import com.ssafy.sunin.repository.AlarmRepository;
import com.ssafy.sunin.repository.FeedRepository;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final FollowerRepository followerRepository;

    public Alarm writeMessage(Long fromUserId, Long toUserId, String message){
        Alarm alarm = Alarm.alarm(fromUserId,toUserId,message);
        return alarmRepository.save(alarm);
    }

    public List<AlarmResponse> getMessage(Long toUserId) {
        List<Alarm> messages = alarmRepository.findAllByToUserId(toUserId);
        // 아이디 중복 처리
        Set<Long> setUser = messages.stream()
                .map(Alarm::getFromUserId)
                .collect(Collectors.toSet());

        // 각 메시지에 보내는 사람이랑 프로필 묶어서 보내기
        Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(setUser).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        return AlarmResponse.alarmResponseList(messages,userMap);
    }

    public void deleteMessage(Long id){
        alarmRepository.deleteById(id);
    }

    public boolean getLikeUser(Long fromUserId,String feedId){
        FeedCollections feedCollections = feedRepository.findById(new ObjectId(feedId)).get();
        if(feedCollections.getLikeUser().containsKey(fromUserId)){
            return false;
        }
        return true;
    }

    public Follower getFollower(Long fromUserId, Long toUserId){
        FollowerUser followerUser = new FollowerUser(fromUserId,toUserId);
        return followerRepository.getUser(followerUser);
    }
}
