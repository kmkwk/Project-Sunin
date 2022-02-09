import { useEffect, useState } from "react";
import http from "../../src/lib/customAxios";

export default function Personal() {
  useEffect(() => {
    const token = localStorage.getItem("token");
    http
      .get(`/api/v1/users`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((data: any) => {
        console.log(data);
      });
  }, []);

  return <></>;
}
