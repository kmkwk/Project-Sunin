import { useRouter } from "next/router";
import { Button } from "semantic-ui-react";

export default function Authentication(){

  const router = useRouter()

  function goMainPage(){
    router.push('/')
  }

  if (typeof window !== "undefined") { 
    localStorage.setItem('token', String(router.query.token) );
  }

  return <></>;
}

export async function getServerSideProps() {
    if (typeof window !== "undefined") {
      localStorage.setItem("token", JSON.stringify("adsf"));
    }
  
    return {
      redirect: {
        permanent: false,
        destination: "/",
      },
    };
  }