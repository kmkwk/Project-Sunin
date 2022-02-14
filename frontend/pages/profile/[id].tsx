import React from "react";
import { Grid, Divider, Icon, Item, Label, Container } from "semantic-ui-react";
import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import { useEffect, useState } from "react";
import styles from "styles/signup.module.css";
import allAxios from "src/lib/allAxios";
import { useRouter } from "next/router";
import InfiniteScroll from "react-infinite-scroll-component";
import FeedList from "src/component/feed/FeedList";

function Profiles({ id }: any) {
  const router = useRouter();

  // 프로필 유저
  const [user, setUser] = useState({
    intro: "",
    feedCount: 0,
    follower: 0,
    following: 0,
    image: "",
    nickName: "",
    sunin: 0,
  });

  // 프로필 유저 피드
  const [list, setList]: any = useState([]);

  const [pages, setPage] = useState(0); // 넌 보류

  useEffect(() => {
    allAxios
      .get(`/api/v1/users/profile/${id}`)
      .then(({ data }) => {
        console.log(data);
        setUser({
          intro: data.introduction,
          feedCount: data.feed_count,
          follower: data.follwer_count,
          following: data.follwing_count,
          image: data.image,
          nickName: data.nick_name,
          sunin: data.sunin_days,
        });
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
        router.push("/");
      });

    allAxios.get(`/feed/person/${id}`).then(({ data }) => {
      setList(data);
    });
  }, []);

  function loadFeed() {
    setPage(pages + 1);
  }

  return (
    <>
      <Navbar />
      <Container className={styles.con}>
        <Grid columns={2} padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <div className={styles.headeralign}>
              <Item.Group divided>
                <Item>
                  <Item.Image src={user.image} />

                  <Item.Content>
                    <Item.Header>
                      <span>{user.nickName}</span>
                      <span> | </span>
                      <Icon name="lemon outline" />
                      <span className="cinema">{user.sunin}</span>
                    </Item.Header>
                    <Item.Description>
                      <span>{user.intro} ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</span>
                    </Item.Description>
                    <Item.Extra>
                      <Label>
                        <Icon name="write" />
                        <span>Feed {user.feedCount}</span>
                      </Label>
                      <Label>
                        <Icon name="sign-in" />
                        <span>Follower {user.follower}</span>
                      </Label>
                      <Label>
                        <Icon name="sign-out" />
                        <span>Following {user.following}</span>
                      </Label>
                    </Item.Extra>
                  </Item.Content>
                </Item>
              </Item.Group>
            </div>
            <Divider />
            <InfiniteScroll
              style={{ overflow: "hidden" }}
              dataLength={list.length}
              next={loadFeed}
              hasMore={true}
              loader={undefined}>
              <FeedList list={list} />
            </InfiniteScroll>
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}
export async function getServerSideProps(context: any) {
  const id = context.params.id;
  return { props: { id } };
}
export default Profiles;
