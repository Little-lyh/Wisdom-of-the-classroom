// pages/login/login.js
const request = require('../../utils/request.js');
let time = require('../../utils/util.js');
const app = getApp();
const { $Toast } = require('../../dist/base/index');
const jinrishici = require('../../utils/jinrishici.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jinrishici:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    jinrishici.load(result => {
      // 下面是处理逻辑示例
      console.log(result.data.content)
      this.setData({
        jinrishici: result.data.content
      });
    });
  },
  Search1: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent1: content,
    });
  },
  Search2: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent2: content,
    });
  },
  LoginSubmit: function (e) {
    console.log(this.data.SearchContent1);
    console.log(this.data.SearchContent2);
    console.log(e)
    var self = this;
    var username = this.data.SearchContent1
    var pass = this.data.SearchContent2;
    if (username == '' || username == null || username.length > 45) {
      this.handleError1("用户名")
    } else if (pass == '' || pass == null || pass.length > 45) {
      this.handleError1("密码")
    } else {
      wx.request({
        url: app.globalData.url1+'StudentLoginAction', //仅为示例，并非真实的接口地址
        data: {
          x: username,
          y: pass
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          console.log(getApp())
          getApp().globalData.username = self.data.value1
          console.log(getApp().globalData.username)
          console.log(res.data)
          console.log("接受到的rsult" + res.data.res)
          if (res.data.res == true) {
            var app = getApp();
            app.globalData.username=username
            wx.navigateTo({
              url: '../index/index',
            })
          } else {
            self.handleError()
          }
        },
        fail: function (e) {
          console.log('failed')
        },
      })
    }


  },
  handleError: function () {
    $Toast({
      content: '密码错误',
      type: 'error'
    });
  },
  handleError1: function (name) {
    $Toast({
      content: name + '不能为空且必须小于45个字符',
      type: 'error'
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  showModal:function(){
    wx.navigateTo({
      url: '/pages/iindex/iindex',
    })
  }

})