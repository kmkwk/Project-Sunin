import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import styles from '../styles/chat.module.css'

function Index() {
  const SERVER_URL = "http://localhost:8080";
  // const stompClient = Stomp.over(new SockJS(SERVER_URL));

  const fixname = '고정이름'

  const [list, setList]: any = useState({
    stompClient: Stomp.over(new SockJS(SERVER_URL)),
    userName: "",
    message: "",
    recvList: [],
  });

  useEffect(() => {
    console.log(`소켓 연결을 시도합니다. 서버 주소: ${SERVER_URL}`);
    list.stompClient.connect(
      {},
      (frame: any) => {
        // 소켓 연결 성공
        list.stompClient.connected = true;
        console.log("소켓 연결 성공", frame);
        list.stompClient.subscribe("/send", (res: any) => {
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
    if (e.key === "Enter" && list.userName !== "6" && list.message !== "") {
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
      list.stompClient.send("/receive", JSON.stringify(msg), {});
    }
    console.log(list.recvList);
  }

  return (
    <div>
      <header className={styles.chat_wrap}>
      {list.recvList.map((data: any, index: any) => {
        return (
          <h3 key={index} className={styles.back}>
            유저이름: {data.userName} / 내용: {data.content}
          </h3>
        );
      })}
      </header>
      {/* <span>유저이름: </span> */}

      {/* <input name="userName" type="text" onChange={handleOnChange} /> */}
      

      <div className={styles.foot}>

        <span>내용: </span>
        <input
          name="message"
          type="text"
          onChange={handleOnChange}
          onKeyUp={sendMessage} />

        </div>
    </div>
  );
}

export default Index;

