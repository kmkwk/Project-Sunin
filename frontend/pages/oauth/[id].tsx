import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Authentication() {
  const router = useRouter();

  useEffect(() => {
    console.log(router);
    router.push("/");
  }, []);

  if (typeof window !== "undefined") {
    localStorage.removeItem("token");
    localStorage.setItem("token", String(router.query.token));
  }

  return <></>;
}
