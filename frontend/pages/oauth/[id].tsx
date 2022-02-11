import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Authentication() {
  const router = useRouter();

  useEffect(() => {
    console.log(router);
    router.push("http://localhost:3000/");
  }, []);

  if (typeof window !== "undefined") {
    localStorage.setItem("token", String(router.query.token));
  }

  return <></>;
}
