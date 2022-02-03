import React from 'react'
import { Grid, Divider, Button, Icon, Image, Item, Modal, Header } from 'semantic-ui-react'
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar';
import Axios from "axios"
import { useEffect, useState } from 'react';
import styles from "../../styles/signup.module.css"
import ProfileList from '../../src/component/ProfileList'

export default function profileperson() {

const [list, setList] = useState([]);
const paragraph = <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png' />;
const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";
const [open, setOpen] = React.useState(false)

function getData() {
  Axios.get(API_URL)
  .then(res => {
    console.log(res.data)
    setList(res.data)
  })
}


useEffect(()=>{
  getData();
}, []);

const [tempnickname, setTempNickname] = useState('회사명')
const [tempintroduce, setTempIntroduce] = useState('tempIntroduce')
const [nickname, setNickname] = useState('회사명')
const [introduce, setIntroduce] = useState('introduce')

function writeNickname(e: any) {
  setTempNickname(e.target.value)
}

function writeIntroduce(e: any) {
  setTempIntroduce(e.target.value)
}

function submitChange(nick: String, intro: String) {
  console.log('서버 전달할 코드 여기에 작성', '보내는 회사명: ', nick, '보내는 소개글: ', intro)
}

return (

    <>
    <Navbar/>
    <Grid columns={2} padded>
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={13}>
        <div className={ styles.headeralign }>
  <Item.Group divided>
    <Item>
      <Item.Image src='https://react.semantic-ui.com/images/wireframe/image.png' />

      <Item.Content>
      <Icon name='building outline'/>
        <Item.Header as='a'>{nickname}&nbsp;</Item.Header>
        <Icon name='lemon outline'/>
          <span className='cinema'>
              9day</span>
    
        
        <Item.Description>
          {/* {paragraph} */}
          {introduce}
        </Item.Description>
        <Item.Extra>
        <Modal 
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={<Button>Edit</Button>}
    >
      <Modal.Header>My Profile</Modal.Header>
      <Modal.Content image>
        <Image size='medium' src='https://react.semantic-ui.com/images/wireframe/image.png' wrapped />
        <Modal.Description>
          {/* <Header>프로필을 저장하시겠습니까?</Header> */}
          <label htmlFor="nickname">회사명:&nbsp;&nbsp;</label>
          <p>{nickname}</p>
          {/* <textarea name="nickname" id="nickname" cols={70} rows={2} onChange={writeNickname}>{nickname}</textarea> */}
          <br />
          <label htmlFor="introduce">회사소개: </label>
          <textarea name="introduce" id="introduce" cols={70} rows={12} onChange={writeIntroduce} maxLength={1000}>{introduce}</textarea>
         
         
        </Modal.Description>
      </Modal.Content>
   
      <Modal.Actions>
        <Button color='black' onClick={() => setOpen(false)}>
          취소
        </Button>
  
          <Button
          content="저장"
          icon='checkmark'
          onClick={() => {setOpen(false); setNickname(tempnickname); setIntroduce(tempintroduce); submitChange(nickname, tempintroduce);}}
          positive
        />
      </Modal.Actions>
    </Modal>
          
        </Item.Extra>
        <span className='cinema'>
              게시물수 9&nbsp;&nbsp;&nbsp;</span>
        <Icon name='user' /> followers 127&nbsp;&nbsp;
        <Icon name='user' /> following 219
      </Item.Content>
    </Item>
    </Item.Group>
    </div>
    <Divider />
      <ProfileList list={list} />
    </Grid.Column>
  </Grid>

   


    </>
)

}
