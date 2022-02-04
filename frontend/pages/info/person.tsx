import React, { useState } from "react";
import { Grid, Header, Divider, Form, Button, Image } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar'
import Link from 'next/link'


const infoperson = () => {
  const [content, setContent] = useState('');
  const [tag, setTag] = useState('');
  const [image, setImage]: any = useState();
  const [createObjectURL, setCreateObjectURL] = useState(null);


  const uploadToClient = (event: any) => {
    if (event.target.files && event.target.files[0]) {
      const i = event.target.files[0];

      setImage(i);
      setCreateObjectURL(URL.createObjectURL(i));
    }
  };

  const uploadToServer = async (event: any) => {
    const body = new FormData();
    body.append("file", image);
    body.append("text", content);
    body.append("text", tag);
    const response = await fetch("/api/testpage", {
      method: "POST",
      body
    });
    document.getElementsByTagName('textarea')[0].value = ''
    document.getElementsByTagName('input')[0].value = ''
    document.getElementsByTagName('input')[1].value = ''
    setContent('')
    setTag('')
    setImage(null)
  };


  return (
  <>
  <Navbar/>

  <Grid columns={2} padded stackable>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
        <div className={ styles.headeralign }>
    <Header size='huge'>회원정보 수정</Header>
    <Divider />
    </div>
    <div className={ styles.formalign }>
    <Form>
    <Form.Field>
      <div>
      <h4>프로필 사진</h4>
      <Image src={createObjectURL} width="100px" />
              
              <input type="file" accept="image/*" name="myImage" onChange={uploadToClient} />
      {/* <Image src='https://react.semantic-ui.com/images/wireframe/image.png' size='small' verticalAlign='bottom' />{' '}
      <Button basic color='grey'>변&nbsp;경</Button> */}
      </div>
    </Form.Field>
    <Form.Field>
      <label>이메일</label>
      <input disabled placeholder='Email' />
    </Form.Field>
    <Form.Field>
      <label>비밀번호</label>
      <Form.Group inline>
     
      <Link href="passwordperson">
      <Button basic color='grey' fluid>변&nbsp;경</Button>
    </Link>
    </Form.Group>
    </Form.Field>

    <Form.Field>
      <label>이름</label>
      <Form.Group inline>
      <input placeholder='Name' />
      {/* <Button basic color='grey'>
      변&nbsp;경
    </Button> */}
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>닉네임</label>
      <Form.Group inline>
      <input placeholder='Nickname' />
 
      {/* <Button basic color='grey'>
      변&nbsp;경
    </Button> */}
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>전화번호</label>
      <Form.Group inline>
      <input placeholder='Tel' />
      {/* <Button basic color='grey'>
      변&nbsp;경
    </Button> */}
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>주소</label>
      <Form.Group inline>
      <input placeholder='Address' />
      {/* <Button basic color='grey'>
      변&nbsp;경
    </Button> */}
    </Form.Group>
    </Form.Field >
    
  </Form>
  {/* <Form.Field control={Button} onClick={uploadToServer}>저장하기</Form.Field> */}
  <br/>
  <Button basic color='grey' onClick={uploadToServer} fluid>저장하기</Button>
  </div>
  <br/>
  
         
        </Grid.Column>
        <Grid.Column width={2}>
         
        </Grid.Column>
      </Grid>  
      </> 

  );
  };

export default infoperson;