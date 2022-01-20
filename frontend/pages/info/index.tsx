import React from 'react'
import { Header, Divider, Form, Button } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'


const HeaderExampleContent = () => (
  <>
  <Navbar/>
  <div className={ styles.headeralign }>
    <Header size='huge'>회원정보 수정</Header>
    <Divider />
  </div>
  <div className={ styles.textalign }>
      <h3>개인정보를 수정하려면 인증이 필요합니다.
        <br/>비밀번호 입력 후 확인 버튼을 눌러주세요. 
      </h3>

    </div>
    <div className={ styles.formalign }>
    <Form>
  <Form.Field>
      <input placeholder='Password' />
    </Form.Field>
    </Form>
    </div>
    <div className={ styles.buttonalign }>
    <Button type='submit'>확인</Button>
    </div>
  </>
)

export default HeaderExampleContent