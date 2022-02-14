import { Menu } from "semantic-ui-react";
import Link from "next/link";
import styles from "../../styles/Navbar.module.css";
import Alarm from "./Alarm";
import Searchbar from "./Searchbar";
import IsLogin from "../lib/customIsLogin";
import { useRouter } from "next/router";
import LoginModal from "./login/loginModal";
import { createRef, useEffect, useState } from "react";
import Image from "next/image";
import userAxios from "src/lib/userAxios";
import User from "src/class/User";

export default function Navbar() {
  const contextRef = createRef();
  const router = useRouter();

  function goLogout() {
    localStorage.removeItem("token");
    router.reload();
  }

  const isLogin = IsLogin;

  const [userInfo, setUserInfo]: any = useState([])

  useEffect(() => {
    if (isLogin) {
      userAxios
      .get(`/api/v1/users`, {

      })
      .then(({ data }) => {
        setUserInfo(data.body.user)
      })
      .catch((e: any) => {
        console.log(e)
        // alert("잘못된 접근입니다.");
      });
    }
  }, [])

  return (
    <div className={styles.fixed}>
      <Menu fixed="top" color="teal" inverted secondary>
        <Menu.Item link>
          <Link href="/mainpage">
            <a>
              <Image
                src="/images/로고만.png"
                alt="logo"
                width={40}
                height={40}
              />
            </a>
          </Link>
        </Menu.Item>

        {/* <Menu.Item name="home">
          <Link href="/">
            <a>Home</a>
          </Link>
        </Menu.Item> */}

        <Menu.Item className={styles.item} link>
          <Link href="/feed/personal">
            <a>Feed</a>
          </Link>
        </Menu.Item>
        
        {userInfo.username?
        <Menu.Item className={styles.item} link>
          <Link href={`/profile/${userInfo['user_seq']}`}>
            <a>Profile</a>
          </Link>
        </Menu.Item>
        :""}

        <Menu.Item position="right">
          <Searchbar />
        </Menu.Item>
        <Menu.Item />

        {isLogin ? (
          <Menu.Item>
            <Alarm />
          </Menu.Item>
        ) : (
          <Menu.Item name="signup">
            <Link href="/signup">
              <a>Signup</a>
            </Link>
          </Menu.Item>
        )}

        {isLogin ? (
          <Menu.Item name="logout">
            <Link href="/">
              <a onClick={goLogout}>Logout</a>
            </Link>
          </Menu.Item>
        ) : (
          <Menu.Item name="login">
            {/* <Link href="/login"><a>Login</a></Link> */}
            <LoginModal />
          </Menu.Item>
        )}
      </Menu>
    </div>
  );
}
