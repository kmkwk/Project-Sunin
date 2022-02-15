import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import {
  Icon,
  Grid,
  Container,
  Header,
  Button,
  Label,
} from "semantic-ui-react";

import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import allAxios from "src/lib/allAxios";
import userAxios from "src/lib/userAxios";
import User from "src/class/User";
import FeedDetail from "src/class/FeedDetail";
import Comments from "src/component/comment/Comments";
import SwiperMedia from "src/component/Swiper";
import styles from "styles/feed.module.css";

function Detail({ feedid }: any) {
  const router = useRouter();

  // 로그인 유저 정보
  const [user, setUser]: any = useState(new User());

  // 피드 정보
  const [feed, setFeed]: any = useState({
    feedId: "", // 피드ID
    userInfo: {}, // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
    likes: 0, // 좋아요 수
    likeUser: [], // 좋아요 누른사람
    comments: {},
    createdDate: "", // 작성일
    modifiedDate: "", // 수정일
  });

  useEffect(() => {
    console.log(feedid);
    userAxios
      .get("/api/v1/users")
      .then(({ data }) => {
        setUser(data.body.user);
      })
      .catch(() => {});

    allAxios
      .get(`/feed/detail/${feedid}`)
      .then(({ data }) => {
        setFeed(new FeedDetail(data));
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/feed");
      });
  }, []);

  const deleteFeed = () => {
    allAxios
      .delete(`/feed/${feedid}/${user.user_seq}`)
      .then(() => {
        router.push("/feed");
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  const showNickname = (object: any) => {
    return Object.entries(object).map(
      (obj: any) => obj[0] == "nickName" && <Header as="h2">{obj[1]}</Header>
    );
  };

  const likeFeed = () => {
    const body = new FormData();
    body.append("id", feed.feedId);
    body.append("userId", user.userSeq);

    userAxios
      .put(`/feed/addLike`, body)
      .then(() => {
        router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  return (
    <>
      <Navbar />
      
      <Grid padded className={styles.con}>
        <Grid.Row>
          <Grid.Column width={3}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={9}>
            <div className={styles.height}>
            <SwiperMedia media={feed.filePath} />
            </div>
          </Grid.Column>
          <Grid.Column width={3}>
            <Container>
              <Header as="h2">{showNickname(feed.userInfo)}</Header>
              <Label onClick={likeFeed}>
                <Icon name="like" />
                <span>Like {feed.likes == 0 ? 0 : feed.likes}</span>
              </Label>
              <Label>
                <Icon name="sign-in" />
                <span>Follower</span>
              </Label>
              <Label>
                <Icon name="sign-out" />
                <span>Following</span>
              </Label>
            </Container>
            <br />
            <Container>
              <p className="content">{feed.content}</p>
            </Container>
            <br /><br />
            <Container>
            <Header as="h1">
              <Icon name="tags" />
              <Header.Content>Tags</Header.Content>
            </Header>
              {/* {feed.hashtags} */}
              {/* {feed.hashtags.map((item: any, index: any) => {
                <p>{item}123</p>
              })} */}
              {feed.hashtags.map((data: any, key: any) => {
                return (
                  <div key={key}>
                    <Label>{data}</Label>
                  </div>
                );
              })}
              
            </Container>
            <Container>
              <br />
            <Header as="h1">
              <Icon name="comments" />
              <Header.Content>Comments</Header.Content>
            </Header>
            <Comments
              list={feed.comments}
              userSeq={user.user_seq}
              feedId={feed.feedId}
              onChange={feed.comments}
            />
            </Container>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={12}>
          </Grid.Column>
          <Grid.Column width={3}>
            <Button onClick={() => router.push("/feed")} color="grey">뒤로</Button>
            {feed.userInfo.user_id == user.user_seq && (
              <>
                <Button onClick={() => router.push(`/feed/edit/${feedid}`)} color="grey">
                  수정
                </Button>
                <Button onClick={deleteFeed} color="black">삭제</Button>
              </>
            )}
          </Grid.Column>
        </Grid.Row>

        {/* <Grid.Row>
          <Grid.Column width={3} />
          <Grid.Column width={10}>
            <Header as="h1">
              <Icon name="comments" />
              <Header.Content>Comments</Header.Content>
            </Header>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={3} />
          <Grid.Column width={12}>
            <Comments
              list={feed.comments}
              userSeq={user.user_seq}
              feedId={feed.feedId}
              onChange={feed.comments}
            />
          </Grid.Column>
        </Grid.Row> */}
      </Grid>
    </>
  );
}

export async function getServerSideProps(context: any) {
  const feedid = context.params.feedid;
  return { props: { feedid } };
}

export default Detail;
