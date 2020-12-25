import request from '@/utils/request'

export default{


    addVideoInfo(video) {
        return request({
           //url: '/table/list',
           url:'/eduService/edu-video/addVideo',
           method:'post',
           data:video
      })
    },
    updateVideoInfo(video) {
        return request({
           //url: '/table/list',
           url:'/eduService/edu-video/updateVideo',
           method:'post',
           data:video
      })
    },
    deleteVideoInfo(videoId) {
        return request({
           //url: '/table/list',
           url:`/eduService/edu-video/deleteVideo/${videoId}`,
           method:'delete',
      })
    },
    //删除阿里云视频
    deleteAliyunVideo(id) {
      return request({
         //url: '/table/list',
         url:`/eduvod/video/removeAlyVideo/${id}`,
         method:'delete',
    })
  },
}