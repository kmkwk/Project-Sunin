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
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={13}>
        <div className={ styles.headeralign }>
        <Header size='huge'>비밀번호 변경</Header>
        <Divider />
        </div>
        <div className={ styles.formalign }>
        <Form>
        <Form.Field>
          <label>현재 비밀번호</label>
          <input placeholder='Current Password' />
        </Form.Field>
        <Form.Field>
          <label>새 비밀번호</label>
          <input placeholder='New Password' />
        </Form.Field>
        <Form.Field>
          <label>새 비밀번호 확인</label>
          <input placeholder='Confirm New Passwrod' />
        </Form.Field>

        <Button type='submit'>비밀번호 변경</Button>
      </Form>
      


      </div>
      <br/>

      </Grid.Column>

  </Grid>
  
 </>
)

export default HeaderExampleContent