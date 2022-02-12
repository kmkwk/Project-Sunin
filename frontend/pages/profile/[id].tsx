import React from "react";
import { Grid, Divider, Icon, Image, Item } from "semantic-ui-react";
import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import { useEffect, useState } from "react";
import styles from "styles/signup.module.css";
import ProfileList from "src/component/ProfileList";

export default function Profileperson() {
  const [list, setList] = useState([]);

  // router.query 불러와서
  // 해당 프로필의 주인을 불러옵니다.
  // router.query엔 userSeq 들어가야함

  // 유저정보랑 그 유저가 작성한 피드 로드해오기

  const API_URL =
    "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  function getData() {
    // 피드 불러오기
  }
  const [nickname, setNickname] = useState("닉네임");
  const [introduce, setIntroduce] = useState("introduce");

  useEffect(() => {
    getData();
  }, []);

  return (
    <>
      <Navbar />
      <Grid columns={2} padded stackable>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
          <div className={styles.headeralign}>
            <Item.Group divided>
              <Item>
                <Item.Image src="https://react.semantic-ui.com/images/wireframe/image.png" />

                <Item.Content>
                  <Item.Header as="a">{nickname}&nbsp;</Item.Header>
                  <Icon name="lemon outline" />
                  <span className="cinema">9day</span>
                  <Item.Description>
                    {/* {paragraph} */}
                    {introduce}
                  </Item.Description>
                  <Item.Extra>
                    <h1>어디?</h1>
                  </Item.Extra>
                  <span className="cinema">게시물수 9&nbsp;&nbsp;&nbsp;</span>
                  <Icon name="user" /> followers 127&nbsp;&nbsp;
                  <Icon name="user" /> following 219
                </Item.Content>
              </Item>
            </Item.Group>
          </div>
          <Divider />
          <ProfileList list={list} />
        </Grid.Column>
        <Grid.Column width={2} />
      </Grid>
    </>
  );
}
