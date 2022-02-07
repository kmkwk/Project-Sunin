import { Menu, Input, Search } from 'semantic-ui-react'
import Link from 'next/link'
import styles from '../../styles/Navbar.module.css'
import { useState } from 'react';
import { useRouter } from 'next/router';


export default function Navbar() {

  const [searchValue, setSearchValue] = useState('')
  const router = useRouter()

  function goSearchedPage(e: any){
    router.push(`/feed/company/${searchValue}`)
  }

  function getSearchValue(e: any){
    setSearchValue(e.target.value)
    if (e.keyCode === 13){
      router.push(`/feed/company/${searchValue}`)
    }
  }

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
        <Menu.Item
          name='login'
        >
          <Link href="/login"><a>Login</a></Link>
        </Menu.Item>
        <Menu.Item
          name='signup'
        >
          <Link href="/signup"><a>Signup</a></Link>
        </Menu.Item>
        <Menu.Item position='right'>
          <Input
            action={{ type: 'submit', content: '검색', onClick:goSearchedPage}}
            placeholder='피드 아이디를 입력하세요!!!'
            onKeyUp={getSearchValue}
          />
          {/* <Search
            onResultSelect={getSearchValue}
            results={[{title: '123', description: '123efef', price: '2000'}]}
            onKeyUp={getSearchValue}
          /> */}
        </Menu.Item>
        <Menu.Item>

        </Menu.Item>
      </Menu>
    </>
  );
}