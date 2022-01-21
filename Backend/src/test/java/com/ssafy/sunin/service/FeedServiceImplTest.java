//package com.ssafy.sunin.service;
//
//import com.ssafy.sunin.domain.FeedCollections;
//import com.ssafy.sunin.dto.FeedDto;
//import com.ssafy.sunin.service.FeedServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class FeedServiceImplTest {
//
//    @Autowired
//    private FeedServiceImpl feedService;
//
//    @Test
//    void writeFeed() {
//        List<String> list = new ArrayList<>();
//        list.add("1번");
//        list.add("2번");
//        list.add("3번");
//        FeedCollections feedCollections = FeedCollections.builder()
////                .id("sdfs")
//                .userId("asdf")
//                .likes(1)
//                .hashtags(list)
//                .content("안녕하세요2")
//                .build();
////        FeedCollections feedCollections1 = FeedCollections.builder()
////                .fileName("ima.png")
////                .fileSize(10001)
////                .filePath("apㅁ")
////                .build();
////        feedService.writeFeed(feedCollections);
////        feedService.writeFeed(feedCollections);
////        Optional<String> op = Optional.ofNullable(feedService.writeFeed(feedCollections));
////        System.out.println(op);
////        feedService.getFeed(op.get());
//    }
//
//    @Test
//    void listFeed() {
//        List<FeedDto> feedCollections = feedService.getListFeed();
//        System.out.println(feedCollections);
//    }
//
//    @Test
//    void getFeed() {
//        System.out.println(feedService.getFeed("fdfsdf"));
//    }
//
//    @Test
//    void updateFeed() {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        FeedCollections feedCollections = FeedCollections.builder()
//                .userId("asdf")
//                .likes(0)
//                .hashtags(list)
//                .content("안녕하세요32322")
//                .build();
//        feedService.updateFeed(feedCollections);
//    }
//
//    @Test
//    void deleteFeed() {
//    }
//}