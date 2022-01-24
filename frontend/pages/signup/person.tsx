import React, { useState } from 'react'
import Link from 'next/link'
import { Image, Form, Divider, Grid, Segment, Button, GridColumn } from 'semantic-ui-react'
import '../../styles/signup.module.css';
import Navbar from '../../src/component/Navbar'




const signup =()=>{
  
  const [email,setEmail] = useState('');
  const [name,setName] = useState('');
  const [nick,setNick] = useState('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [phonenumber,setPhonenumber] = useState('');
  const [address,setAddress] = useState('');

  const onSubmit = (e) => {
    e.preventDefault();

    if(password !== passwordCheck){
      return setPasswordError(true);
    }

    console.log({
      email,
      name,
      nick,
      password,
      passwordCheck,
      phonenumber,
      address
    });
  };
const onChangeEmail = (e) => {
  setEmail(e.target.value);
};
const onChangPassword = (e) => {
  setPassword(e.target.value);
};
const onChangePasswordChk = (e) => {
  setPasswordError(e.target.value !== password);
  setPasswordCheck(e.target.value);
};
const onChangeName = (e) => {
  setName(e.target.value);
};
const onChangeNick = (e) => {
  setNick(e.target.value);
};
const onChangePhone = (e) => {
  setPhonenumber(e.target.value);
};
const onChangeAddress = (e) => {
  setAddress(e.target.value);
}

return (
  <>
  <Navbar />
  <Segment placeholder>
    <Grid columns={2} relaxed='very' stackable>
      
      <Grid.Column>
        
        <Image src='../images/로고.png' size='huge' />
        
      </Grid.Column>
     
      <Grid.Column>

   
    <Form>
    <Form.Field required>
      <label>이메일</label>
      <input placeholder='Email' onChange={onChangeEmail} />
    </Form.Field>
    <Form.Field required>
      <label>비밀번호</label>
      <input placeholder='Password' onChange={onChangPassword} />
    </Form.Field>
    <Form.Field required>
      <label>비밀번호 확인</label>
      <input placeholder='Password' onChange={onChangePasswordChk} />
    </Form.Field>
    <Form.Field required>
      <label>이름</label>
      <input placeholder='Name' onChange={onChangeEmail} />
    </Form.Field>
    <Form.Field required>
      <label>닉네임</label>
      <input placeholder='Nickname' onChange={onChangeEmail} />
    </Form.Field>
    <Form.Field>
      <label>전화번호</label>
      <input placeholder='Tel. ex) 01012345678' onChange={onChangeEmail} />
    </Form.Field>
    <Form.Field>
      <label>주소</label>
      <input placeholder='Address' onChange={onChangeEmail} />
    </Form.Field >
    <Button.Group widths='2'>
    <Button type='submit'>RESISTER</Button>
    <Link href='/signup'>
    <Button type='submit'>Back</Button>
    </Link>
    </Button.Group>
  </Form>

       
      </Grid.Column>
    </Grid>


  </Segment>
  </>
);
};

export default Signup;