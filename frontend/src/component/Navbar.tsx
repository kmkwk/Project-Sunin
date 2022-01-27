import { Menu } from 'semantic-ui-react'
import Link from 'next/link'
import styles from '../../styles/Navbar.module.css'
import { useState } from 'react';


export default function Navbar() {

  return (
    <>
      <Menu stackable>
        <Menu.Item>
        <Link href="/"><a><img className={ styles.logo_size } src='/images/로고만.png' /></a></Link>
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
      </Menu>
    </>
  );
}