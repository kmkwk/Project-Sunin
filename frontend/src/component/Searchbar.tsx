import { useRouter } from "next/router"
import { useState } from "react"
import { Dropdown, Input } from "semantic-ui-react"

export default function Search(){

  // const option = [
  //   { key: '1', text:'피드 검색', value:'피드 검색'}, 
  //   { key: '2', text:'태그 검색', value:'태그 검색'}, 
  //   { key: '3', text:'유저 검색', value:'유저 검색'}, 
  // ]
  // const [searchRange, setSearchRange] = useState('피드 검색')

  // function changeSearchRange(e: any){
  //   setSearchRange(e.target.outerText)
  // }

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

  return(
    <>
      {/* <Dropdown placeholder='State' search selection options={option} defaultValue="피드 검색" onChange={changeSearchRange}/> */}
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
    </>
  );
}