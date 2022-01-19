import Navbar from "../src/component/Navbar";
import { Button, Form, Input, TextArea, } from 'semantic-ui-react'
import { useState } from "react";


export default function Createfeed() {
  const [content, setContent] = useState('');
  const [img, setImg] = useState('');
  const [tag, setTag] = useState('');

  const [image, setImage] = useState(null);
  const [createObjectURL, setCreateObjectURL] = useState(null);

  const uploadToClient = (event) => {
    if (event.target.files && event.target.files[0]) {
      const i = event.target.files[0];

      setImage(i);
      setCreateObjectURL(URL.createObjectURL(i));
      console.log(i)
    }
  };

  const uploadToServer = async (event) => {
    const body = new FormData();
    body.append("file", image);
    console.log(body)
    // const response = await fetch("/api/file", {
    //   method: "POST",
    //   body
    // });
  };

  // const reader = new FileReader()


  function submitfeed(){
    setContent(document.getElementsByTagName('textarea')[0].value)
    setImg(document.getElementsByTagName('input')[0].value)
    setTag(document.getElementsByTagName('input')[1].value)
    // console.log(document.getElementsByTagName('input'))
    console.log(content, image, tag)
    document.getElementsByTagName('textarea')[0].value = ''
    document.getElementsByTagName('input')[0].value = ''
    document.getElementsByTagName('input')[1].value = ''
  }
  
  return (
    <>
      <Navbar />   
      <Form>
        <Form.Field
          control={TextArea}
          label='피드 내용'
          placeholder='내용을 작성하세요...'
        />
        <div>
        <img src={createObjectURL} />
        <h4>이미지 선택</h4>
        <input type="file" accept="image/*" name="myImage" onChange={uploadToClient} />
        <button
          className="btn btn-primary"
          type="submit"
          onClick={uploadToServer}
        >
          Send to server
        </button>
      </div>
        {/* <Form.Field
            control={Input}
            label='이미지 선택'
            placeholder='원하는 이미지를 입력하세요...'
            type="file"
            accept="image/*"
          /> */}
        <Form.Field
            control={Input}
            label='Tag'
            placeholder='원하는 태그를 작성하세요...'
          />
        <Form.Field control={Button} onClick={submitfeed}>저장하기</Form.Field>
      </Form>
    </>
  );
}


// function PrivatePage(props) {
//   const [image, setImage] = useState(null);
//   const [createObjectURL, setCreateObjectURL] = useState(null);

//   const uploadToClient = (event) => {
//     if (event.target.files && event.target.files[0]) {
//       const i = event.target.files[0];

//       setImage(i);
//       setCreateObjectURL(URL.createObjectURL(i));
//       console.log(i)
//     }
//   };

//   const uploadToServer = async (event) => {
//     const body = new FormData();
//     body.append("file", image);
//     const response = await fetch("/api/file", {
//       method: "POST",
//       body
//     });
//   };

//   return (
//     <div>
//       <div>
//         <img src={createObjectURL} />
//         <h4>Select Image</h4>
//         <input type="file" name="myImage" onChange={uploadToClient} />
//         <button
//           className="btn btn-primary"
//           type="submit"
//           // onClick={uploadToServer}
//         >
//           Send to server
//         </button>
//       </div>
//     </div>
//   );
// }

