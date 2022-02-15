import { Container, Grid } from "semantic-ui-react";
import { useEffect, useState } from "react";

import InfiniteScroll from "react-infinite-scroll-component";

import FeedList from "src/component/feed/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";
import userAxios from "src/lib/userAxios";

function Personal() {
  const [list, setList]: any = useState([]);
  const [id, setId] = useState(0);
  const [page, setPage] = useState(0);

  useEffect(() => {
    userAxios.get("/api/v1/users").then(({ data }) => {
      setId(data.body.user.user_seq);
      console.log(id);
    });
    loadList();
  }, []);

  const loadList = () => {
    allAxios
      .get(`/feed/followerLike/${id}`, {
        params: {
          size: 4,
          page: page,
        },
      })
      .then(({ data }) => {
        setList([...list, ...data]);
      });
    setPage(page + 1);
  };

  return (
    <>
      <Navbar />
      <Container>
        <Grid padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <InfiniteScroll
              style={{ overflow: "hidden" }}
              dataLength={list.length}
              next={loadList}
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

export default Personal;
