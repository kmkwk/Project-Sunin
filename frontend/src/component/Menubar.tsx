import { Header, Menu, MenuItem } from 'semantic-ui-react'
import Link from 'next/link'

export default function Menuvar(){
  return (
    <>
      <Menu vertical>
        <MenuItem>
          <p>회원프로필</p>
          <p>회원이름</p>
          <p>썬인등급</p>
          <p><Link href="/Custom"><a>회원 정보 수정 이미지</a></Link></p>
        </MenuItem>
        <Menu.Item
          name='personal'
        >
          <Header as='h4'><Link href="/Personal"><a>개인</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='company'
        >
          <Header as='h4'><Link href="/Company"><a>기업</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='rank'
        >
          <Header as='h4'><Link href="/Rank"><a>랭킹</a></Link></Header>
        </Menu.Item>

        <Menu.Item
          name='createfeed'
        >
          <Header as='h4'><Link href="/CreateFeed"><a>피드 작성하기</a></Link></Header>
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