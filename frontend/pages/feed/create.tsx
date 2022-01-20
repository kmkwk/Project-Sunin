import { Button, Form, Input, TextArea, Grid, Image } from 'semantic-ui-react'
import { useState } from "react";
import Navbar from '../../src/component/Navbar';
import styles from '../../styles/CreateFeed.module.css'

const options = [
  { key: 'angular', text: 'Angularc', value: 'angular' },
  { key: 'css', text: 'CSS', value: 'css' },
  { key: 'design', text: 'Graphic Design', value: 'design' },
  { key: 'ember', text: 'Ember', value: 'ember' },
  { key: 'html', text: 'HTML', value: 'html' },
  { key: 'ia', text: 'Information Architecture', value: 'ia' },
  { key: 'javascript', text: 'Javascript', value: 'javascript' },
  { key: 'mech', text: 'Mechanical Engineering', value: 'mech' },
  { key: 'meteor', text: 'Meteor', value: 'meteor' },
  { key: 'node', text: 'NodeJS', value: 'node' },
  { key: 'plumbing', text: 'Plumbing', value: 'plumbing' },
  { key: 'python', text: 'Python', value: 'python' },
  { key: 'rails', text: 'Rails', value: 'rails' },
  { key: 'react', text: 'React', value: 'react' },
  { key: 'repair', text: 'Kitchen Repair', value: 'repair' },
  { key: 'ruby', text: 'Ruby', value: 'ruby' },
  { key: 'ui', text: 'UI Design', value: 'ui' },
  { key: 'ux', text: 'User Experience', value: 'ux' },
]

export default function Createfeed() {
  const [content, setContent] = useState('');
  const [tag, setTag] = useState('');
  const [image, setImage] = useState(null);
  const [createObjectURL, setCreateObjectURL] = useState(null);

  const updateContent = (event) => {
    setContent(event.target.value)
  }

  const updateTag = (event) => {
    setTag(event.target.value)
    // setTag(event.target.outerText)
    // console.log(tag)
  }

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
    body.append("text", content);
    body.append("text", tag);
    console.log(body)
    const response = await fetch("/api/testpage", {
      method: "POST",
      body
    });
    console.log(content, image, tag)
    document.getElementsByTagName('textarea')[0].value = ''
    document.getElementsByTagName('input')[0].value = ''
    document.getElementsByTagName('input')[1].value = ''
    setContent('')
    setTag('')
    setImage(null)
  };

  // function submitfeed(){
  //   console.log(content, image, tag)
  //   document.getElementsByTagName('textarea')[0].value = ''
  //   document.getElementsByTagName('input')[0].value = ''
  //   document.getElementsByTagName('input')[1].value = ''
  //   setContent('')
  //   setTag([])
  //   setImage(null)
  // }
  
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
                cols="30" 
                rows="10" 
                placeholder='내용을 작성하세요...'
                onChange={updateContent}
              ></textarea>
              {/* <Form.Field
                control={TextArea}
                label='피드 내용'
                placeholder='내용을 작성하세요...'
                onChange={updateContent}
              /> */}
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
                {/* <Dropdown placeholder='Tags' fluid multiple selection options={options} onChange={updateTag} /> */}
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

