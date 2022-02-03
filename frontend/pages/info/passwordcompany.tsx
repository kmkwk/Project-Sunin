import React, { useState } from 'react'
import { Grid, Header, Divider, Form, Button } from 'semantic-ui-react'
import styles from "../../styles/signup.module.css"
import Navbar from '../../src/component/Navbar'
import Menubar from '../../src/component/Menubar'
import Link from 'next/link'


const Passwordcompany = () => {

  const [currentpassword, setCurrentPassword] = useState("");
  const [newpassword, setNewPassword] = useState("");
  const [confirmpassword, setConfirmPassword] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const onSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    if (newpassword !== confirmpassword) {
      return setPasswordError(true);
    }
    console.log({
      currentpassword,
      newpassword,
      confirmpassword,
    });
  };
    const onChangeCurrentPassword = (event: React.ChangeEvent<HTMLInputElement>) => {
      setCurrentPassword(event.target.value);
    };
    const onChangeNewPassword = (event: React.ChangeEvent<HTMLInputElement>) => {
      setNewPassword(event.target.value);
    };
    const onChangeConfirmPassword = (
      event: React.ChangeEvent<HTMLInputElement>
    ) => {
      setPasswordError(event.target.value !== newpassword);
      setConfirmPassword(event.target.value);
    };

return (
  <>
  <Navbar/>

  <Grid columns={2} padded stackable>
        <Grid.Column width={4}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
        <div className={ styles.headeralign }>
        <Header size='huge'>비밀번호 변경</Header>
        <Divider />
        </div>
        <div className={ styles.formalign }>
        <Form onSubmit={onSubmit}>
        <Form.Field required>
          <label>현재 비밀번호</label>
          <Form.Input 
          placeholder='Current Password'
          name='currentpassword'
          value={currentpassword}
          onChange={onChangeCurrentPassword} />
        </Form.Field>
        <Form.Field required>
          <label>새 비밀번호</label>
          <input placeholder='New Password'
          name='newpassword'
          value={newpassword}
          onChange={onChangeNewPassword} />
        </Form.Field>
        <Form.Field required>
          <label>새 비밀번호 확인</label>
          <input placeholder='Confirm New Passwrod'
            name="confirmpassword"
            value={confirmpassword}
            type="password"
            onChange={onChangeConfirmPassword} />
            {passwordError && (
              <div style={{ color: "red" }}>
                비밀번호가 일치하지 않습니다.
               </div>
                )}
        </Form.Field>

        <Button type='submit'>비밀번호 변경</Button>
      </Form>
      


      </div>
      <br/>

      </Grid.Column>
      <Grid.Column width={2}>
         
         </Grid.Column>

  </Grid>
  
 </>
);
};


export default Passwordcompany;