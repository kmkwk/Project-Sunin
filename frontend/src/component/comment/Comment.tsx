import Router from "next/router";
import { useState } from "react";
import { List, Image, Icon, Label, Input } from "semantic-ui-react";
import allAxios from "src/lib/allAxios";

function Comment({ list, userSeq, feedId }: any) {
  const [comment, setComment] = useState("");

  const writeDate = (date: any) => {
    return date.split("T")[0];
  };

  const handleComment = (e: any) => {
    setComment(e.target.value);
  };

  const writeComment = () => {
    const body = new FormData();
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .post(`/comment`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  const likeFeed = (e: any) => {
    console.log("피드 좋아요");
  };

  const modifyComment = (e: any) => {
    console.log("댓글 수정");
  };

  const deleteComment = (e: any) => {
    const body: any = new FormData();
    body.append("commentId", e.target.name);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .delete("/comment", {
        params: {
          commentId: e.target.name,
          feedId: feedId,
          writer: userSeq,
        },
      })
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  const replyComment = (e: any) => {
    console.log("대댓글 작성");
  };

  return (
    <>
      {userSeq != 0 && (
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
        {Object.keys(list).map((key: any, index: any) => (
          <List.Item key={index}>
            <List.Content floated="right">
              {userSeq == 0 && (
                <Label>
                  <Icon name="like" />
                  {list[key].likes}
                </Label>
              )}
              {userSeq != 0 && !list[key].deleted && (
                <>
                  <Label as="a" onClick={likeFeed}>
                    <Icon name="like" />
                    {list[key].likes}
                  </Label>
                  <Label as="a" onClick={replyComment}>
                    <Icon name="reply" />
                    Reply
                  </Label>
                </>
              )}
              {userSeq == list[key].writer && !list[key].deleted && (
                <>
                  <Label as="a" onClick={modifyComment}>
                    <Icon name="edit" />
                    Edit
                  </Label>

                  <Label as="a" name={key} onClick={deleteComment}>
                    <Icon name="remove" /> Delete
                  </Label>
                </>
              )}
            </List.Content>
            <Image
              avatar
              circular
              width={30}
              height={30}
              src={list[key].user.image}
              alt={list[key].user.image}
            />
            <List.Content>
              <List.Header as="">
                <span>{list[key].user.nickName}</span>
                <span> | {writeDate(list[key].writeDate)}</span>
              </List.Header>
              <List.Description>
                {list[key].deleted == true
                  ? "※ 삭제된 댓글입니다."
                  : list[key].content}
                {list[key].deleted == false && list[key].modified == true
                  ? "(" + writeDate(list[key].modifiedDate) + " 수정됨)"
                  : ""}
              </List.Description>
            </List.Content>
          </List.Item>
        ))}
      </List>
    </>
  );
}

export default Comment;
