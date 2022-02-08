import { Header, Menu, MenuItem, Icon, Divider, Grid, GridRow } from 'semantic-ui-react'
import Link from 'next/link'


export default function Menuvar(){

  var isLogin = false

  if (typeof window !== "undefined") {
    const token = localStorage.getItem('token')
    if (token){
      isLogin = true
    } else {
      isLogin = false
    }
  }

  return (
    <>
      <Menu vertical>
        <MenuItem>
          {isLogin?
          <Grid>
            <GridRow>
              <img src="/images/로고.png" alt="프로필사진" width="50px"/>
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 로그인
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 새싹
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <Link href="/info"><a><Icon name='cog' size='large' color='grey' circular/></a></Link>
            </GridRow>
          </Grid>
          :
          <Grid>
            <GridRow>
              <img src="/images/디폴트프로필사진.png" alt="프로필사진" width="50px"/>
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 회원이름
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 썬인등급
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <Link href="/info"><a><Icon name='cog' size='large' color='grey' circular/></a></Link>
            </GridRow>
          </Grid>
          }
          
          
        </MenuItem>
        <Menu.Item
          name='personal'
        >
          <Header as='h4'><Link href="/feed/personal"><a>개인</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='company'
        >
          <Header as='h4'><Link href="/feed/company"><a>기업</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='rank'
        >
          <Header as='h4'><Link href="/rank"><a>랭킹</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='createfeed'
        >
          <Header as='h4'><Link href="/feed/create"><a>피드 작성하기</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='chat'
        >
          <Header as='h4'>채팅</Header>
        </Menu.Item>
        </Menu>
    </>
  );
}