import { Menu } from 'semantic-ui-react'
import Link from 'next/link'
import styles from './Nav.module.css'

// import React, { Component } from 'react'
// import { Input, Menu } from 'semantic-ui-react'
// import Link from 'next/link'

// export default class MenuExampleSecondary extends Component {
//   state = { activeItem: 'home' }

//   handleItemClick = (e, { name }) => this.setState({ activeItem: name })

//   render() {
//     const { activeItem } = this.state

//     return (
//       <Menu secondary>
//         <Menu.Item
//           name='home'
//           active={activeItem === 'home'}
//           onClick={this.handleItemClick}
//         />
//         <Menu.Item
//           name='personal'
//           active={activeItem === 'personal'}
//           onClick={this.handleItemClick}
//         />
//         <Menu.Item
//           name='company'
//           active={activeItem === 'company'}
//           onClick={this.handleItemClick}
//         />
//         <Menu.Item
//           name='profile'
//           active={activeItem === 'profile'}
//           onClick={this.handleItemClick}
//         />
//         <Menu.Menu position='right'>
//           <Menu.Item>
//             <Input icon='search' placeholder='Search...' />
//           </Menu.Item>
//           <Menu.Item
//             name='login'
//             active={activeItem === 'login'}
//             onClick={this.handleItemClick}
//           />
//           <Menu.Item
//             name='signin'
//             active={activeItem === 'signin'}
//             onClick={this.handleItemClick}
//           />
//         </Menu.Menu>
//       </Menu>
//     )
//   }
// }

export default function Navbar() {

  return (
    <>
      <Menu stackable>
        <Menu.Item>
        {/* <img src='/images/로고.jpg' /> */}
        로고자리
        {/* <Link href="/"><a><img className={ styles.logo_size } src='/images/로고.jpg' /></a></Link> */}
        </Menu.Item>

        <Menu.Item
          name='home'
        >
          <Link href="/"><a>Home</a></Link>
        </Menu.Item>

        <Menu.Item
          name='personal'
        >
          <Link href="/Personal"><a>Perosnal</a></Link>
        </Menu.Item>

        <Menu.Item
          name='company'
        >
          <Link href="/Company"><a>Company</a></Link>
        </Menu.Item>
        <Menu.Item
          name='login'
        >
          <Link href="/Login"><a>Login</a></Link>
        </Menu.Item>
        <Menu.Item
          name='signup'
        >
          <Link href="/Signup"><a>Signup</a></Link>
        </Menu.Item>
      </Menu>
    </>
  );
}