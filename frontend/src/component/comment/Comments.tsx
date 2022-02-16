import Router from "next/router";
import { useState } from "react";
import { List, Image, Icon, Label, Input } from "semantic-ui-react";
import allAxios from "src/lib/allAxios";
import Comment from "./Comment";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

function Comments({feedWriter, nickName ,list, userSeq, feedId }: any) {
  const [comment, setComment] = useState("");

  const handleComment = (e: any) => {
    setComment(e.target.value);
  };

  const writeComment = () => {
    const body = new FormData();
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    console.log(userSeq);
    console.log(feedId);
    console.log(userSeq);

    // 보내는 사람
    const fromUserId = localStorage.getItem("userId");
    const messages = nickName+"님이 게시글에 댓글을 작성하였습니다"
    const socket = new SockJS('http://i6c210.p.ssafy.io:8080/stomp');
    const stompClient = Stomp.over(socket);

    allAxios
      .post(`/comment`, body)
      .then(() => {
        // 메시지 전달
        setTimeout(() => {
           stompClient.send(`/send/`+fromUserId+`/`+feedWriter+`/`+messages
          );
        }, 300);

        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  console.log(list);

  return (
    <>
      {userSeq != undefined && (
        <Input
          fluid
          className="commentInput"
          placeholder="Comment"
          icon="comment"
          iconPosition="left"
          value={comment}
          onChange={handleComment}
          action={{
            icon: "send",
            onClick: writeComment,
          }}
        />
      )}

      <List relaxed="very" divided verticalAlign="middle">
        {Object.entries(list).map((object: any) => {
          return (
            <List.Item key={object}>
              <Comment nickName={nickName} item={object} userSeq={userSeq} feedId={feedId} />
            </List.Item>
          );
        })}
      </List>
    </>
  );
}

export default Comments;
