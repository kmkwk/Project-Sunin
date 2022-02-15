import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import {
  Icon,
  Grid,
  Container,
  Header,
  Button,
  Label,
  Image,
  List
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
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Link from "next/link";

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

  // const showNickname = (object: any) => {
  //   return Object.entries(object).map(
  //     (obj: any) => obj[0] == "nickName" && <span>{obj[1]}</span>
  //   );
  // };

  const likeFeed = () => {
    console.log(feed.feedId);
    console.log(user.user_seq);
    console.log(user.userSeq);

    const body = new FormData();
    body.append("id", feed.feedId);
    body.append("userId", user.user_seq);
    // 보내는 사람
    const fromUserId = localStorage.getItem("userId");
    const messages = fromUserId+"가 게시글에 좋아요를 눌렀습니다!"
    const socket = new SockJS('http://i6c210.p.ssafy.io:8080/stomp');
    const stompClient = Stomp.over(socket);
    
    
    userAxios
      .put(`/feed/addLike`, body)
      .then(() => {
        stompClient.send(`/send/`+fromUserId+`/`+feed.userInfo.user_id+`/`+messages);
        router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  function goProfile() {
    router.push(`/profile/${feed.userInfo.user_id}`)
  }

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
            
            <List divided verticalAlign='middle'>
            <List.Item>
              <List.Content floated='right'>
                <Label onClick={likeFeed}>
                <Icon name="like" />
                <span style={{ cursor: "pointer", textShadow: "좋아요" }}>Like {feed.likes == 0 ? 0 : feed.likes}</span>
              </Label>
              </List.Content>
            <Image avatar onClick={goProfile} title="작성자 프로필로 이동" style={{ cursor: "pointer", textShadow: "작성자 프로필로 이동" }} src={feed.userInfo.image} />
              <List.Content onClick={goProfile} title="작성자 프로필로 이동" style={{ cursor: "pointer", textShadow: "작성자 프로필로 이동" }}>{feed.userInfo.nick_name}</List.Content>
            </List.Item>
            </List>
              {/* <Label>
                <Icon name="sign-in" />
                <span>Follower</span>
              </Label>
              <Label>
                <Icon name="sign-out" />
                <span>Following</span>
              </Label> */}
            </Container>
            <br />
            <Container>
              <p className="content">{feed.content}</p>
            </Container>
            <br />
            <br />
            <Container>
              <Header as="h2">
                <Icon name="tags" />
                <Header.Content>Tags</Header.Content>
              </Header>
              {feed.hashtags.map((data: any, key: any) => {
                return (
                  <Label key={key} color="green" size="large">
                    {data}
                  </Label>
                );
              })}
            </Container>
            <Container>
              <br />
              <Header as="h2">
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
          <Grid.Column width={12}></Grid.Column>
          <Grid.Column width={3}>
            <Button onClick={() => router.push("/feed")} color="grey">
              뒤로
            </Button>
            {feed.userInfo.user_id == user.user_seq && (
              <>
                <Button
                  onClick={() => router.push(`/feed/edit/${feedid}`)}
                  color="grey">
                  수정
                </Button>
                <Button onClick={deleteFeed} color="black">
                  삭제
                </Button>
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
