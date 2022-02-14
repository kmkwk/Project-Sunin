package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.FeedCollections;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class FeedSearch {
    private List<FeedCollections> feedCollectionsList;
    private List<String> contentList;

    public static FeedSearch feedSearch(List<FeedCollections> feedCollections, List<String> contentList){
        return FeedSearch.builder()
                .feedCollectionsList(feedCollections)
                .contentList(contentList)
                .build();
    }

    @Builder
    public FeedSearch(List<FeedCollections> feedCollectionsList, List<String> contentList) {
        this.feedCollectionsList = feedCollectionsList;
        this.contentList = contentList;
    }
}
