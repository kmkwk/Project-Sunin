import Navbar from "../../../src/component/Navbar";
import Menubar from "../../../src/component/Menubar";
import { Grid } from "semantic-ui-react";
import axios from "axios";
import { useEffect, useState } from "react";
import FeedList from "../../../src/component/FeedList";

export default function personal() {
  const [list, setList] = useState([]);

  const API_URL =
    "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  function getData() {
    axios.get(API_URL).then((res) => {
      setList(res.data);
    });
  }

  useEffect(() => {
    getData();
  }, []);

  return (
    <>
      <Navbar />
      <Grid columns={2} padded stackable>
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={13}>
          <h1>this page is personal</h1>
          <FeedList list={list} />
        </Grid.Column>
      </Grid>
    </>
  );
}
