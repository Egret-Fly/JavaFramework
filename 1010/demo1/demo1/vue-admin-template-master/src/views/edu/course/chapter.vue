<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

<el-button type="text" @click="showdialogChapter()">添加章节</el-button>
    <!-- 章节 -->
    <ul class="chanpterList">
        <li
            v-for="chapter in chapterInfo"
            :key="chapter.id">
            <p>
                {{ chapter.title }}
            <span class="acts">
                <el-button style="" type="text" @click="openVideoInfo(chapter.id)">添加小节</el-button>
                <el-button style="" type="text" @click="editChpater(chapter.id)">编辑</el-button>
                <el-button type="text" @click="deleteChpater(chapter.id)">删除</el-button>
            </span>
            </p>

            <!-- 视频 -->
            <ul class="chanpterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}

                        <span class="acts">
                            <el-button style="" type="text" >编辑</el-button>
                            <el-button type="text" @click="deleteVideo(video.id)">删除</el-button>
                        </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>

    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
    </div>

    
      <!-- 添加和修改章节表单 -->
      <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
          <el-form :model="chapter" label-width="120px">
              <el-form-item label="章节标题">
                  <el-input v-model="chapter.title"/>
              </el-form-item>
              <el-form-item label="章节排序">
                  <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
              </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
          </div>
      </el-dialog>


      <!-- 添加和修改课时表单 -->
      <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
        <el-form :model="video" label-width="120px">
          <el-form-item label="课时标题">
            <el-input v-model="video.title"/>
          </el-form-item>
          <el-form-item label="课时排序">
            <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
          </el-form-item>
          <el-form-item label="是否免费">
            <el-radio-group v-model="video.free">
              <el-radio :label="true">免费</el-radio>
              <el-radio :label="false">默认</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="上传视频">
                  <el-upload
                    :on-success="handleVodUploadSuccess"
                    :on-remove="handleVodRemove"
                    :before-remove="beforeVodRemove"
                    :on-exceed="handleUploadExceed"
                    :file-list="fileList"
                    :action="BASE_API+'/eduvod/video/uploadAliyunVideo'"
                    :limit="1"
                    class="upload-demo">
              <el-button size="small" type="primary">上传视频</el-button>
              <el-tooltip placement="right-end">
                  <div slot="content">最大支持1G，<br>
                      支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                      GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                      MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                      SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
                  <i class="el-icon-question"/>
              </el-tooltip>
              </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
          <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
        </div>
      </el-dialog>
  </div>

  
</template>
<script>
import chapter from '@/api/edu/chapter';
import video from '@/api/edu/video'


export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      chapterInfo:[],
      courseId:'',
      dialogChapterFormVisible:false,
      chapter:{
        sort:'',
        title:'',
        videoSourceId: '',
        videoOriginalName:''//视频名称
      },
      dialogVideoFormVisible:false,
      video:{
        title:'',
        sort:'',
        free:'',
        videoSourceId:'',
        videoOriginalName:''
      },
      fileList: [],//上传文件列表
      BASE_API: process.env.BASE_API // 接口API地址
    }
  },

  created() { 
    if (this.$route.params && this.$route.params.id){
          
         this.courseId = this.$route.params.id;         
         this.showChapterInfo(this.courseId)

    }
    
  },

  methods: {
    //点击x调用这个方法
    beforeVodRemove(file,fileList){
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    //点击确定调用的方法
    handleVodRemove(){
      //调用接口删除视频的方法
      video.deleteAliyunVideo(this.video.videoSourceId)
          .then(response => {
            //返回信息
              this.$message({
                type: 'success',
                message: '删除视频成功!'
              });
              //把文件列表清空
              this.fileList = []
              this.video.videoSourceId = '';
              this.video.videoOriginalName = ''
          })
    },
    handleVodUploadSuccess(response, file, fileList){
           this.video.videoSourceId = response.data.videoId
           this.video.videoOriginalName = file.name
    },
    handleUploadExceed(){
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
 
    //=============小节===============

    openVideoInfo(chapterId){
      
        this.dialogVideoFormVisible = true;
 
        this.video.sort=''
        this.video.title=''
        this.video.free=''
        //清空
        this.video = {}
        this.video.chapterId = chapterId
        this.fileList = []
    },
    saveOrUpdateVideo(){
      this.video.courseId = this.courseId
      video.addVideoInfo(this.video)
          .then(response=>{
            //存储数据          
            //关闭弹窗
            this.dialogVideoFormVisible = false
            //返回信息
            this.$message({
              type: 'success',
              message: '添加小节成功!'
            });

            //查询章节数据
            this.showChapterInfo()
          })
    },
    deleteVideo(videoId){
      this.$confirm('此操作将删除该小节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {  
        //调用删除方法                                               
         video.deleteVideoInfo(videoId)
            .then(response => {//删除成功
                //提示信息
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              //查询章节数据
              this.showChapterInfo()
            })

      })
    },
  //=============章节===============
    showdialogChapter(){
      this.dialogChapterFormVisible=true;
      this.chapter.sort=''
      this.chapter.title=''
    },
    addChpater(){
      this.chapter.courseId = this.courseId 
      chapter.addChpaterInfo(this.chapter)
        .then(response => {
          //存储数据
          
          //关闭弹窗
          this.dialogChapterFormVisible = false
          //返回信息
          this.$message({
            type: 'success',
            message: '添加章节成功!'
          });

          //查询章节数据
          this.showChapterInfo()
        })
    },
    editChpater(chapterId){
        chapter.getOneChapter(chapterId)
            .then(response => {

              this.chapter = response.data.chapter
              this.dialogChapterFormVisible=true
            })
    },
    updateChapter(){
        chapter.updateChpaterInfo(this.chapter)
          .then(response => {
                //关闭弹窗
                this.dialogChapterFormVisible = false
                //返回信息
                this.$message({
                  type: 'success',
                  message: '修改章节信息成功!'
                });

                //查询章节数据
                this.showChapterInfo()
          })
    },
    deleteChpater(chapterId){
      this.$confirm('此操作将删除该章节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {  
        //调用删除方法                                               
         chapter.deleteChpaterInfo(chapterId)
            .then(response => {//删除成功
                //提示信息
              this.$message({
                type: 'success',
                message: '删除成功!'
              });

                 //查询章节数据
                this.showChapterInfo()
            })

      })
    },
    saveOrUpdate(){
      if(!this.chapter.id) {
          this.addChpater() 
      }else{
          this.updateChapter() 
      }        
      
    },
    showChapterInfo(){
        chapter.findChapterInfo(this.courseId)
          .then(response => {
              this.chapterInfo = response.data.items
          })
    },
    previous(){
        this.$router.push({ path:'/course/info/'+this.courseId})
    },

    next() {
      this.$router.push({ path: '/course/publish/'+this.courseId})
    }
  }
}
</script>

<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>