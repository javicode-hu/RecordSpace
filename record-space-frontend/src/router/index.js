import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../Home";
import LoginIn from "../views/login-in"
import BlogArticle from "../views/blog/blog-article"
import ArticleWrite from "../views/blog/article-write"
import BlogColumn from "../views/blog/blog-column"
import BlogTag from "../views/blog/blog-tag"
import ResourceWeb from "../views/resource/resource-web"
import ResourceDoc from "../views/resource/resource-doc"
import ResourceImg from "../views/resource/resource-img"
import ArticleManager from "../views/manager/article-manager"
import ColumnManager from "../views/manager/column-manager"
import TagManager from "../views/manager/tag-manager"
import ProfileManager from "../views/manager/profile-manager"
import AccountManager from "../views/manager/account-manager"
import ArticleView from "../views/blog/article-view"
import ColumnView from  "../views/blog/column-view"
import TagView from "../views/blog/tag-view"

Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '',
    component:Home,
    children:[
      {
        path: "/",
        component:BlogArticle
      },
      {
        path: "/blog/article",
        component: BlogArticle
      },{
        path: "/blog/article/write",
        component: ArticleWrite
      },{
        path: "/blog/article/view/:id",
        name:'ArticleView',
        component: ArticleView
      }
      ,{
        path: "/blog/column",
        component: BlogColumn
      },{
        path: "/blog/column/view/:id",
        name: "ColumnView",
        component: ColumnView
      },
      {
        path: "/blog/tag",
        component: BlogTag
      },
      {
        path: "/blog/tag/view/:id",
        name: "TagView",
        component: TagView
      },
      {
        path: "/resource/web",
        component: ResourceWeb
      },{
        path: "/resource/doc",
        component: ResourceDoc
      },{
        path: "/resource/img",
        component: ResourceImg
      },{
        path: "/manager",
        component: ArticleManager,
      }, {
        path:"/manager/article",
        component:ArticleManager
      },{
        path: "/manager/column",
        component: ColumnManager
      },{
        path: "/manager/tag",
        component: TagManager
      },{
        path: "/manager/profile",
        component: ProfileManager
      },{
        path: "/manager/account",
        component: AccountManager
      }
    ]
  },{
    path: "/login-in",
    component:LoginIn
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})




export default router
