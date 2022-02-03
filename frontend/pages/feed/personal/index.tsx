import Navbar from "../../../src/component/Navbar";
import Menubar from "../../../src/component/Menubar";
import { Grid } from "semantic-ui-react";
import axios from "axios";
import { useEffect, useState } from "react";
import FeedList from "../../../src/component/FeedList";

export default function personal() {
  const [list, setList] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/feed/pageLatest`, {
        params: {
          page: 0,
        },
      })
      .then(({ data }) => {
        setList(data);
      });
  }, []);

  return (
    <>
      <Navbar />
      <Grid columns={2} padded stackable>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
          <h1>this page is personal</h1>
          <FeedList list={list} />
        </Grid.Column>
        <Grid.Column width={2} />
      </Grid>
    </>
  );
}
