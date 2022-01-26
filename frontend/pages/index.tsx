import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import Menuvar from '../src/component/Menubar'
import Navbar from '../src/component/Navbar'
import styles from '../styles/Home.module.css'
import { Header, Menu, Grid } from 'semantic-ui-react'
import Axios from "axios"
import { useEffect, useState } from 'react';
import SampleFeedList from '../src/component/SampleFeedList'
import Searchbar from '../src/component/searchbar'

const Home: NextPage = () => {
  const [list, setList] = useState([]);

  const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  function getData() {
    Axios.get(API_URL)
    .then(res => {
      // console.log(res.data)
      setList(res.data)
    })
  }

  useEffect(()=>{
    getData();
  }, []);

  return (
    <>
      <Navbar />
      <Searchbar />
      <Grid columns={4} padded stackable>
      <Grid.Column>
        <Menuvar />
      </Grid.Column>
      <Grid.Column>
        <b>Recent</b>
        <SampleFeedList list={list.slice(0,3)} />
      </Grid.Column>
      <Grid.Column>
        <b>Personal</b>
        <SampleFeedList list={list.slice(3,6)} />
      </Grid.Column>
      <Grid.Column>
        <b>Company</b>
        <SampleFeedList list={list.slice(6,9)} />
      </Grid.Column>
    </Grid>
    </>
  )
}

export default Home
