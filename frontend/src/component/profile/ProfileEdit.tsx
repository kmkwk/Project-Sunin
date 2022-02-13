import Image from "next/image";
import React, { useState } from "react";
import { Button, Modal } from "semantic-ui-react";

const ProfileEdit = () => {
  // const [open, setOpen] = React.useState(false);

  // const [tempnickname, setTempNickname] = useState("임시닉네임");
  // const [tempintroduce, setTempIntroduce] = useState("tempIntroduce");
  // const [nickname, setNickname] = useState("닉네임");
  // const [introduce, setIntroduce] = useState("introduce");

  // function writeNickname(e: any) {
  //   setTempNickname(e.target.value);
  // }

  // function writeIntroduce(e: any) {
  //   setTempIntroduce(e.target.value);
  // }

  // function submitChange(nick: String, intro: String) {
  //   console.log("서버전송");
  // }

  // return (
  //   <>
  //     <Modal
  //       onClose={() => setOpen(false)}
  //       onOpen={() => setOpen(true)}
  //       open={open}
  //       trigger={<Button>Edit</Button>}>
  //       <Modal.Header>My Profile</Modal.Header>
  //       <Modal.Content image>
  //         <Image
  //           size="medium"
  //           src="https://react.semantic-ui.com/images/wireframe/image.png"
  //           wrapped
  //         />
  //         <Modal.Description>
  //           {/* <Header>프로필을 저장하시겠습니까?</Header> */}
  //           <label htmlFor="nickname">닉네임:&nbsp;&nbsp;</label>
  //           <textarea
  //             name="nickname"
  //             id="nickname"
  //             cols={70}
  //             rows={2}
  //             onChange={writeNickname}
  //             maxLength={20}>
  //             {nickname}
  //           </textarea>
  //           <br />
  //           <label htmlFor="introduce">자기소개: </label>
  //           <textarea
  //             name="introduce"
  //             id="introduce"
  //             cols={70}
  //             rows={12}
  //             onChange={writeIntroduce}
  //             maxLength={1000}>
  //             {introduce}
  //           </textarea>
  //         </Modal.Description>
  //       </Modal.Content>

  //       <Modal.Actions>
  //         <Button color="black" onClick={() => setOpen(false)}>
  //           취소
  //         </Button>

  //         <Button
  //           content="저장"
  //           icon="checkmark"
  //           onClick={() => {
  //             setOpen(false);
  //             setNickname(tempnickname);
  //             setIntroduce(tempintroduce);
  //             submitChange(tempnickname, tempintroduce);
  //           }}
  //           positive
  //         />
  //       </Modal.Actions>
  //     </Modal>
  //   </>
  // );
  return <></>;
};

export default ProfileEdit;
