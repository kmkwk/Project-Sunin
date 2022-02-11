import { Button, Form, Input,Image, Grid, TextArea, Label } from "semantic-ui-react";
import { useEffect, useState } from "react";
import styles from "styles/CreateFeed.module.css";
import { useRouter } from "next/router";

import Navbar from "src/component/Navbar";
import userAxios from "src/lib/userAxios";
import User from "src/class/User";
import Feed from "src/class/Feed";

// import Swiper core and required modules
// import { Swiper, SwiperSlide } from 'swiper/react';
// import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";

// Import Swiper styles

import "swiper/components/navigation/navigation.min.css";
import "swiper/components/pagination/pagination.min.css";
import "swiper/swiper.min.css";




export default function Createfeed() {
  const router = useRouter();

  const [feed, setFeed]: any = useState({});
  const [user, setUser]: any = useState({});

  const [attachment,setAttachment] = useState()
  const [createObjectURL, setCreateObjectURL] = useState(null);

  useEffect(() => {
    setFeed(new Feed());
    userAxios
      .get(`/api/v1/users`)
      .then(({ data }) => {
        setUser(new User(data.body.user));
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/feed/personal");
      });
  }, []);

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
    // body.append("userId", user.userSeq);
    body.append("userId", JSON.stringify(1));
    body.append("content", feed.content);

    if (feed.filePath != null) {
      feed.filePath.map((each: any) => {
        body.append("files", each);
      });
    }

    feed.hashtags.map((each: any) => {
      body.append("hashtags", each);
    });

    console.log(feed); // ##### 디버그 #####

    userAxios
      .post(`/feed`, body, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then(() => {
        alert("성공");
        router.push("/feed/personal");
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
              <Form.Field>
                <Input
                  placeholder="해시태그를 입력하세요"
                  onKeyUp={handleKeyPress}
                />
                {feed.hashtags &&
                  feed.hashtags.map((tag: any, index: any) => {
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
              <Button basic color='black' onClick={handleSubmit} fluid>저장하기</Button>

            </Form>
          </Grid.Column>
          <Grid.Column width={3}></Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  );
}
function setAttachment(arg0: any) {
  throw new Error("Function not implemented.");
}

