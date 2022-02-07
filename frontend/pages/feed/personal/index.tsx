import Navbar from "../../../src/component/Navbar";
import Menubar from "../../../src/component/Menubar";
import { Grid } from "semantic-ui-react";
import axios from "axios";
import { useEffect, useState } from "react";
import FeedList from "../../../src/component/FeedList";
import InfiniteScroll from "react-infinite-scroll-component";

export default function Personal() {
  const [list, setList]: any = useState([]);
  const [pages, setPage] = useState(0);

  useEffect(() => {
    loadFeed();
  }, []);

  function loadFeed() {
    axios
      .get(`http://localhost:8080/feed/latest`, {
        params: {
          page: pages,
        },
      })
      .then(({ data }) => {
        setList([...list, ...data]);
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
