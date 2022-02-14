import React from 'react'
import { Grid, Header, Divider, Form, Button } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar'
import Link from 'next/link'


const HeaderExampleContent = () => (
  <>
  <Navbar/>

  <Grid columns={2} padded>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
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
    <Link href="info/person">
    <Button type='submit'>확인</Button>
    </Link>
    </div>          
        </Grid.Column>
        <Grid.Column width={2}>
         
         </Grid.Column>
      </Grid>


  
  </>
)

export default HeaderExampleContent