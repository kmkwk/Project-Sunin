import axios from "axios";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { Card, CardContent, Icon, Grid, Image, Container, Header, Modal, Button } from 'semantic-ui-react'
import Navbar from "../../../src/component/Navbar";
import Menubar from "../../../src/component/Menubar"
import React from 'react'
import Link from "next/link";

export default function Detail() {

  const router = useRouter();
  const { feedid } = router.query

  const [feed, setFeed] = useState({});

  const API_URL = `http://makeup-api.herokuapp.com/api/v1/products/${ feedid }.json`;

  function getData() {
    axios.get(API_URL).then((res) => {
      console.log(res.data)
      setFeed(res.data)
    })
  }

  useEffect(() => {
    if (feedid) {
      getData()
    }
  }, [feedid]);

  const [open, setOpen] = React.useState(false)

  function backToList() {
    return router.push('/feed/company')
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
              trigger={<img src={ feed.image_link } alt="이미지" width="200px" />}
            >
              <Modal.Header>User Name</Modal.Header>
              <Modal.Content image>
                <Image size='big' src={ feed.image_link } fluid />
                <Modal.Description>
                  <Header></Header>
                  <p>#tag</p>
                </Modal.Description>
              </Modal.Content>
              <Modal.Actions>
                <Button color='black' onClick={() => setOpen(false)}>
                  닫기
                </Button>
                {/* <Button
                  content="Yep, that's me"
                  labelPosition='right'
                  icon='checkmark'
                  onClick={() => setOpen(false)}
                  positive
                /> */}
              </Modal.Actions>
            </Modal>
            {/* <img src={ feed.image_link } alt="이미지" width="200px" /> */}
          </Grid.Column>
          <Grid.Column width={5}>
            <Container>
              <Header as='h2'>User Name</Header>
              <Icon name='user' />4 flowers &nbsp;&nbsp;&nbsp;
              <Icon name='like' />4 like
            </Container>
            <br />
            <Container>
              <p>{feed.description}</p>
            </Container>
            <br />
            <Container>#tag</Container>
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={3}>
            
          </Grid.Column>
          <Grid.Column width={10}>
            <h2>댓글 작성하는 곳</h2>
            <input type="text" placeholder="댓글 쓰는 곳" size={50} />
            <button>작성</button>
          </Grid.Column>
          <Grid.Column width={3}>
            <Button onClick={backToList}>뒤로가기</Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      
      
      
    </>
  );
}