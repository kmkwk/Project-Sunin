import Navbar from "../../src/component/Navbar";
import Link from "next/link";
import { Button, Form, Grid, Segment, Image, Header } from "semantic-ui-react";
import { useState } from "react";
import styles from "../../styles/Login.module.css";
import axios from "axios";

export default function NaverLogin() {
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
      .post("http://localhost:8080/login/oauth2/code/naver", {
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
                  label="Naver Eamil"
                  placeholder="Naver Email"
                  onChange={updateEmail}
                />
                <Form.Input
                  icon="lock"
                  iconPosition="left"
                  label="Naver Password"
                  type="password"
                  onChange={updatePassword}
                />
                {/* <Button content="Login" onClick={GoLogin} color='green' /> */}
                <Image src="/images/btnG_축약형.png" size="small" onClick={GoLogin} className={ styles.social_login_button }/>
                <br />
              </Form>            
            </Grid.Column>
          </Grid>
        </Segment>
      </div>
    </>
  );
}
