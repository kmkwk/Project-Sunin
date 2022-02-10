import { Grid } from "semantic-ui-react";
import { useEffect, useState } from "react";

import InfiniteScroll from "react-infinite-scroll-component";

import FeedList from "src/component/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";

export default function Personal() {
  const [list, setList]: any = useState([]);
  const [pages, setPage] = useState(0);

  useEffect(() => {
    loadFeed();
  }, []);

  function loadFeed() {
    allAxios
      .get(`/feed/latest`, {
        params: {
          size: 9,
          page: pages,
          userId: 1,
        },
      })
      .then(({ data }) => {
        console.log(data.content);
        setList([...list, ...data.content]);
      });
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
          <h1>this page is personal</h1>
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
