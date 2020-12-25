import request from '@/utils/request'
export default {
    //获取视频id
getPlayAuth(id) {
    return request({
      url: `/eduvod/video/getPlayAuth/${id}`,
      method: 'get'
    })
  },

}