export default class FeedWrite {
  content: string; // 내용
  filePath: []; // 이미지, 동영상
  hashtags: []; // 해시태그
  sunin: boolean; // 선인여부

  constructor() {
    this.content = "";
    this.filePath = [];
    this.hashtags = [];
    this.sunin = false;
  }
}
