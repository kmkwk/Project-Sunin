import type { NextPage } from "next";
// import Head from 'next/head'
// import Image from 'next/image'
import styles from "styles/mainpage.module.css";
import Navbar from "../src/component/Navbar";
import { useEffect, useState } from "react";
// import { Container, Grid, Image } from "semantic-ui-react";
import { Grid, Image } from "semantic-ui-react";

import _ from "lodash";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";
import SwiperMedia from "src/component/SwiperMain";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";
import GetUserData from "src/lib/getUserData";

const Home: NextPage = () => {
  const [list, setList]: any = useState([
    "images/1번.jpg",
    "images/2번.png",
    "images/3번.jpg",
  ]);

  return (
    <>
      <Navbar />

      <SwiperMedia media={list} />

      <body>
        <div className={styles.mainzero}>
          <Grid columns={1}>
            <Grid.Row>
              <Grid.Column>
                <Image src="" />
              </Grid.Column>
              <Grid.Column>
                {/* <br /><br /> */}
                <br/>
                선한 영향력 프로젝트 썬인🌍🌞입니다.
                <br /><br />
                우리 함께 SunIn해봐요!
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </div>
        {/* <div className={styles.container}> */}
        <div className={styles.mainfirst}>
           
          <br />
          {/* <br /> */}
 
          <div  className={styles.title}>
          선한 영향력을 행사하셨나요?
          </div>
          <></>
          <br />
          <div  className={styles.content}>
          자신의 피드에 사진, 태그와 함께
          <br/>
           피드에 기록을 남겨보세요
          </div>
        
          {/* </div> */}
        </div>
        <div className={styles.mainsecond}>
          <br />
          {/* <br /> */}
          <div  className={styles.title}>
          SunIn 아이콘을 성장시켜보세요
          </div>
        
          <br />

          <div className={styles.content}>
          썬인 활동에 따라 성장하는 아이콘을 보며 성취감을 느껴보세요
          </div>

          
        </div>
        <div className={styles.mainthird}>
          <br />
          {/* <br /> */}
          <div  className={styles.title}>
          함께 SunIn할 친구를 찾으시나요?
          </div>
          <br />
          <div  className={styles.content}>
          팔로우 및 채팅을 통해 관심있는 사람과 소통할 수 있습니다.
          </div>
        </div>
      </body>

      <footer>
      <div className={styles.footer}>
      <span className={styles.contact}>CONTACT US</span>
      <br/>
      🗺️ SunIn / 썬인
      <br/>
      광주광역시 광산구 하남산단 6번로 107
      <br/>
      ✉️ SunInOffical@gmail.com
      <br/>
      </div>
      <div className={styles.distributed}>© Distributed By SunIn</div>
      
      </footer>
    </>
  );
};

export default Home;
