import React, { useState } from "react";
import Link from "next/link";
import { Image, Form, Grid, Segment, Button } from "semantic-ui-react";
import "styles/signup.module.css";
import Navbar from "src/component/Navbar";
import axios from "axios";

const Signup = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [name, setName] = useState("");
  const [nickname, setNickname] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [address, setAddress] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const onSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    if (password !== passwordCheck) {
      return setPasswordError(true);
    }
    console.log({
      email,
      password,
      passwordCheck,
      name,
      nickname,
      phonenumber,
      address,
    });

    /*
     * 성공하면 아래 axios로 가입
     **/
    axios
      .post("http://localhost:8080/user/signup", {
        userId: email,
        user_name: name,
        user_nickname: nickname,
        user_password: password,
        user_tel: phonenumber,
        user_address: address,
      })
      .then(({ data }) => {
        alert("회원가입 성공");
        console.log(data);
      })
      .catch(() => {
        alert("회원가입 실패");
        console.warn("실패");
      });
  };

  const onChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const onChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };
  const onChangePasswordcheck = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setPasswordError(event.target.value !== password);
    setPasswordCheck(event.target.value);
  };
  const onChangeName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };
  const onChangeNickname = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNickname(event.target.value);
  };
  const onChangePhonenumber = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPhonenumber(event.target.value);
  };
  const onChangeAddress = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAddress(event.target.value);
  };

  return (
    <>
      <Navbar />
      <Segment placeholder>
        <Grid columns={2} relaxed="very" stackable>
          <Grid.Column>
            <Image src="../images/로고.png" size="huge" />
          </Grid.Column>

          <Grid.Column>
            <Form onSubmit={onSubmit}>
              <Form.Field required>
                <label>이메일</label>
                <Form.Input
                  placeholder="Email"
                  name="email"
                  value={email}
                  onChange={onChangeEmail}
                />
              </Form.Field>
              <Form.Field required>
                <label>비밀번호</label>
                <input
                  placeholder="Password"
                  name="password"
                  value={password}
                  type="password"
                  onChange={onChangePassword}
                />
              </Form.Field>
              <Form.Field required>
                <label>비밀번호 확인</label>
                <input
                  placeholder="Password"
                  name="passwordcheck"
                  value={passwordCheck}
                  type="password"
                  onChange={onChangePasswordcheck}
                />
                {passwordError && (
                  <div style={{ color: "red" }}>
                    비밀번호가 일치하지 않습니다.
                  </div>
                )}
              </Form.Field>
              <Form.Field required>
                <label>이름</label>
                <input
                  placeholder="Name"
                  name="name"
                  value={name}
                  onChange={onChangeName}
                />
              </Form.Field>
              <Form.Field required>
                <label>닉네임</label>
                <input
                  placeholder="Nickname"
                  name="nickname"
                  value={nickname}
                  onChange={onChangeNickname}
                />
              </Form.Field>
              <Form.Field>
                <label>전화번호</label>
                <input
                  placeholder="Tel. ex) 01012345678"
                  name="phonenumber"
                  value={phonenumber}
                  onChange={onChangePhonenumber}
                />
              </Form.Field>
              <Form.Field>
                <label>주소</label>
                <input
                  placeholder="Address"
                  name="address"
                  value={address}
                  onChange={onChangeAddress}
                />
              </Form.Field>
              <Button.Group widths="2">
                <Button type="submit">REGISTER</Button>
                <Link href="/signup">
                  <Button type="submit">Back</Button>
                </Link>
              </Button.Group>
            </Form>
          </Grid.Column>
        </Grid>
      </Segment>
    </>
  );
};

export default Signup;
