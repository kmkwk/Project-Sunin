export default class User {
  userSeq: number; // 기본키
  userId: string; // 아이디
  username: string; // 이름
  email: string; // 이메일
  follower: []; // 팔로워
  userNickname: string; // 닉네임
  profileImageUrl: string; // 프로필사진
  providerType: string; // 제공자(GOOGLE, KAKAO, NAVER)
  roleType: string; // ENUM("ADMIN", "USER")
  suninDays: number; // 적립된 선인

  constructor(data: any) {
    this.userSeq = data.userSeq || -1;
    this.userId = data.userId || "";
    this.username = data.username || "";
    this.email = data.email || "";
    this.follower = data.follwer || [];
    this.userNickname = data.userNickname || "";
    this.profileImageUrl = data.profileImageUrl || "";
    this.providerType = data.providerType || "";
    this.roleType = data.roleType || "USER";
    this.suninDays = data.suninDays || 0;
  }
}
