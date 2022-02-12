import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import {
  Icon,
  Grid,
  Image,
  Container,
  Header,
  Modal,
  Button,
} from "semantic-ui-react";

import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import allAxios from "src/lib/allAxios";
import userAxios from "src/lib/userAxios";
import User from "src/class/User";
import FeedDetail from "src/class/FeedDetail";
import Comment from "src/component/comment/Comment";

function Detail({ feedid }: any) {
  const router = useRouter();

  // 로그인 유저 정보
  const [user, setUser]: any = useState({
    userSeq: 0, // 기본키
    userId: "", // 아이디
    username: "", // 이름
    email: "", // 이메일
    follower: [], // 팔로워
    userNickname: "", // 닉네임
    profileImageUrl: "", // 프로필사진
    providerType: "", // 제공자(GOOGLE, KAKAO, NAVER)
    roleType: "", // ENUM("ADMIN", "USER")
    suninDays: 0, // 적립된 선인
  });

  // 피드 정보
  const [feed, setFeed] = useState({
    feedId: "", // 피드ID
    userInfo: {}, // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
    likes: "", // 좋아요 수
    likeUser: [], // 좋아요 누른사람
    comments: {},
    createdDate: "", // 작성일
    modifiedDate: "", // 수정일
  });

  useEffect(() => {
    userAxios
      .get(`/api/v1/users`)
      .then(({ data }) => {
        setUser(new User(data.body.user));
      })
      .catch(() => {});

    allAxios
      .get(`/feed/detail/${feedid}`)
      .then(({ data }) => {
        setFeed(new FeedDetail(data));
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/feed/personal");
      });
  }, []);

  const [open, setOpen] = React.useState(false);

  function backToList() {
    return router.push("/feed/personal");
  }
  function changeContent() {
    return router.push(`/feed/personal/${feedid}/edit`);
  }

  const showNickname = (object: any) => {
    return Object.entries(object).map(
      (obj: any) => obj[0] == "nickName" && <Header as="h2">{obj[1]}</Header>
    );
  };

  return (
    <>
      <Navbar />
      <Grid padded>
        <Grid.Row>
          <Grid.Column width={3}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={3}>
            <Modal
              onClose={() => setOpen(false)}
              onOpen={() => setOpen(true)}
              open={open}
              trigger={
                <Image
                  src={feed.filePath[0]}
                  alt={feed.filePath[0]}
                  width="200px"
                />
              }>
              <Modal.Header>{showNickname(feed.userInfo)}</Modal.Header>
              <Modal.Content image>
                <Image
                  size="big"
                  src={feed.filePath[0]}
                  alt={feed.filePath[0]}
                />
                <Modal.Description>
                  <Header></Header>
                  <p>#tag</p>
                </Modal.Description>
              </Modal.Content>
              <Modal.Actions>
                <Button color="black" onClick={() => setOpen(false)}>
                  닫기
                </Button>
              </Modal.Actions>
            </Modal>
          </Grid.Column>
          <Grid.Column width={9}>
            <Container>
              <Header as="h2">{showNickname(feed.userInfo)}</Header>
              <Icon name="user" />4 followers
              <Icon name="like" />
              {feed.likes} like
            </Container>
            <br />
            <Container>
              <p className="content">{feed.content}</p>
            </Container>
            <br />
            <Container>
              #tag <br />
              <p className="content">{feed.hashtags}</p>
            </Container>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row textAlign="right">
          <Grid.Column>
            <Button onClick={backToList}>뒤로가기</Button>
            <Button onClick={changeContent}>수정</Button>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
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
            <Comment
              list={feed.comments}
              userSeq={user.userSeq}
              feedId={feed.feedId}
              onChange={feed.comments}
            />
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
}

export async function getServerSideProps(context: any) {
  const feedid = context.params.feedid;
  return { props: { feedid } };
}

export default Detail;
