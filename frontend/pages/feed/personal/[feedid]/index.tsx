import axios from "axios";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import {
  Icon,
  Grid,
  Image,
  Container,
  Header,
  Modal,
  Button,
} from "semantic-ui-react";
import Navbar from "../../../../src/component/Navbar";
import styles from "../../../../styles/FeedPersonalDetail.module.css";
import Menubar from "../../../../src/component/Menubar";
import React from "react";

export default function Detail() {
  const router = useRouter();
  const { feedid } = router.query;

  const [feed, setFeed]: any = useState({});

  // const API_URL = `http://makeup-api.herokuapp.com/api/v1/products/${ feedid }.json`;

  function getData() {
    axios
      .get(`http://localhost:8080/feed/detail`, {
        params: {
          id: feedid,
        },
      })
      .then(({ data }) => {
        // setFeed(res.data);
        console.log(data);
        setFeed(data);
      });
  }

  useEffect(() => {
    if (feedid) {
      console.log(feedid);
      getData();
    }
  }, [feedid]);

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
                <Image src={feed.filePath} alt={feed.filePath} width="200px" />
              }>
              <Modal.Header>{feed.userId}</Modal.Header>
              <Modal.Content image>
                <Image size="big" src={feed.image_link} />
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
          /* overflow: scroll; */
        }
      `}</style>
    </>
  );
}
