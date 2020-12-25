import request from '@/utils/request'
export default {
    //查询课程名师
  getTeacherList(page,limit) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFront/${page}/${limit}`,
      method: 'get'
    })
  },
  //讲师详情的方法
  getTeacherInfo(teacherId) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${teacherId}`,
      method: 'get'
    })
  },

}