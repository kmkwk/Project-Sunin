import { useEffect } from "react";
import allAxios from "../../src/lib/allAxios";
import userAxios from "../../src/lib/userAxios";

export default function Personal() {
  useEffect(() => {
    allAxios
      .get(`/api/v1/users`)
      .then((data: any) => {
        console.log(data);
      })
      .catch(() => {
        console.log("로그인 실패");
      });

    userAxios
      .get(`/api/v1/users`)
      .then((data: any) => {
        console.log(data);
      })
      .catch(() => {
        console.log("로그인 실패");
      });
  }, []);

  return <></>;
}
