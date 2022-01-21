import React from 'react'
import { Grid, Divider, Button, Icon, Image, Item, Label } from 'semantic-ui-react'
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar';
import Axios from "axios"
import { useEffect, useState } from 'react';
import styles from "../../styles/signup.module.css"
import FeedList from '../../src/component/FeedList';

export default function profileperson() {

const [list, setList] = useState([]);
const paragraph = <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png' />;
const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

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
      <Icon name='building outline'/>
        <Item.Header as='a'>회사명&nbsp;</Item.Header>
        <Icon name='lemon outline'/>
          <span className='cinema'>
              9day</span>
    
        
        <Item.Description>{paragraph}</Item.Description>
        <Item.Extra>
          <Button basic floated='right'>
            Edit
          </Button>
          
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
      <FeedList list={list} />
    </Grid.Column>
  </Grid>

   


    </>
)

}
