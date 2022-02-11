import { Button, Grid, GridColumn, Header, Icon, Image, Progress } from "semantic-ui-react"
import Navbar from "../src/component/Navbar"
import Menubar from "../src/component/Menubar"
import styles from "../styles/rank.module.css"
import { useRouter } from "next/router"

export default function Rank() {

  const router = useRouter()

  function goProfile() {
    router.push('/profile/person')
  }
  return (
    <>
      <Navbar />
      <Grid>
        <Grid.Row>
        <Grid.Column width={3}>
          <Menubar />
        </Grid.Column>
        <Grid.Column width={10}>
          <br />
          <Header as='h1' content='1월 Sun in MVP' textAlign="center" color="yellow"/>
          <br />
          <div>
            <Image src='/images/goldmedal.png' size="tiny" inline/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Header as='h2' content='1등 이름' textAlign="center" className={styles.name}/>&nbsp;&nbsp;&nbsp;
            <Icon name='trophy' />
            <Button onClick={goProfile}>프로필</Button>
            <Progress progress='value' value={45} total={50} success />
          </div><br />
          <div>
            <Image src='/images/silvermedal.png' size="tiny" inline/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Header as='h2' content='2등 이름' textAlign="center" className={styles.name}/>&nbsp;&nbsp;&nbsp;
            <Icon name='trophy' />
            <Button onClick={goProfile}>프로필</Button>
            <Progress progress='value' value={30} total={50} success />
          </div><br />
          <div>
            <Image src='/images/bronzemedal.png' size="tiny" inline/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Header as='h2' content='3등 이름' textAlign="center" className={styles.name}/>&nbsp;&nbsp;&nbsp;
            <Icon name='trophy' />
            <Button onClick={goProfile}>프로필</Button>
            <Progress progress='value' value={15} total={50} success />
          </div>
        </Grid.Column>
        <GridColumn width={3}>
          <br />
          <h2>안내</h2>
          <p>* 매달 랭킹순위가 변경됩니다</p>
        </GridColumn>
      </Grid.Row>
      </Grid>
    </>
  )
}