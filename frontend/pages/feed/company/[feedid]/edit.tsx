import { useRouter } from "next/router"
import axios from "axios";
import { useEffect, useState } from "react";
import Navbar from "../../../../src/component/Navbar";
import { Form, Grid, Input, Button } from "semantic-ui-react";
import styles from '../../../../styles/edit.module.css'

export default function Edit() {
  const [content, setContent] = useState('');
  const [tag, setTag] = useState('');
  const [image, setImage]: any = useState();
  const [createObjectURL, setCreateObjectURL] = useState(null);

  const router = useRouter()
  const { feedid } = router.query

  const [feed, setFeed] = useState({});

  const API_URL = `http://makeup-api.herokuapp.com/api/v1/products/${ feedid }.json`;

  function getData() {
    axios.get(API_URL).then((res) => {
      setFeed(res.data)
      document.getElementsByTagName('input')[1].value = '#tag'
      document.getElementsByTagName('textarea')[0].value = res.data.description
      setContent(res.data.description)
      setTag('#tag')
    })
  }

  useEffect(() => {
    if (feedid) {
      getData()
    }
  }, [feedid]);

  const updateContent = (event: any) => {
    setContent(event.target.value)
  }

  const updateTag = (event: any) => {
    setTag(event.target.value)
  }

  const uploadToClient = (event: any) => {
    if (event.target.files && event.target.files[0]) {
      const i = event.target.files[0];

      setImage(i);
      // setCreateObjectURL(URL.createObjectURL(i));
    }
  };

  const uploadToServer = async (event: any) => {
    const body = new FormData();
    body.append("file", image);
    body.append("text", content);
    body.append("text", tag);
    const response = await fetch("/api/testpage", {
      method: "PUT",
      body
    });
  };

  function backToDetail() {
    router.push(`/feed/company/${feedid}`)
  }

  return(
    <>
      <Navbar />
      <h1>피드 수정 페이지</h1>
      <p>수정하는 글 번호: {feedid}</p>
      <Grid>
        <Grid.Row>
          <Grid.Column width={3}>
          </Grid.Column>
          <Grid.Column width={10}>
            <Form className={ styles.form }>
              <br /><br />
              <label htmlFor="feedcontent"><b>피드 내용</b></label>
              <textarea 
                name="feedcontent" 
                id="feedcontent" 
                cols={30} 
                rows={10} 
                placeholder='내용을 작성하세요...'
                onChange={updateContent}
              ></textarea>
              <div>
              {/* <img src={createObjectURL} width="500px" /> */}
              <h4>이미지 선택</h4>
              <input type="file" accept="image/*" name="myImage" onChange={uploadToClient} />
            </div>
              <br /><br /><br />
              <Form.Field
                  control={Input}
                  label='Tag'
                  placeholder='원하는 태그를 작성하세요...'
                  onChange={updateTag}
                />
              <br /><br />
              <Form.Field control={Button} onClick={uploadToServer}>저장하기</Form.Field>
            </Form>
            <Button onClick={backToDetail}>뒤로가기</Button>
          </Grid.Column>
          <Grid.Column width={3}>
            <Button>글 삭제</Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </>
  )
}