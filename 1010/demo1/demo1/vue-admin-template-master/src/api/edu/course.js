import request from '@/utils/request'

export default{


    findAllCourseByPage(page,limit,courseQuery){
        return request({
            url:`/eduService/edu-course/pageCourseCondition/${page}/${limit}`,
            method:'post',
            data:courseQuery
        })
    },

    //1 课程列表（条件查询分页）
    addCourseInfo(courseInfo) {
             return request({
             //url: '/table/list',
             url:`/eduService/edu-course/saveCourse`,
             method: 'post',
             data:courseInfo
           })   
    },
    findTeachers(){
            return request({
                url:`/eduService/edu-teacher/findAll`,
                method:'get',
            })
    },
    showCourseInfo(courseId){
        return request({
            url:`/eduService/edu-course/getCourseInfo/${courseId}`,
            method:'get',
        })
    },
    updateCourseInfo(courseInfo){
        return request({
            url:`/eduService/edu-course/updateCourseInfo`,
            method:'post',
            data:courseInfo
        })
    },
    showPublishCourseInfo(courseId){
        return request({
            url:`/eduService/edu-course/getPublishCourseInfo/${courseId}`,
            method:'get',
        })
    },
    
    publishCourse(courseId){
        return request({
            url:`/eduService/edu-course/publishCourse/${courseId}`,
            method:'get',
        })
    },
    deleteCourse(courseId){
        return request({
            url:`/eduService/edu-course/${courseId}`,
            method:'delete',
        })
    }
 } 