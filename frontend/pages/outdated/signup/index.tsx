import React from "react";
import Link from "next/link";
import { Divider, Grid, Image, Segment, Button } from "semantic-ui-react";
import styles from "styles/signup.module.css";
import Navbar from "src/component/Navbar";

const DividerExampleVertical = () => (
  <>
    <Navbar />
    <Segment placeholder>
      <Grid columns={2} relaxed="very" stackable>
        <Grid.Column>
          <Image src="../images/로고.png" size="huge" />
        </Grid.Column>

        <Grid.Column>
          <div className={styles.login_content}>
            <h3>가입 유형을 선택하세요</h3>
            <div className={styles.box_align}>
              <Button.Group widths="2" size="large">
                <Link href="signup/person">
                  <Button>
                    <br />
                    개인회원
                    <br />
                    <br />
                  </Button>
                </Link>
                <Link href="signup/company">
                  <Button>
                    <br />
                    기업회원
                    <br />
                    <br />
                  </Button>
                </Link>
              </Button.Group>
            </div>
            <br />
            {/* <Button fluid basic color='black' content='Next' /> */}
          </div>
        </Grid.Column>
      </Grid>
    </Segment>
  </>
);

export default DividerExampleVertical;
