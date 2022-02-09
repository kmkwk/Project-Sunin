import {
  Button,
  Form,
  Input,
  Grid,
  TextArea,
  Dropdown,
  Label,
  Icon,
} from "semantic-ui-react";
import { useState } from "react";
import Navbar from "../../src/component/Navbar";
import styles from "../../styles/CreateFeed.module.css";
import http from "../../src/lib/customAxios";

export default function Createfeed() {
  const [feed, setFeed]: any = useState({
    userId: 0, // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
  });

  const handleOnChange = (e: any) => {
    setFeed({
      ...feed,
      [e.target.name]: e.target.value,
    });
  };

  const handleKeyPress = (e: any) => {
    if (e.key === " ") {
      const value = e.target.value.split(" ");
      if (!feed.hashtags.includes(value[0]))
        setFeed({
          ...feed,
          hashtags: [...feed.hashtags, value[0]],
        });
      e.target.value = "";
    }
  };

  const onRemove = (event: any, data: any) => {
    const index = feed.hashtags.indexOf(data.content);
    feed.hashtags.splice(index, 1);
    const result = feed.hashtags.filter((word: any) => word != data.content);
    setFeed({ hashtags: result });
  };

  const uploadFile = (e: any) => {
    e.stopPropagation(); // 이벤트 전파 방지
    setFeed({
      ...feed,
      filePath: Array.from(e.target.files),
    });
  };

  const handleSubmit = (event: any) => {
    event.preventDefault(); // 새로고침 방지

    const body = new FormData();
    body.append("content", feed.content);
    body.append("userId", JSON.stringify(1)); // ###### 개발용 ######

    if (feed.filePath != null) {
      feed.filePath.map((each: any) => {
        body.append("files", each);
      });
    }

    feed.hashtags.map((each: any) => {
      body.append("hashtags", each);
    });

    console.log(feed); // ##### 디버그 #####

    http
      .post(`/feed`, body, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then(() => {
        alert("성공");
      })
      .catch(() => {
        alert("실패");
      });
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
              <Form.Field>
                <Input
                  placeholder="해시태그를 입력하세요"
                  onKeyUp={handleKeyPress}
                />
                {feed.hashtags.map((tag: any, index: any) => {
                  return (
                    <Label
                      name="mail"
                      key={index}
                      content={tag}
                      removeIcon="delete"
                      onRemove={onRemove}
                    />
                  );
                })}
              </Form.Field>
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
