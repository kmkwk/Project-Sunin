import React from "react";
import { Grid, Divider, Icon, Item, Label } from "semantic-ui-react";
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
    image: "",
    nickName: "",
  });

  const [follower, setFollower] = useState(-1);
  const [following, setFollowing] = useState(-1);

  // 프로필 유저 피드
  const [list, setList]: any = useState([]);

  const [pages, setPage] = useState(0); // 넌 보류

  useEffect(() => {
    allAxios
      .get(`/api/v1/users/profile/${id}`)
      .then(({ data }) => {
        setUser({
          ...user,
          image: data.image,
          nickName: data.nickName,
        });
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
        router.push("/");
      });

    allAxios.get(`/follower/follower/${id}`).then(({ data }) => {
      setFollower(data);
    });

    allAxios.get(`/follower/following/${id}`).then(({ data }) => {
      setFollowing(data);
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
                    <span className="cinema">### 썬인포인트 조회 불가 ###</span>
                  </Item.Header>
                  <Item.Description>
                    ### 소개문구 조회 불가 ###
                  </Item.Description>
                  <Item.Extra>
                    <Label>
                      <Icon name="write" />
                      <span>Feed {list.length}</span>
                    </Label>
                    <Label>
                      <Icon name="sign-in" />
                      <span>Follower {follower}</span>
                    </Label>
                    <Label>
                      <Icon name="sign-out" />
                      <span>Following {following}</span>
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
    </>
  );
}
export async function getServerSideProps(context: any) {
  const id = context.params.id;
  return { props: { id } };
}
export default Profiles;
