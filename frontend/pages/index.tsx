import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import Menuvar from '../src/component/Menubar'
import Navbar from '../src/component/Navbar'
import styles from '../styles/Home.module.css'
import { Header, Menu, Grid } from 'semantic-ui-react'

const Home: NextPage = () => {
  return (
    <>
      <Navbar />
      <Grid columns={4} padded>
      <Grid.Column>
        <Menuvar />
      </Grid.Column>
      <Grid.Column>
        Recent
      </Grid.Column>
      <Grid.Column>
        Personal
      </Grid.Column>
      <Grid.Column>
        Company
      </Grid.Column>
    </Grid>
    </>
  )
}

export default Home
