import { Button, Form, Input, TextArea, Grid, Image } from 'semantic-ui-react'
import { useState } from "react";
import Navbar from '../../src/component/Navbar';
import styles from '../../styles/CreateFeed.module.css'

export default function Createfeed() {
  const [content, setContent] = useState('');
  const [tag, setTag] = useState('');
  const [image, setImage]: any = useState();
  const [createObjectURL, setCreateObjectURL] = useState(null);

  const updateContent = (event: any) => {
    setContent(event.target.value)
  }

  const updateTag = (event: any) => {
    setTag(event.target.value)
  }

  const uploadToClient = (event: any) => {
    if (event.target.files && event.target.files[0]) {
      const i = event.target.files[0];

      setImage(i);
      setCreateObjectURL(URL.createObjectURL(i));
    }
  };

  const uploadToServer = async (event: any) => {
    const body = new FormData();
    body.append("file", image);
    body.append("text", content);
    body.append("text", tag);
    const response = await fetch("/api/testpage", {
      method: "POST",
      body
    });
    document.getElementsByTagName('textarea')[0].value = ''
    document.getElementsByTagName('input')[0].value = ''
    document.getElementsByTagName('input')[1].value = ''
    setContent('')
    setTag('')
    setImage(null)
  };
  
  return (
    <>
      <Navbar />
      <Grid>
        <Grid.Row>
          <Grid.Column width={3}>
          </Grid.Column>
          <Grid.Column width={10}>
            <Form className={ styles.form }>
              <br /><br />
              <label htmlFor="feedcontent"><b>피드 내용</b></label>
              <textarea 
                name="feedcontent" 
                id="feedcontent" 
                cols={30} 
                rows={10} 
                placeholder='내용을 작성하세요...'
                onChange={updateContent}
              ></textarea>
              <div>
              <img src={createObjectURL} width="500px" />
              <h4>이미지 선택</h4>
              <input type="file" accept="image/*" name="myImage" onChange={uploadToClient} />
            </div>
              <br /><br /><br />
              <Form.Field
                  control={Input}
                  label='Tag'
                  placeholder='원하는 태그를 작성하세요...'
                  onChange={updateTag}
                />
              <br /><br />
              <Form.Field control={Button} onClick={uploadToServer}>저장하기</Form.Field>
            </Form>
          </Grid.Column>
          <Grid.Column width={3}>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      
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

