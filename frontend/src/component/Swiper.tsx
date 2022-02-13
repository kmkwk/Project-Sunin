import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";
import { Image } from "semantic-ui-react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

const SwiperMedia = ({ media }: any) => {
  SwiperCore.use([Navigation, Pagination, Autoplay]);
  console.log(media);
  return (
    <>
      <Swiper
        style={{ height: "500px" }}
        spaceBetween={200}
        slidesPerView={1}
        navigation
        pagination={{ clickable: true }}
        loop={true}
        autoplay={{ delay: 3000 }}>
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
