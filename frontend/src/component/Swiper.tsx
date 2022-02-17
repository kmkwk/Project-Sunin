import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";
import { Image } from "semantic-ui-react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

const SwiperMedia = ({ media }: any) => {
  SwiperCore.use([Navigation, Pagination, Autoplay]);
  return (
    <>
      <Swiper
        spaceBetween={10}
        slidesPerView={1.6}
        navigation
        speed={500}
        pagination={{ clickable: true }}
        loop={true}
        autoplay={{ delay: 3000 }}
        centeredSlides={true}>
        {media.map((item: any, index: any) => {
          return (
            <SwiperSlide key={index}>
              <Image src={item} alt={item} />
            </SwiperSlide>
          );
        })}
      </Swiper>
    </>
  );
};

export default SwiperMedia;
