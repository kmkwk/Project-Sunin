import { useEffect, useState } from "react";
import { Input, Message } from "semantic-ui-react";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import styles from '../../../styles/chat.module.css'
import 'semantic-ui-css/semantic.min.css'
import userAxios from 'src/lib/userAxios'
import IsLogin from 'src/lib/customIsLogin'
import User from "src/class/User";

function Chatting() {
  const SERVER_URL = "http://i6c210.p.ssafy.io:8080/stomp";
  // const stompClient = Stomp.over(new SockJS(SERVER_URL));
  const [userInfo, setUserInfo]: any = useState([])
  const isLogin = IsLogin
  const fixname = userInfo.username
  const click = (e: any) =>{
    if(list.message !== ""){
      send(); 
      const erase: any = document.getElementsByName("message")[0] 
      erase['value'] = ''
    }
  }

  const [list, setList]: any = useState({
    stompClient: Stomp.over(new SockJS(SERVER_URL)),
    userName: "",
    message: "",
    recvList: [],
  });


useEffect(() => {
    if (isLogin) {
      userAxios
      .get(`/api/v1/users`, {

      })
      .then(({ data }:any) => {
        setUserInfo(data.body.user)
      })
      .catch((e: any) => {
        console.log(e)
        // alert("잘못된 접근입니다.");
      });
    }
  }, [])

  useEffect(() => {
    console.log(`소켓 연결을 시도합니다. 서버 주소: ${SERVER_URL}`);
    list.stompClient.connect(
      {},
      (frame: any) => {
        // 소켓 연결 성공
        list.stompClient.connected = true;
        console.log("소켓 연결 성공", frame);
        list.stompClient.subscribe("/sub", (res: any) => {
          console.log("구독으로 받은 메시지 입니다.", res.body);
          list.recvList.push(JSON.parse(res.body));
          setList({
            ...list,
            message:""
          });
        });
      },
      (error: any) => {
        console.log("소켓 연결 실패", error);
        list.stompClient.connected = false;
      }
    );
  }, []);

  const handleOnChange = (e: any) => {
    setList({
      ...list,
      [e.target.name]: e.target.value,
    });
  };

  function sendMessage(e: any) {
    if (e.key === "Enter" && list.userName !== "6" && list.message !== "" ) {
      send();
      const nametag: any = document.getElementsByName('userName')[0]
      // nametag['value'] = ''
      e.target.value = "";
    }
  }
  function send() {
    console.log("Send message:" + list.message);
    if (list.stompClient && list.stompClient.connected) {
      const msg = {
        userName: fixname,
        content: list.message,
      };
      // stompClient.send(`/app/send/`+ toUserId+`/`+messages)
      list.stompClient.send("/receive", JSON.stringify(msg), {});
    }
    console.log(list.recvList);
  }

  return (
    <div className={styles.chat_border}>
      <header>
        <h1 className={styles.chat_header}>🌞Sun-In 채팅방🌞</h1>
      </header>
      <div className={styles.chat_background}>
        {list.recvList.slice(0).reverse().map((data: any, index: any) => {
          return (
            <div key={index}>
              <br />
            <div className={styles.chat_message}>
              {data.content}
            </div>
            <div className={styles.chat_username}>
              {data.user_name}
            </div>
            </div>
          );
        })}
      </div>
      <div className={styles.chat_write}>
        {/* <span>유저이름: </span> */}
        {/* <br /> */}
        {/* <input name="userName" type="text" onChange={handleOnChange} /> */}
        {/* <br /> */}
        {/* <span>내용: </span> */}
        {/* <br /> */}
        {/* <Input icon='send' fluid/> */}

        <Input 
        name="message"
        type="text"
        onChange={handleOnChange}
        onKeyUp={sendMessage}
        action={{ icon: 'send', onClick: click}} fluid/>
        {/* <input
          name="message"
          type="text"
          onChange={handleOnChange}
          onKeyUp={sendMessage}
        /> */}
      </div>
    </div>
  );
}

export default Chatting;
