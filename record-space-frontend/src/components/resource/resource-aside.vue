<template>
  <el-card class="rs-resource-aside" shadow="hover">
    <el-row >
      <el-button class="rs-resource-aside-edit" type="text" size="median"
                 @click="categoryEditable=!categoryEditable">{{ editableWord }}</el-button>
    </el-row>
    <el-tree :data="categoryTree"
             :default-expand-all="true"
             :props="defaultProps"
             :expand-on-click-node="false"
             :highlight-current="true"
             @node-click="handleNodeClick">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span v-if="node.isCurrent && categoryEditable">
          <el-button
              type="text"
              size="mini"
              @click="() => append(data)">
            添加
          </el-button>
          <el-button
              type="text"
              size="mini"
              @click="() => remove(node, data)">
            删除
          </el-button>
        </span>
    </span>
    </el-tree>
    <el-dialog title="新增类目" :visible="categoryAddVisible" width="30%"
               @close="categoryAddVisible=false"
               center>
      <el-form :model="categoryAddForm"
               label-width="80px"
               ref="categoryAddForm"
               :rules="rules">
        <el-form-item label="类名名称" prop="name">
          <el-input v-model="categoryAddForm.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="categoryAddVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </el-dialog>
  </el-card>
</template>

<script>
import ApiManager from "../../api";
import da from "element-ui/src/locale/lang/da";

export default {
  name: "ResourceAside",
  props: {},
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      categoryTree: [],
      type: 'web',
      categoryAddVisible:false,
      categoryAddForm:{
        name:'',
        parentId:'',
        level:'',
        type:'',
      },
      rules:{
        name:[
          {required: true, message: '类目名称不能为空', trigger: 'blur' },
          {max: 10, message: '不能大于20个字符', trigger: 'blur'}
        ]
      },
      currentData:'',
      categoryEditable:false,
    };
  },
  methods: {
    getTreeChild(data, list) {
      list.push(data.id)
      for (let i = 0; i < data.children.length; i++) {
        this.getTreeChild(data.children[i], list)
      }
    },

    handleNodeClick(data, node) {
      let categoryIds = []
      this.getTreeChild(data, categoryIds)
      this.$emit("categoryClick", categoryIds)
    },
    getCategoryTree() {
      ApiManager.getCategoryTreeByType(this.type).then(res => {
        this.categoryTree = res.data
      })
    },
    append(data) {
      this.categoryAddForm.parentId = data.parentId
      this.categoryAddForm.level = data.level
      this.categoryAddForm.type = this.type
      this.categoryAddVisible=true
      this.currentData = data
    },
    remove(node, data) {

    },
    //添加类名
    submitForm(){
      const _this = this
      this.$refs.categoryAddForm.validate(valid=>{
        if(valid){
          ApiManager.addCategory(this.categoryAddForm).then(res=>{
            _this.$message.success("类目添加成功")
            _this.categoryAddVisible =false
            _this.getCategoryTree()
          })
        }
      })
    }
  },
  created() {
    this.getCategoryTree()
  },
  computed:{
    editableWord(){
        if(this.categoryEditable){
          return "关闭"
        }else {
          return "编辑"
        }
    }
  }
}
</script>

<style scoped>
.rs-resource-aside {
  position:fixed;
  margin-left: 10px;
  margin-top: 20px;
  height: 600px;
  width: 300px;
  overflow-y:auto;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.el-menu {
  border: none;

}

.rs-resource-aside-edit{
  float: right;
}

</style>