import React, { useEffect, useState } from "react";
import {
  Grid,
  Header,
  Divider,
  Form,
  Button,
  Image,
  Input,
} from "semantic-ui-react";
import styles from "styles/signup.module.css";
import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import userAxios from "src/lib/userAxios";
import { useRouter } from "next/router";
import User from "src/class/User";
import allAxios from "src/lib/allAxios";

function EditProfile() {
  const router = useRouter();
  const [user, setUser] = useState(new User());
  const [attachment, setAttachment] = useState();
  const [filePath, setFilePath]: any = useState();

  useEffect(() => {
    userAxios
      .get("/api/v1/users")
      .then(({ data }) => {
        setUser(data.body.user);
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/");
      });
  }, []);

  const handleOnChange = (e: any) => {
    const { value, name } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const uploadToServer = async (event: any) => {
    const body = new FormData();
    body.append("address", user.address);
    body.append("image", filePath);
    body.append("introduction", user.introduction);
    body.append("nickName", user.userNickname);
    body.append("phoneNumber", user.phoneNumber);
    body.append("userId", JSON.stringify(user.userSeq));

    allAxios
      .put("/api/v1/users", body, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then(({ data }) => {
        console.log("성공");
      })
      .then(() => {
        console.log("실패");
      });
  };

  const onFileChange = (event: any) => {
    event.stopPropagation(); // 이벤트 전파 방지
    console.log(event.target.files);
    setFilePath(event.target.files);

    const {
      target: { files },
    } = event;
    const theFile = files[0];
    const reader = new FileReader();
    reader.onloadend = (finishedEvent: any) => {
      setAttachment(finishedEvent.currentTarget["result"]);
    };
    if (theFile) {
      reader.readAsDataURL(theFile);
    } else {
      setAttachment(undefined);
    }
  };

  const onClearAttachment = () => {
    const erase: any = document.getElementsByName("profileImage")[0];
    erase["value"] = null;
    setAttachment(undefined);
  };

  return (
    <>
      <Navbar />

      <Grid columns={2} padded stackable>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={12}>
          <div className={styles.headeralign}>
            <Header size="huge">회원정보 수정</Header>
            <Divider />
          </div>
          <div className={styles.formalign}>
            <Form>
              <Form.Field>
                <div>
                  <h4>프로필 사진</h4>
                  <Image src={user.profileImageUrl} width="100px" />
                  <Input
                    type="file"
                    accept="image/*"
                    name="profileImage"
                    onChange={onFileChange}
                  />
                  {
                    <div>
                      <br />
                      <Button
                        basic
                        color="grey"
                        onClick={onClearAttachment}
                        fluid>
                        Cancel
                      </Button>
                    </div>
                  }
                </div>
              </Form.Field>
              <Form.Field>
                <label>이메일</label>
                <Input
                  disabled
                  name="email"
                  placeholder="Email"
                  value={user.email}
                  onChange={handleOnChange}
                />
              </Form.Field>

              <Form.Field>
                <label>닉네임</label>
                <Form.Group inline>
                  <Input
                    name="user_nickname"
                    placeholder="Nickname"
                    value={user.userNickname}
                    onChange={handleOnChange}
                  />
                </Form.Group>
              </Form.Field>
              <Form.Field>
                <label>전화번호</label>
                <Form.Group inline>
                  <Input
                    name="phone_number"
                    placeholder="Tel"
                    value={user.phoneNumber}
                    onChange={handleOnChange}
                  />
                </Form.Group>
              </Form.Field>
              <Form.Field>
                <label>주소</label>
                <Form.Group inline>
                  <Input
                    name="address"
                    placeholder="Address"
                    value={user.address}
                    onChange={handleOnChange}
                  />
                </Form.Group>
              </Form.Field>
            </Form>
            <br />
            <Button basic color="grey" onClick={uploadToServer} fluid>
              저장하기
            </Button>
          </div>
          <br />
        </Grid.Column>
      </Grid>
    </>
  );
}

export default EditProfile;