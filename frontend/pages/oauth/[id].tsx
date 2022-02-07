import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Authentication() {
  const router = useRouter();

  useEffect(() => {
    router.push("/");
  }, []);

  if (typeof window !== "undefined") {
    localStorage.setItem("token", String(router.query.token));
  }

  return <></>;
}
