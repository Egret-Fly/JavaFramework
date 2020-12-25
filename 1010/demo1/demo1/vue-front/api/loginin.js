import request from '@/utils/request'
export default {
    //根据手机号发送验证码
  submitLogin(userInfo) {
    return request({
      url: `/edumsm/msm/send/${phone}`,
      method: 'get'
    })
  },

  //注册的方法
  registerMember(formItem) {
    return request({
      url: `/eduCenter/member/register`,
      method: 'post',
      data:formItem
    })
  },


    //登录的方法
    loginMember(userInfo) {
        return request({
            url: `/eduCenter/member/login`,
            method: 'post',
            data:userInfo
        })
    },

    //根据token获取用户信息
    getLoginUserInfo() {
        return request({
            url: `/eduCenter/member/getMemberInfo`,
            method: 'get',
        })
    }

  
  


}