import React from 'react'
import { Grid, Header, Divider, Form, Button, Image } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar'
import Link from 'next/link'


const HeaderExampleContent = () => (
  <>
  <Navbar/>

  <Grid columns={2} padded>
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={13}>
        <div className={ styles.headeralign }>
    <Header size='huge'>회원정보 수정</Header>
    <Divider />
    </div>
    <div className={ styles.formalign }>
    <Form>
    <Form.Field>
      <label>프로필 사진</label>
      <div>
      <Image src='https://react.semantic-ui.com/images/wireframe/image.png' size='small' verticalAlign='bottom' />{' '}
      <Button basic color='grey'>변&nbsp;경</Button>
      </div>
    </Form.Field>
    <Form.Field>
      <label>이메일</label>
      <input disabled placeholder='Email' />
    </Form.Field>
    <Form.Field>
      <label>비밀번호</label>
      <Form.Group inline>
      <input placeholder='Password' />
      <Link href="passwordperson">
      <Button basic color='grey'>변&nbsp;경</Button>
    </Link>
    </Form.Group>
    </Form.Field>

    <Form.Field>
      <label>이름</label>
      <Form.Group inline>
      <input placeholder='Name' />
      <Button basic color='grey'>
      변&nbsp;경
    </Button>
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>닉네임</label>
      <Form.Group inline>
      <input placeholder='Nickname' />
 
      <Button basic color='grey'>
      변&nbsp;경
    </Button>
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>전화번호</label>
      <Form.Group inline>
      <input placeholder='Tel' />
      <Button basic color='grey'>
      변&nbsp;경
    </Button>
    </Form.Group>
    </Form.Field>
    <Form.Field>
      <label>주소</label>
      <Form.Group inline>
      <input placeholder='Address' />
      <Button basic color='grey'>
      변&nbsp;경
    </Button>
    </Form.Group>
    </Form.Field >

  </Form>
  </div>
  <br/>
         
        </Grid.Column>
      </Grid>  
      </> 

  
)

export default HeaderExampleContent