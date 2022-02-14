import Router from "next/router";
import { useState } from "react";
import { List, Image, Icon, Label, Input } from "semantic-ui-react";
import allAxios from "src/lib/allAxios";

function Comment({ item, userSeq, feedId }: any) {
  const [comment, setComment] = useState("");
  const [editable, setEditable] = useState(false); // 테스트
  const [replyable, setReplyable] = useState(false); // 테스트

  const writeDate = (date: any) => {
    return date.split("T")[0];
  };

  const handleComment = (e: any) => {
    setComment(e.target.value);
  };

  const likeComment = (e: any) => {
    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("feedId", feedId);
    body.append("userId", userSeq);

    allAxios
      .put(`/comment/addLike`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  const modifyButton = () => {
    setEditable(!editable);
  };

  const modifyComment = () => {
    setEditable(false);

    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .put(`/comment`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  const deleteButton = (e: any) => {
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

  const replyButton = (e: any) => {
    console.log("대댓글 작성");
    setReplyable(!replyable);
  };

  const replyComment = (e: any) => {
    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .post(`/comment/reply`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  return (
    <>
      <List.Content floated="right">
        {userSeq == 0 && (
          <Label>
            <Icon name="like" />
            {item[1].likes}
          </Label>
        )}
        {userSeq != 0 && !item[1].deleted && (
          <>
            <Label as="a" onClick={likeComment}>
              <Icon name="like" />
              {item[1].likes}
            </Label>
            {item[1].depth == 0 ? (
              <Label as="a" onClick={replyButton}>
                <Icon name="reply" />
                Reply
              </Label>
            ) : (
              ""
            )}
          </>
        )}
        {userSeq == item[1].writer && !item[1].deleted && (
          <>
            <Label as="a" name={item[0]} onClick={modifyButton}>
              <Icon name="edit" />
              Edit
            </Label>

            <Label as="a" name={item[0]} onClick={deleteButton}>
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
        src={item[1].user.image}
        alt={item[1].user.image}
      />
      <List.Content>
        <List.Header as="">
          {item[1].depth > 0 && (
            <Icon name="level up alternate" rotated="clockwise" />
          )}
          <span>{item[1].user.nick_name}</span>
          <span> | {writeDate(item[1].write_date)}</span>
        </List.Header>
        <List.Description>
          {item[1].deleted ? (
            "※ 삭제된 댓글입니다."
          ) : editable ? (
            <Input
              placeholder="Comment"
              icon="comment"
              iconPosition="left"
              value={comment}
              onChange={handleComment}
              action={{
                icon: "send",
                onClick: modifyComment,
              }}
            />
          ) : (
            item[1].content
          )}

          {!item[1].deleted && item[1].modified && !editable
            ? "(" + writeDate(item[1].modified_date) + " 수정됨)"
            : ""}
        </List.Description>
        <List.Description>
          {replyable && (
            <>
              <br />
              <Icon name="level up alternate" rotated="clockwise" />
              &nbsp;&nbsp;
              <Input
                placeholder="Comment"
                icon="comment"
                iconPosition="left"
                value={comment}
                onChange={handleComment}
                action={{
                  icon: "send",
                  onClick: replyComment,
                }}
              />
            </>
          )}
        </List.Description>
      </List.Content>
    </>
  );
}

export default Comment;
