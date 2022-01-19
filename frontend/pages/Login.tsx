import Navbar from "../src/component/Navbar";
import Link from 'next/link'
import { Button, Divider, Form, Grid, Segment, Image } from 'semantic-ui-react'
import { useEffect, useState } from "react";
import styles from "../styles/Login.module.css"

export default function Login() {

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  function GoLogin() {
    setEmail(document.getElementsByTagName('input')[0].value)
    setPassword(document.getElementsByTagName('input')[1].value)
    document.getElementsByTagName('input')[0].value = ''
    document.getElementsByTagName('input')[1].value = ''
    console.log(email, password)
  } 

  return (
    <>
      <Navbar />
      <div>
      <Segment placeholder >
        <br /><br /><br />
    <Grid columns={2} relaxed='very' stackable>
    <Grid.Column verticalAlign='middle'>
      <Image src='/images/로고.png' size='huge' />
      </Grid.Column>

      <Grid.Column className={ styles.location }>
        <Form>
          <Form.Input
            icon='user'
            iconPosition='left'
            label='User Eamil'
            placeholder='User Email'
          />
          <Form.Input
            icon='lock'
            iconPosition='left'
            label='Password'
            type='password'
          />
          
          <Button content='Login' onClick={GoLogin} primary />
        </Form>
        <br /><br /><br />
        <hr /><br /><br /><br />
        <div className={ styles.guide_location }>
          <p>아이디가 없으신가요?<Link href="/Singup"><a>--- 회원가입 ---</a></Link></p>
          <p>아이디가 기억이 안나요<Link href="/Singup"><a>--- 아이디 찾기 ---</a></Link></p>
          <p>비밀번호가 기억이 안나요<Link href="/Singup"><a>--- 비밀번호 찾기 ---</a></Link></p>
        </div>
        
      </Grid.Column>

    </Grid>
  </Segment>
      </div>
      
    </>
  );
}