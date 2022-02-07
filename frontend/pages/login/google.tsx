import Navbar from "../../src/component/Navbar";
import Link from "next/link";
import { Button, Form, Grid, Segment, Image, Header } from "semantic-ui-react";
import { useState } from "react";
import styles from "../../styles/Login.module.css";
import axios from "axios";

export default function GoogleLogin() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const updateEmail = (event: any) => {
    setEmail(event.target.value);
  };

  const updatePassword = (event: any) => {
    setPassword(event.target.value);
  };

  function GoLogin() {
    axios
      .post("http://localhost:8080/login/oauth2/code/google", {
        userId: email,
        user_password: password,
      })
      .then(({ data }) => {
        alert("로그인 성공");
        console.log(data);
      })
      .catch(() => {
        alert("로그인 실패");
      });
  }
  return (
    <>
      <Navbar />
      <div>
        <Segment placeholder>
          <br />
          <br />
          <br />
          <Grid columns={2} relaxed="very" stackable>
            <Grid.Column verticalAlign="middle">
              <Image src="/images/로고.png" size="huge" />
            </Grid.Column>

            <Grid.Column className={styles.location}>
              <Form>
                <Form.Input
                  icon="user"
                  iconPosition="left"
                  label="Google Eamil"
                  placeholder="Google Email"
                  onChange={updateEmail}
                />
                <Form.Input
                  icon="lock"
                  iconPosition="left"
                  label="Google Password"
                  type="password"
                  onChange={updatePassword}
                />
                <Button content="Login" onClick={GoLogin} color='google plus' />
                <br />
              </Form>            
            </Grid.Column>
          </Grid>
        </Segment>
      </div>
    </>
  );
}
