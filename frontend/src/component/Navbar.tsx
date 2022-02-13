import { Menu } from 'semantic-ui-react'
import Link from 'next/link'
import styles from '../../styles/Navbar.module.css'
import Alarm from './Alarm';
import Searchbar from './Searchbar'
import IsLogin from '../lib/customIsLogin';
import { useRouter } from 'next/router';
import LoginModal from './login/loginModal';


export default function Navbar() {

  const router = useRouter()

  function goLogout(){
    localStorage.removeItem('token')
    router.reload()
  }

  const isLogin = IsLogin
  
  return (
    <div>
      <Menu stackable pointing secondary>
        <Menu.Item>
          <Link href="/"><a><img className={ styles.logo_size } src='/images/로고center.png' /></a></Link>
        </Menu.Item>

        <Menu.Item
          name='home'
        >
          <Link href="/"><a className={ styles.nav_names }>Home</a></Link>
        </Menu.Item>

        <Menu.Item
          name='personal'
        >
          <Link href="/feed/personal"><a className={ styles.nav_names }>Personal</a></Link>
        </Menu.Item>

        <Menu.Item
          name='company'
        >
          <Link href="/feed/company"><a className={ styles.nav_names }>Company</a></Link>
        </Menu.Item>

        <Menu.Item position='right'>
          <Searchbar />
        </Menu.Item>
        <Menu.Item />

        {isLogin?
        <Menu.Item>
          <Alarm />
        </Menu.Item> 
        :
        // <Menu.Item
        //   name='signup'
        // >
        //   <Link href="/signup"><a  className={ styles.nav_names }>Signup</a></Link>
        // </Menu.Item>
        ""
        }

        {isLogin?
        <Menu.Item
          name='logout'
        >
          <Link href="/"><a onClick={goLogout} className={ styles.nav_logout }>Logout</a></Link>
        </Menu.Item>
        :
        <div className={ styles.nav_loginbutton }>
          <Menu.Item
           name='login' className={ styles.nav_login }
          >
          {/* <Link href="/login"><a>Login</a></Link> */}
          <LoginModal />
        </Menu.Item>
        </div>
        
        }
      </Menu> 
    </div>
  );
}