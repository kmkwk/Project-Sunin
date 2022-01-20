import React from 'react'
import { Header, Divider, Form, Button } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'
import Link from 'next/link'


const HeaderExampleContent = () => (
  <>
  <Navbar/>
  <div className={ styles.headeralign }>
    <Header size='huge'>비밀번호 변경</Header>
    <Divider />
    </div>
    <div className={ styles.formalign }>
    <Form>
    <Form.Field>
      <label>현재 비밀번호</label>
      <input placeholder='Password' />
    </Form.Field>
    <Form.Field>
      <label>새 비밀번호</label>
      <input placeholder='Password' />
    </Form.Field>
    <Form.Field>
      <label>새 비밀번호 확인</label>
      <input placeholder='Passwrod' />
    </Form.Field>

    <Button type='submit'>비밀번호 변경</Button>
  </Form>
  


  </div>
  <br/>
 </>
)

export default HeaderExampleContent