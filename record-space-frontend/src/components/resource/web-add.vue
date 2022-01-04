<template>
  <div>
    <el-button type="primary" icon="el-icon-edit" size="medium" @click="webAddVisible=true">新建</el-button>
    <el-dialog  title="新建网站资源"
                :visible="webAddVisible"
                @close="webAddVisible=false"
                width="40%" center>
      <el-form  ref="webAddForm" :model="webAddForm" :rules="rules" label-width="80px" class="rs-web-add-form">
        <el-form-item  label="网站名称" prop="name">
          <el-input v-model="webAddForm.name" placeholder="请输入名称" clearable></el-input>
        </el-form-item>

        <el-form-item  label="网站链接" prop="url">
          <el-input v-model="webAddForm.url" placeholder="请输入名称" clearable></el-input>
        </el-form-item>

        <el-form-item  label="网站简介" >
          <el-input v-model="webAddForm.description" placeholder="请用一句话概括网站" type="textarea" :rows="2" ></el-input>
        </el-form-item>

        <el-form-item label="网站分类" prop="categoryId">
          <el-cascader :options="categoryTree"  :props="defaultProps" v-model="webAddForm.categoryId" clearable></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="webAddVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>

    </el-dialog>
  </div>
</template>

<script>
import ApiManager from "../../api";

export default {
  name: "web-add",
  props:{
    curCategoryId:Number
  },
  watch:{
    curCategoryId:{
      deep:true,
      handler: function (newVal,oldVal){
        this.webAddForm.categoryId = newVal
      }
    }
  },
  data(){
    return{
      webAddVisible:false,
      webAddForm:{
        name:'',
        url:'',
        categoryId:'',
        description:'',
        type:'web',
      },
      rules:{
        name:[
          {required: true, message: '网站名称不能为空', trigger: 'blur' },
          {max: 20, message: '不能大于20个字符', trigger: 'blur'}
        ],
        url:[
          {required: true, message: '网站链接不能为空',trigger: 'blur' },
          {type:'url',message: "请输入链接格式",trigger: 'blur'}
        ],
        categoryId:[
          {type: 'number', required:true,message: '网站分类不能为空',trigger: 'blur' },
        ],
      },
      categoryTree:[],

      defaultProps:{
        checkStrictly: true,
        label:'name',
        children:'children',
        value:'id',
        emitPath:false
      }
    }
  },
  methods:{
    getCategoryTree() {
      ApiManager.getCategoryTreeByType(this.webAddForm.type).then(res=>{
        this.categoryTree = res.data
      })
    },
    submitForm(){
      const _this = this
      this.$refs.webAddForm.validate(valid=>{
          if(valid){
            ApiManager.addResource(this.webAddForm).then(res=>{
              _this.$message.success("新建网站资源成功！")
              _this.webAddVisible = false
              _this.$emit("contentUpdated")
            })
          }
      })
    }
  },
  created() {
    this.getCategoryTree()
  }
}
</script>

<style scoped>
.rs-web-add-form .el-form-item{
  margin-bottom: 22px;
  width: 100%;
}
.rs-web-add-category-add{
  margin-left: 10px;
}
</style>
<style>
.rs-web-add-form .el-form-item__content{
  width: 60%;
}
.rs-web-add-form .el-cascader{
  width: 100%;
}

</style>