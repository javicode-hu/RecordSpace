import Vue from 'vue'
import Vuex from 'vuex'
import ApiManager from "../api";


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id:'',
    username:localStorage.getItem('username'),
    avatar:localStorage.getItem('avatar'),
    nickname:localStorage.getItem('nickname'),
    token:localStorage.getItem("token"),
    // userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: {
    SET_USERNAME: (state, username) => {
      state.username = username
      localStorage.setItem('username',username)
    },
    SET_NICKNAME: (state, nickname) => {
      state.nickname = nickname
      localStorage.setItem('nickname',nickname)
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
      localStorage.setItem('avatar',avatar)
    },
    SET_ID: (state, id) => {
      state.id = id
      localStorage.setItem('id',id)
    },
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem("token", token)
    },
    REMOVE_INFO: (state)=> {
      state.token = null
      state.id = null
      state.avatar = null
      state.username = null
      state.nickname = null
      localStorage.removeItem("token")
      localStorage.removeItem("id")
      localStorage.removeItem("username")
      localStorage.removeItem("nickname")
      localStorage.removeItem("avatar")
    }
  },
  actions: {
    login({commit},userForm){
      return new Promise((resolve, reject)=>{
          ApiManager.login(userForm).then(res=>{
            console.log(res)
            const token = res.headers['authorization']
            const userInfo = res.data.data
            commit('SET_TOKEN',token)
            commit('SET_ID',userInfo.id)
            commit('SET_USERNAME',userInfo.username)
            commit('SET_NICKNAME',userInfo.nickname)
            commit('SET_AVATAR',userInfo.avatar)
            // commit('SET_USERINFO',res.data.data)
            resolve(userInfo)
          }).catch(error=>{
            reject(error)
          })
      })
    },
    logout({commit},state){
      return new Promise((resolve, reject)=>{
        ApiManager.logout().then(res=>{
          commit("REMOVE_INFO")
          resolve()
        }).catch(error=>{
          reject(error)
        })
      })
    }
  },
  getters:{
  },
  modules: {
  }
})
