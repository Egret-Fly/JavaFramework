import request from '@/utils/request'

export default{

    //1 课程列表（条件查询分页）
    getAllSubject() {
             return request({
             //url: '/table/list',
             url:`/eduService/edu-subject/getAllSubject`,
             method: 'get',
           })
          
    },
   
 } 