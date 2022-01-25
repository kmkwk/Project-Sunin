import React, { useEffect, useState } from 'react'
import { Grid, Divider, Button, Icon, Image, Item, Modal, Header } from 'semantic-ui-react'
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar';
import Axios from "axios"
import styles from "../../styles/signup.module.css"
import ProfileList from '../../src/component/ProfileList'

export default function profileperson() {

  let [글제목, 글제목변경] = useState([]); 
  let [입력값, 입력값변경] = useState('');

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
        
        <Item.Header as='a'>닉네임&nbsp;</Item.Header>
        <Icon name='lemon outline'/>
          <span className='cinema'>
              9day</span>
    
        
        <Item.Description>{paragraph}</Item.Description>
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
          <Header>프로필을 저장하시겠습니까?</Header>
          <div className='publish'>
      <input onChange={ (e)=>{ 입력값변경(e.target.value) } } />
      <button onClick={ ()=>{
        var arrayCopy = [...글제목];
        arrayCopy.unshift(입력값);
        글제목변경( arrayCopy );
      } }>저장</button>
    </div>
         
        </Modal.Description>
      </Modal.Content>
      <Modal.Actions>

      <Button color='grey' onClick={() => setOpen(false)}>
          취소
        </Button>
          <Button
          content="저장"
          labelPosition='취소'
          icon='checkmark'
          onClick={() => setOpen(false)}
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
