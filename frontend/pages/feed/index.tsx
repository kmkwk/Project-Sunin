import { Container, Grid, Menu } from "semantic-ui-react";
import { useEffect, useState } from "react";

import InfiniteScroll from "react-infinite-scroll-component";

import FeedList from "src/component/feed/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";

function Personal() {
  const [latest, setLatest]: any = useState([]);
  const [like, setLike]: any = useState([]);
  const [page1, setPage1] = useState(0);
  const [page2, setPage2] = useState(0);

  const [activeItem, setActiveItem] = useState("latest");
  const handleItemClick = (e: any) => {
    const value = e.target.outerText.toLowerCase();
    setActiveItem(value);
    if (value == "like") {
      setLatest([]);
      setPage1(0);
      loadByLatest();
    }

    if (value == "latest") {
      setLike([]);
      setPage2(0);
      loadByLike();
    }
  };

  useEffect(() => {
    loadByLatest();
    loadByLike();
  }, []);

  const loadByLatest = () => {
    allAxios
      .get(`/feed/latest`, {
        params: {
          size: 4,
          page: page1,
        },
      })
      .then(({ data }) => {
        setLatest([...latest, ...data]);
      });
    setPage1(page1 + 1);
  };

  const loadByLike = () => {
    allAxios
      .get(`/feed/like`, {
        params: {
          size: 4,
          page: page2,
        },
      })
      .then(({ data }) => {
        setLike([...like, ...data]);
      });
    setPage2(page2 + 1);
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
            {/* <Menu text>
              <Menu.Item header>Sort By</Menu.Item>
              <Menu.Item
                name="Latest"
                active={activeItem === "latest"}
                onClick={handleItemClick}
              />
              <Menu.Item
                name="Like"
                active={activeItem === "like"}
                onClick={handleItemClick}
              />
            </Menu> */}
            {activeItem == "latest" ? (
              <div id="sortByLatest">
                <InfiniteScroll
                  style={{ overflow: "hidden" }}
                  dataLength={latest.length}
                  next={loadByLatest}
                  hasMore={true}
                  loader={undefined}>
                  <FeedList list={latest} />
                </InfiniteScroll>
              </div>
            ) : (
              <div id="sortByLike">
                <InfiniteScroll
                  style={{ overflow: "hidden" }}
                  dataLength={like.length}
                  next={loadByLike}
                  hasMore={true}
                  loader={undefined}>
                  <FeedList list={like} />
                </InfiniteScroll>
              </div>
            )}
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}

export default Personal;
