import { Button, Form, Input, Grid, TextArea } from "semantic-ui-react";
import { useState } from "react";
import Navbar from "../../src/component/Navbar";
import styles from "../../styles/CreateFeed.module.css";
import http from "../../src/lib/customAxios";

export default function Createfeed() {
  const [feed, setFeed] = useState({
    userId: "", // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
  });

  const handleOnChange = (e) => {
    console.log(e.target.name);
    setFeed({
      ...feed,
      [e.target.name]: e.target.value,
    });
  };

  const uploadFile = (e) => {
    e.stopPropagation(); // 이벤트 전파 방지
    const filesArr = Array.from(e.target.files);
    setFeed({ filePath: filesArr });
    console.log(feed.filePath); // ##### 디버그 #####
  };

  const handleSubmit = (event: any) => {
    event.preventDefault(); // 새로고침 방지

    const body = new FormData();
    body.append("content", feed.content);

    feed.filePath.map((each) => {
      body.append("files", each);
    });
    // feed.hashtags.map((each) => {
    //   body.append("hashtags", each);
    // });
    body.append("userId", "admin"); // ###### 개발용 ######

    console.log(feed.content); // ##### 디버그 #####

    // http
    //   .post(`/feed`, body, {
    //     headers: { "Content-Type": "multipart/form-data" },
    //   })
    //   .then((data) => {
    //     alert("성공");
    //   })
    //   .catch(() => {
    //     alert("실패");
    //   });
  };

  return (
    <>
      <Navbar />

      <Grid>
        <Grid.Row>
          <Grid.Column width={3}></Grid.Column>
          <Grid.Column width={10}>
            <Form className={styles.form}>
              <Form.Field>
                <h3>피드 내용</h3>
                <TextArea
                  name="content"
                  rows={10}
                  placeholder="내용을 작성하세요..."
                  onChange={handleOnChange}
                />
              </Form.Field>
              <Form.Field>
                <div>
                  <h3>이미지 선택</h3>
                  <input
                    type="file"
                    accept="image/*, video/*"
                    multiple
                    onChange={uploadFile}
                  />
                </div>
              </Form.Field>
              {/* <Form.Field
                control={Input}
                label="Tag"
                placeholder="원하는 태그를 작성하세요..."
                name="hashtags"
                onChange={handleOnChange}
              /> */}
              <Form.Field></Form.Field>
              <Form.Field control={Button} onClick={handleSubmit}>
                저장하기
              </Form.Field>
            </Form>
          </Grid.Column>
          <Grid.Column width={3}></Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
}
