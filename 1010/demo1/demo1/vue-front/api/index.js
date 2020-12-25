import request from '@/utils/request'
export default {
    //查询课程名师
  getHotTeacherAndCourse() {
    return request({
      url: `/eduservice/indexfront/index`,
      method: 'get'
    })
  }
}