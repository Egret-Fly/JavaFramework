import request from '@/utils/request'

export default{

    //1 课程列表（条件查询分页）
    findChapterInfo(courseId) {
             return request({
                //url: '/table/list',
                url:'/eduService/chapter/GetChpater/'+courseId,
                method:'get',
           })   
    },
    getOneChapter(chapterId) {
        return request({
           //url: '/table/list',
           url:'/eduService/chapter/getOneChapterById/'+chapterId,
           method:'get',
      })   
    },
    addChpaterInfo(chapter) {
        return request({
           //url: '/table/list',
           url:'/eduService/chapter/addChpater',
           method:'post',
           data:chapter
      })
    },
    updateChpaterInfo(chapter) {
        return request({
           //url: '/table/list',
           url:'/eduService/chapter/updateChpater',
           method:'post',
           data:chapter
      })
    },
    deleteChpaterInfo(chapterId) {
        return request({
           //url: '/table/list',
           url:`/eduService/chapter/deleteChapter/${chapterId}`,
           method:'delete',
      })
    },
}