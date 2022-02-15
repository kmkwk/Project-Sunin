import { useEffect } from "react";
import userAxios from "src/lib/userAxios";

function Test() {
  useEffect(() => {
    userAxios
      .get("/api/v1/users")
      .then(({ data }) => {
        console.log(data);
      })
      .catch(() => {
        console.warn("실패 !!");
      });
  }, []);

  return <></>;
}

export default Test;
