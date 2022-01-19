import { useState } from "react"

export default function CreateFeedForm() {
  const registerUser = async event => {
    event.preventDefault()
    console.log(event)

    const res = await fetch('/api/testpage', {
      body: JSON.stringify({
        name: event.target.feedcontent.value,
        content: event.target.feedcontent.value,
        tag: event.target.feedcontent.value,
        image: event.target[1].value
      }),
      headers: {
        'Content-Type': 'application/json'
      },
      method: 'POST'
    })

    const result = await res.json()
    // result.user => 'Ada Lovelace'
  }

  return (
    <form onSubmit={registerUser}>
      <div>
        <label htmlFor="feedcontent">피드 내용</label>
        <textarea name="feedcontent" id="feedcontent" cols="30" rows="10" required></textarea>
      </div>
      <div>
        <label htmlFor="image">이미지 선택</label>
        <input type="file" accept="image/*" name="myImage"/>
      </div>
      <div>
        <label htmlFor="tagname">태크 이름</label>
        <input id="tagname" name="tagname" type="text" autoComplete="tagname" required />
      </div>

      <button type="submit">제출</button>
    </form>
  )
}