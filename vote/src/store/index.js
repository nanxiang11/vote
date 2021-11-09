import { createStore } from 'vuex'

export default createStore({
  state: {
    userinfo:{},
    imgurl:'',
  },
  mutations: {  // set
    SET_USERINFO:(state, userinfo) => {
      state.userinfo = userinfo;
      localStorage.setItem("user_info", JSON.stringify(userinfo));
    },
    SET_IMG:(state, imgurl) =>{
      state.imgurl = imgurl;
    }
  },
  getters:{

  },
  actions: {
  },
  modules: {
  }
})
