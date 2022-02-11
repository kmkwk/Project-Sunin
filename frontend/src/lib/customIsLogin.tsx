
var isLogin = false

if (typeof window !== "undefined") {
  const token = localStorage.getItem('token')
  if (token){
    isLogin = true
  } else {
    isLogin = false
  }
}

const IsLogin = isLogin

export default IsLogin