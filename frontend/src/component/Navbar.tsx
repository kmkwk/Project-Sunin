import { Menu } from 'semantic-ui-react'
import Link from 'next/link'
import styles from '../../styles/Navbar.module.css'
import Alarm from './Alarm';
import Searchbar from './Searchbar'
import IsLogin from '../lib/customIsLogin';


export default function Navbar() {

  function goLogout(){
    localStorage.removeItem('token')
  }

  const isLogin = IsLogin
  
  return (
    <>
      <Menu stackable>
        <Menu.Item>
        <Link href="/"><a><img className={ styles.logo_size } src='/images/로고center.png' /></a></Link>
        </Menu.Item>

        <Menu.Item
          name='home'
        >
          <Link href="/"><a>Home</a></Link>
        </Menu.Item>

        <Menu.Item
          name='personal'
        >
          <Link href="/feed/personal"><a>Perosnal</a></Link>
        </Menu.Item>

        <Menu.Item
          name='company'
        >
          <Link href="/feed/company"><a>Company</a></Link>
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
        <Menu.Item
          name='signup'
        >
          <Link href="/signup"><a>Signup</a></Link>
        </Menu.Item>
        }

        {isLogin?
        <Menu.Item
          name='logout'
        >
          <Link href="/"><a onClick={goLogout}>Logout</a></Link>
        </Menu.Item>
        :
        <Menu.Item
          name='login'
        >
          <Link href="/login"><a>Login</a></Link>
        </Menu.Item>
        }    
      </Menu>
    </>
  );
}