import request from '@/utils/request'

export default{

    //1 讲师列表（条件查询分页）
    getTeacherListPage(cuurrent,limit,teacherQuery) {
             return request({
             //url: '/table/list',
             url:`/eduService/edu-teacher/pageTeacherCondition/${cuurrent}/${limit}`,
             method: 'post',
             //teacherQuery条件对象，后端使用RequestBody获取数据
             //data表示把对象转换json进行传递到接口里面
             data:teacherQuery
           })
          
    },
    deleteTeacherId(id){
      return request({
        //url: '/table/list',
        url:`/eduService/edu-teacher/${id}`,
        method: 'delete',
      })
    },
    addTeacherId(teacher){
      return request({
        //url: '/table/list',
        url:`/eduService/edu-teacher/addTeacher`,
        method: 'post',
        data:teacher
      })

    },
    //根据id查询讲师
    getTeacherInfo(id){
      return request({
        //url: '/table/list',
        url:`/eduService/edu-teacher/getTeacher/${id}`,
        method: 'get'
      })
    
    },
    //修改讲师
    updateTeacher(teacher){
      return request({
        url:`/eduService/edu-teacher/updateTeacher`,
        method:'post',
        data:teacher
      })
    }
// export function getList(params) {
//   return request({
//     url: '/table/list',
//     method: 'get',
//     params
//   })
 } 