import React from 'react'
import Link from 'next/link'
import { Image, Form, Divider, Grid, Segment, Button, GridColumn } from 'semantic-ui-react'
import '../styles/signup.module.css';
import Navbar from '../src/component/Navbar'

const DividerExampleVertical = () => (
  <>
  <Navbar />
  <Segment placeholder>
    <Grid columns={2} relaxed='very' stackable>
      
      <Grid.Column>
        
        <Image src='./images/로고.png' size='huge' />
        
      </Grid.Column>
     
      <Grid.Column>

   
    <Form>
    <Form.Field required>
      <label>이메일</label>
      <input placeholder='Email' />
    </Form.Field>
    <Form.Field required>
      <label>비밀번호</label>
      <input placeholder='Password' />
    </Form.Field>
    <Form.Field required>
      <label>비밀번호 확인</label>
      <input placeholder='Password' />
    </Form.Field>
    <Form.Field required>
      <label>이름</label>
      <input placeholder='Name' />
    </Form.Field>
    <Form.Field required>
      <label>닉네임</label>
      <input placeholder='Nickname' />
    </Form.Field>
    <Form.Field required>
      <label>전화번호</label>
      <input placeholder='Tel. ex) 01012345678' />
    </Form.Field>
    <Form.Field>
      <label>주소</label>
      <input placeholder='Address' />
    </Form.Field >
    <Button.Group widths='2'>
    <Button type='submit'>RESISTER</Button>
    <Link href='/Signup'>
    <Button type='submit'>Back</Button>
    </Link>
    </Button.Group>
  </Form>

       
      </Grid.Column>
    </Grid>


  </Segment>
  </>
)

export default DividerExampleVertical