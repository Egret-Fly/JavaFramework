import request from '@/utils/request'
export default {
    //查询课程名师
  sendCode(phone) {
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
  }
}