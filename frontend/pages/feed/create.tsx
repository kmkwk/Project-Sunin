import { Button, Form, Input, Image, Grid, TextArea } from "semantic-ui-react";
import { useState } from "react";
import Navbar from "../../src/component/Navbar";
import styles from "../../styles/CreateFeed.module.css";
import http from "../../src/lib/customAxios";
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import SwiperCore, { Navigation } from "swiper";

export default function Createfeed() {
  const [feed, setFeed] = useState({
    userId: 0, // 작성자
    content: "", // 내용
    filePath: [], // 이미지, 동영상
    hashtags: [], // 해시태그
  });
  SwiperCore.use([Navigation]);
  const [swiper, setSwiper] = useState(null);
  const swiperParams = {
    navigation : true,
    onSwiper : setSwiper,
  }
  const [attachment,setAttachment] = useState()
  const [createObjectURL, setCreateObjectURL] = useState(null);
  const handleOnChange = (e: any) => {
    setFeed({
      ...feed,
      [e.target.name]: e.target.value,
    });
  };

  const uploadFile = (e: any) => {
    e.stopPropagation(); // 이벤트 전파 방지
    setFeed({
      ...feed,
      [e.target.name]: e.target.value,
      filePath: Array.from(e.target.files),
    });
    const {
      target: { files },
    } = e;
    const theFile = files[0];
    const reader = new FileReader();

   reader.onloadend = (finishedEvent: any) => {
    setAttachment(finishedEvent.currentTarget['result']);
    };
    if (theFile) {
    reader.readAsDataURL(theFile);
  } else {
    setAttachment(undefined)
  }

  };

  const handleSubmit = (event: any) => {
    event.preventDefault(); // 새로고침 방지

    const body = new FormData();
    body.append("content", feed.content);
    body.append("userId", JSON.stringify(1)); // ###### 개발용 ######
    feed.filePath.map((each) => {
      body.append("files", each);
    });
    // feed.hashtags.map((each) => {
    //   body.append("hashtags", each);
    // });

    // console.log(feed.content); // ##### 디버그 #####

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

  const onClearAttachment = () => {
    const erase: any = document.getElementsByName('myImage') 
    erase[''] = null
  setAttachment(undefined);
}

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
                  <Image src={createObjectURL} width="100px" />
                  <input
                    type="file"
                    accept="image/*, video/*"
                    multiple
                    onChange={uploadFile}
                  />
                        {attachment && (
        <div>
          <br/>
        <img src={attachment} width="80%" height="80%"/>
        
        <Button basic color='grey' onClick={onClearAttachment} fluid>Cancel</Button>
        </div>
      )}
                </div>
              </Form.Field>
              {/* <Form.Field
                control={Input}
                label="Tag"
                placeholder="원하는 태그를 작성하세요..."
                name="hashtags"
                onChange={handleOnChange}
              /> */}
              <Form.Field>
                <Swiper className={styles.swiperStyle}>
                {/* <Swiper className={styles.swiperStyle} {...swiperParams} ref={setSwiper}> */}
                  <SwiperSlide>1</SwiperSlide>
                  <SwiperSlide>2</SwiperSlide>
                  <SwiperSlide>3</SwiperSlide>
                </Swiper>
              </Form.Field>
              <Button basic color='black' onClick={handleSubmit} fluid>저장하기</Button>

            </Form>
          </Grid.Column>
          <Grid.Column width={3}></Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
}
