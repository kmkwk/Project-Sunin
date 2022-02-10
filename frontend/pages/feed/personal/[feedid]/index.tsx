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

export default function Detail() {
  const router = useRouter();
  const { feedid } = router.query;

  const [feed, setFeed] = useState({
    feedId: "", // 피드 ID
    userId: "", // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
    likes: "", // 좋아요 수
    likeUser: [], // 좋아요 누른 사람
    createdDate: "", // 작성일
    modifiedDate: "", // 수정일
  });

  useEffect(() => {
    allAxios
      .get(`/feed/detail/${feedid}`)
      .then(({ data }) => {
        setFeed(data);
        console.log(data); // ##### 디버그 #####
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
              <Modal.Header>{feed.userId}</Modal.Header>
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
              <Header as="h2">{feed.userId}</Header>
              <Icon name="user" />4 flowers &nbsp;&nbsp;&nbsp;
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

        <Grid.Row>
          <Grid.Column width={3}></Grid.Column>
          <Grid.Column width={10}>
            <h2>댓글 작성하는 곳</h2>
            <input type="text" placeholder="댓글 쓰는 곳" size={50} />
            <button>작성</button>
          </Grid.Column>
          <Grid.Column width={3}>
            <Button onClick={backToList}>뒤로가기</Button>
            <Button onClick={changeContent}>글 수정하기</Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <style jsx>{`
        .content {
          white-space: normal;
          word-break: break-all;
        }
      `}</style>
    </>
  );
}
