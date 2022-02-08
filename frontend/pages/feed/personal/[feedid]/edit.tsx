import { useRouter } from "next/router";
import axios from "axios";
import { useEffect, useState } from "react";
import Navbar from "../../../../src/component/Navbar";
import { Form, Grid, Input, Button } from "semantic-ui-react";
import styles from "../../../../styles/edit.module.css";
import http from "../../../../src/lib/customAxios";

export default function Edit() {
  const router = useRouter();
  const { feedid } = router.query;

  const [feed, setFeed] = useState({
    feedId: "", // 피드 ID
    userId: "", // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
    likes: "", // 좋아요 수
    likeUser: [], // 좋아요 누른 사람
    createdDate: "", // 작성일
    modifiedDate: "", // 수정일
  });

  useEffect(() => {
    http
      .get(`/feed/detail/${feedid}`)
      .then(({ data }) => {
        setFeed(data);
        console.log(data); // ##### 디버그 #####
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/feed/personal");
      });
  }, []);

  function modifyFeed() {}

  function backToDetail() {
    router.push(`/feed/personal/${feedid}`);
  }

  return (
    <>
      <Navbar />
      <h1>피드 수정 페이지</h1>
      <p>수정하는 글 번호: {feedid}</p>
      <Grid>
        <Grid.Row>
          <Grid.Column width={3}></Grid.Column>
          <Grid.Column width={10}>
            <Form className={styles.form}>
              <br />
              <br />
              <label htmlFor="feedcontent">
                <b>피드 내용</b>
              </label>
              <textarea
                name="feedcontent"
                id="feedcontent"
                cols={30}
                rows={10}
                placeholder="내용을 작성하세요..."
                onChange={modifyFeed}>
                {feed.content}
              </textarea>
              <div>
                {/* <img src={createObjectURL} width="500px" /> */}
                <h4>이미지 선택</h4>
                <input
                  id="upload-file"
                  type="file"
                  accept="image/*, video/*"
                  multiple
                  // onChange={uploadFile}
                />
              </div>
              <br />
              <br />
              <br />
              <Form.Field
                control={Input}
                label="Tag"
                placeholder="원하는 태그를 작성하세요..."
                // onChange={updateTag}
              />
              <br />
              <br />
              {/* <Form.Field control={Button} onClick={uploadToServer}>
                저장하기
              </Form.Field> */}
            </Form>
            <Button onClick={backToDetail}>뒤로가기</Button>
          </Grid.Column>
          <Grid.Column width={3}>
            <Button>글 삭제</Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
}
