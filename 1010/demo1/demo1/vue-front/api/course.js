import request from '@/utils/request'
export default {
    //查询课程名师
  getCourseList(page,limit,searchObj) {
    return request({
      url: `/eduservice/Coursefront/getCourseFront/${page}/${limit}`,
      method: 'post',
      data:searchObj
    })
  },
  //查询所有分类方法
  getAllSubject(){
    return request({
      url:`/eduService/edu-subject/getAllSubject`,
      method:`get`
    })
  },

  //课程详情方法
  getCourseInfo(courseId){
    return request({
      url:`/eduservice/Coursefront/getFrontCourseInfo/${courseId}`,
      method:`get`
    })
  }


}