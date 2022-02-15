import axios from "axios";
import { useEffect, useState } from "react";
// import { Icon, Button, Image, Modal } from "semantic-ui-react";
import { Button, Header, Icon, Segment, TransitionablePortal } from "semantic-ui-react";
import styles from '../../styles/alarm.module.css'
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default function Alarm(){

  // const [open, setOpen] = useState(false)

  const [open, setOpen] = useState(false)

  const token = localStorage.getItem("token");

  function handleClick() {
    setOpen(true)
  }
  function handleClose() {
    setOpen(false)
  }
  const FromUserId = 2
  const toUserId = 1;
  const listUser = [FromUserId,toUserId];
  const message = "";

  useEffect(() => {
    connect()
  })
  
  const socket = new SockJS('http://localhost:8080/stomp');
  const stompClient = Stomp.over(socket);


  function connect() {
    // const userIds = localStorage.getItem("userId");
    stompClient.connect(
      {},
      (frame: any) => {
        // 소켓 연결 성공
        stompClient.connected = true;
        console.log("소켓 연결 성공", frame);
        // 로컬스토리지에 상태관리가 
        // 메세지 받는 사람 - 게시글 작성한 사람
        // 게시글을 작성한사람의 채널을 열어놓음
        stompClient.subscribe(`/sub/`+toUserId, (res: any) => {
          console.log("구독으로 받은 메시지 입니다.", res.body);
        });
      },
      (error: any) => {
        console.log("소켓 연결 실패", error); 
        stompClient.connected = false;
      } 
    );
  }

  function send() {
    stompClient.send(`/app/send/`+ toUserId+`/`+messages)
  }
  
  // subscribe 현재 게시글의 좋아요를 누른사람의 정보가 들어가있다. /sub/보내는사람의 localstorage에서 가져온 userId
  // 팔로워를 눌렀을때 send(받는사람의 pk을 우선 보내서 서버에서 조회를 한 후 userId) 보내면 그 해당 userId 가진 사람이 알람을 받겠지?

  const messages = FromUserId+"님이 팔로워를 요청하셨습니다.";

  

  console.log()
  const userId = 1;
  
  // useEffect(() => {
    function follower(){
      axios
      // 메시지를 받아야함
      .get(`http://localhost:8080/follower/follower/`+ userId,{})
      .then(({ data }) => {
        console.log(data);
        // console.log(localStorage.getItem('token'));
        // connect();
        send()
      })
      .catch((e: any) => {
        console.log(e)
      });
    }
  // }, [])


  return (
    <>
      {/* <Modal
        open={open}
        onClose={() => setOpen(false)}
        onOpen={() => setOpen(true)}
        trigger={<Button><Icon name="bell"/>알림</Button>}
      >
        <Modal.Header>알림</Modal.Header>
        <Modal.Content image>
          <Modal.Description className={styles.alarm_content}>
            <h1></h1>
            <p>댓글 알림</p>
            <p>좋아요 알림</p>
            <p>팔로우 알림</p>
            <p>채팅 알림</p>
            
          </Modal.Description>
        </Modal.Content>
        <Modal.Actions>
          <Button primary onClick={() => setOpen(false)}>
            닫기
          </Button>
        </Modal.Actions>
      </Modal> */}
      <div>
        <Button
          content={open ? <><Icon name="bell"></Icon>알림닫기</> : <><Icon name="bell"></Icon>알림보기</>}
          // negative={open}
          // positive={!open}
          positive={true}
          onClick={follower}
        />
        <TransitionablePortal onClose={handleClose} open={open}>
          <Segment
            style={{ right: '7%', position: 'fixed', top: '5%', zIndex: 1000 }}
            className={ styles.alarm_content }
          >
            <Header>알림</Header>
            <p>-------------------------------------------------------</p>
            <p>ㅁㅁㅁ 님이 작성한 글에 댓글이 달렸습니다.</p>
            <p>댓글 알림</p>
            <p>좋아요 알림</p>
            <p>팔로우 알림</p>
            <p>채팅 알림</p>
          </Segment>
        </TransitionablePortal>
      </div>
    </>
  );
}
