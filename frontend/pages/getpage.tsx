import axios from "axios";

export default function GetPage() {
  axios.get('http://localhost:8080/api/v1/users', {
    headers: {
      //"Content-type": "application/json",
      token: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMTAxMDIxNDY4Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY0NDIzNTc3NH0.RE755zYlklG9lmicKeVCi3foEgCHxvPHQSRHLFX0y7I'
    }
  }).then((res) => {
    console.log(res)
  })


  return (
    <>

    </>
  );
}