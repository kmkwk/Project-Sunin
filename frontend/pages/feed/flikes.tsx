import { Container, Grid } from "semantic-ui-react";
import { useEffect, useState } from "react";

import FeedList from "src/component/feed/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";
import userAxios from "src/lib/userAxios";

function Personal() {
  const [list, setList]: any = useState([]);

  useEffect(() => {
    userAxios.get("/api/v1/users").then(({ data }) => {
      allAxios
        .get(`/feed/followerLike/${data.body.user.user_seq}`)
        .then(({ data }) => {
          setList(data);
        });
    });
  }, []);

  return (
    <>
      <Navbar />
      <Container>
        <Grid padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <FeedList list={list} />
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}

export default Personal;
