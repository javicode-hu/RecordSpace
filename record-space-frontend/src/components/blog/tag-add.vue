<template>
  <el-dialog
      title="新建标签"
      :visible="tagVisible"
      @close="handleClose"
      width="30%" append-to-body center>
    <el-form label-width="80px" :model="tagForm" ref="tagForm" :rules="rules">
      <el-form-item  label="标签名称" prop="name">
        <el-input v-model="tagForm.name" placeholder="请输入内容" ></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">新 建</el-button>
   </span>
  </el-dialog>
</template>

<script>
import ApiManager from "../../api";

export default {
  name: "TagAdd",
  props:{
    dialogVisible:Boolean,
    appendToBody:Boolean
  },
  data(){
    return{
      tagForm:{
        name:''
      },
      hasAddTag:false,
      rules:{
        name:[
          { required: true, message: '标签名称不能为空', trigger: 'blur' },
          {max: 10, message: '不能大于10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    handleClose(){
      this.$emit("changeVisible",this.hasAddTag)
    },
    resetForm(){
      this.tagForm.name=''
      this.hasAddTag = false
    },
    submitForm(){
      const _this = this
      this.$refs.tagForm.validate(valid=>{
        if(valid){
          ApiManager.addBlogTag(this.tagForm).then((res)=>{
            _this.$message.success("新建标签成功！")
            _this.hasAddTag = true
            _this.handleClose()
            _this.resetForm()
          })
        }
      })
    }
  },
  computed:{
    tagVisible(){
      return this.dialogVisible
    }

  }
}
</script>

<style scoped>

</style>