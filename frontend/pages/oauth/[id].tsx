import axios from "axios";
import { useRouter } from "next/router";
import { useEffect } from "react";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default function Authentication() {
  const router = useRouter();

  if (typeof window !== "undefined") {
    localStorage.removeItem("token");
    localStorage.setItem("token", String(router.query.token));
  }

  useEffect(() => {
    console.log(router);
    router.push("/");
    setTimeout(userA,3000);

  }, []);
  
  function userA() {
    const token = localStorage.getItem("token");

    axios.get("http://i6c210.p.ssafy.io:8080/api/v1/users", {
    headers: { Authorization: `Bearer ${token}` },
  })
  .then(({ data }) => {
    localStorage.setItem("userId",data.body.user.user_seq)
    connect();
  })
  .catch(() => {
    alert('로그인 정보 가져오기 실패')
    router.push('/')
  })
  }

  function connect() {
    const userId = localStorage.getItem("userId");
    const socket = new SockJS('http://localhost:8080/stomp');
    const stompClient = Stomp.over(socket);

    stompClient.connect(
      {},
      (frame: any) => {
        stompClient.connected = true;
        console.log("소켓 연결 성공", frame);
        stompClient.subscribe(`/sub/`+userId, (res: any) => {
          console.log("구독으로 받은 메시지 입니다.", res.body);
        });
      },
      (error: any) => {
        console.log("소켓 연결 실패", error); 
        stompClient.connected = false;
      } 
    );
  }
  return <></>;
}
