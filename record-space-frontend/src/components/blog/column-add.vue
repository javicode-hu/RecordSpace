<template>
  <el-dialog
      title="新建专栏"
      :visible="columnVisible"
      @close="handleClose"
      width="30%" append-to-body center>
    <el-form label-width="80px" :rules="rules" ref="columnForm" :model="columnForm">
      <el-form-item  label="专栏名称" prop="name">
        <el-input v-model="columnForm.name" placeholder="请输入内容" ></el-input>
      </el-form-item>

      <el-form-item  label="专栏简介" >
        <el-input v-model="columnForm.description" placeholder="请输入内容" type="textarea" :rows="2"></el-input>
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
  name: "ColumnAdd",
  props:{
    dialogVisible:Boolean,
    appendToBody:Boolean
  },
  data(){
    return{
      columnForm:{
        name:'',
        description:''
      },
      hasAdd:false,
      rules:{
        name: [
          {required: true, message: '专栏名称不能为空', trigger: 'blur' },
          {max: 20, message: '不能大于20个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    handleClose(){
      this.$emit("changeVisible",this.hasAdd)
    },
    resetForm(){
      this.columnForm.name=''
      this.columnForm.description=''
      this.hasAdd = false
    },
    submitForm(){
      const _this = this
      this.$refs.columnForm.validate((valid)=>{
        if(valid){
          ApiManager.addBlogColumn(this.columnForm).then((res)=>{
            _this.$message.success("新建专栏成功！")
            _this.hasAdd = true
            _this.handleClose()
            _this.resetForm()
          })
        }
      })

    }
  },
  computed:{
    columnVisible(){
      return this.dialogVisible
    }

  }
}
</script>

<style scoped>

</style>