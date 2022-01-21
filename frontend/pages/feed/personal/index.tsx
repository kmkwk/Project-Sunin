import Navbar from '../../../src/component/Navbar';
import Menubar from '../../../src/component/Menubar';
import { Grid, Image } from 'semantic-ui-react'
import Axios from "axios"
import { useEffect, useState } from 'react';
import FeedList from '../../../src/component/FeedList';
import Link from 'next/link';

export default function personal() {
  const [list, setList] = useState([]);

  const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  function getData() {
    Axios.get(API_URL)
    .then(res => {
      console.log(res.data)
      setList(res.data)
    })
  }

  useEffect(()=>{
    getData();
  }, []);

  return (
    <>
      <Navbar />
      <Grid columns={2} padded stackable>
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={13}>
          <h1>this page is personal</h1>
          <FeedList list={list} />
        </Grid.Column>
      </Grid>     
    </>
  );
}