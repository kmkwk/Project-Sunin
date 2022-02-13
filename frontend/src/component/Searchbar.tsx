import Link from "next/link"
import { useRouter } from "next/router"
import { useEffect, useState } from "react"
import { Dropdown, Input, Search } from "semantic-ui-react"
import allAxios from '../lib/allAxios'

export default function Searchbar(){

  const [feedList, setFeedList]: any = useState([])

  // useEffect(() => {
  //   // loadFeed()
  // }, [])


  // function loadFeed() {
  //   allAxios
  //     .get(`/feed/latest`, {
  //       params: {
  //         size: 9,
  //       },
  //     })
  //     .then(({ data }) => {
  //       data.map((feed: any) => {
  //         feedList.push({key: feed.createdDate, title: feed.content.slice(0, 15), id: feed.id})
  //       })
  //     });
  //   }
  
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
    // setSearchValue(e.target.value)
    if (e.keyCode === 13){
      allAxios
      .get(`/feed/latest`, {
        params: {
          size: 9,
        },
      })
      .then(({ data }) => {
        let newList: any = []
        data.map((feed: any) => {
          if (feed.content.indexOf(e.target.value) != '-1'){
            newList.push({key: feed.createdDate, title: feed.content.slice(0, 20), id: feed.id})
          }
        })
        setFeedList(newList)
      });
    }
  }
  function goReloading(){
    router.reload()
  }

  function getSelectedValue(e: any){
    if (e.type === "click") {
      // console.log('클릭 선택', e.target.outerText)
      feedList.filter((feed: any) => {
        if (feed.title === e.target.outerText) {
          router.push(`/feed/personal/${feed.id}`)
          setTimeout(goReloading, 500)
        }
      })
    } else {
      // console.log('엔터 선택', e.target.value)
      feedList.filter((feed: any) => {
        if (feed.title === e.target.value) {
          router.push(`/feed/personal/${feed.id}`)
          setTimeout(goReloading, 500)
        }
      })
    }
  }

  return(
    <>
      {/* <Dropdown placeholder='State' search selection options={option} defaultValue="피드 검색" onChange={changeSearchRange}/> */}
      {/* <Input
            action={{ type: 'submit', content: '검색', onClick:goSearchedPage}}
            placeholder='피드 아이디를 입력하세요!!!'
            onKeyUp={getSearchValue}
          /> */}
      <Search
        onResultSelect={getSelectedValue}
        results={feedList}
        onKeyUp={getSearchValue}
        placeholder="#tag 입력 후 enter"
        name = 'searchInput'
      />
    </>
  );
}