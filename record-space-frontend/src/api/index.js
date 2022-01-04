import service, {get,post} from "./request"
import da from "element-ui/src/locale/lang/da";
import th from "element-ui/src/locale/lang/th";


const ApiManager = {



    // =======================> 用户 API
    //登录
    login:(data) =>service.post("/login",data),

    //登出
    logout:() => service.get("/logout"),


    // =======================> 博客 API
    //新增
    addBlogArticle:(data) =>post("/article/add",data),

    searchBlogArticleByUserId:(data)=> post("/article/searchByUserId",data),

    getBlogArticleById:(data) =>get("/article/getById/"+data),

    updateViewCount:(data) =>get("/article/updateViewCount/"+data),


    // =======================> 专栏 API
    addBlogColumn:(data) => post("/column/add",data),

    listBlogColumnByUserId:() => get("/column/listByUserId"),

    searchBlogColumnByUserId:(data) =>post("/column/searchByUserId",data),

    getBlogColumnById:(data)=>get("/column/getById/"+data),


    // // =======================> 标签 API
    addBlogTag:(data) => post("/tag/add",data),

    listBlogTagByUserId:() => get("/tag/listByUserId"),

    searchBlogTagByUserId:(data)=>post("/tag/searchByUserId",data),

    getBlogTagById:(data)=>get("/tag/getById/"+data),

    // =======================> 资源类别 API
    getCategoryTreeByType:(data)=>get("/category/tree/"+data),

    addCategory:(data)=>post("/category/add",data),

    // =======================> 资源 API
    addResource:(data) =>post("/resource/add",data),

    searchResourceByUserId:(data) =>post("/resource/searchByUserId",data),

    //公共
    getBlogAsideInfo:()=>get("/article/getBlogAsideInfo"),

    getItemList(type,data){
        switch (type) {
            case 'article': return  this.searchBlogArticleByUserId(data);
            case 'column': return  this.searchBlogColumnByUserId(data);
            case 'tag': return  this.searchBlogTagByUserId(data);
            case 'web': return this.searchResourceByUserId(data);
        }
    },

}


export default ApiManager


