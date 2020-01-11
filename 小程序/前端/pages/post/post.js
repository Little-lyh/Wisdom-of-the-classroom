// miniprogram/pages/post/post.js
const app = getApp();
const request = require('../../utils/request.js');
let time = require('../../utils/util.js');
const { $Message } = require('../../dist/base/index');
const { $Toast } = require('../../dist/base/index');
var countdown = 60;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        StatusBar: app.globalData.StatusBar,
        CustomBar: app.globalData.CustomBar,
        Custom: app.globalData.Custom,
        skin: app.globalData.skin,
        style: app.globalData.highlightStyle,
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        CommentShow: false,
        ButtonTimer: '',//  按钮定时器
        LastTime: 60,
        CommentSwitch: true,
        current: '',
        position: 'right',
        visible1: false,
        id:''
    },

    getUserInfo: function (e) {
        app.globalData.userInfo = e.detail.userInfo;
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var postId = options.postId;
        // console.log(postId);
        this.setData({
            postId: postId
        })
        var self = this
        wx.request({
          url: app.globalData.url1 +'GetProblemListAction_getProblem', //仅为示例，并非真实的接口地址
          data: {
            x: postId,
            y: 'fdsa'
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
          },
          method: 'POST',
          success: function (res) {
            console.log(res.data.problem)
            self.setData({
              problem: res.data.problem,
              current:res.data.problem.option_A,
              id:res.data.problem.id
            })
          },
          fail: function (e) {
            console.log('failed')
          },
        })
    },
    handleFruitChange({ detail = {} }) {
      console.log(detail.value)
      this.setData({
        current: detail.value
      });
    },
    handleClick: function () {
      this.setData({
        visible1: true
      });
    },
    handleClose1() {
      this.setData({
        visible1: false
      });
    },
    handleClose2() {
      this.setData({
        visible1: false
      });
      console.log(this.data.current)
      var self = this
      if (this.data.current == this.data.problem.answer) {
        this.handleSuccess()
        wx.request({
          url: app.globalData.url1 +'ChangePointAction', //仅为示例，并非真实的接口地址
          data: {
            username: getApp().globalData.username,
            point: self.data.problem.point,
            mode: '+',
            q_id: self.data.id,
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
          },
          success: function (res) {
            setTimeout(function () {
              //要延时执行的代码
              wx.navigateTo({
                url: '../index/index',
              })
            }, 1000) //延迟时间 这里是1秒
          },
          fail: function (e) {
            console.log('failed')
          },
        })
      } else {
        this.handleError()
      }
    },
    handleError() {
      $Toast({
        content: '回答错误',
        type: 'error'
      });
    },
    handleSuccess() {
      $Toast({
        content: '回答正确',
        type: 'success'
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

        // console.warn(app.globalData.userInfo);
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
        } else if (this.data.canIUse) {
            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            // 所以此处加入 callback 以防止这种情况
            app.userInfoReadyCallback = res => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true,
                })
            }
        } else {
            // 在没有 open-type=getUserInfo 版本的兼容处理
            wx.getUserInfo({
                success: res => {
                    app.globalData.userInfo = res.userInfo
                    this.setData({
                        userInfo: res.userInfo,
                        hasUserInfo: true
                    })
                }
            })
        }
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
        // console.warn(this.data.postId);
        return {
            title: this.data.postTitle,
            path: '/pages/post/post?postId=' + this.data.postId,
            imageUrl: this.data.postThumbnail,
        }
    },

    getUserInfo: function (e) {
        // console.log(e)
        app.globalData.userInfo = e.detail.userInfo;
        // app.globalData.nickName = e.detail.userInfo.nickName;
        // app.globalData.avatarUrl = e.detail.userInfo.avatarUrl;
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        });
    },

    /**
     * 文章详情请求--接口调用成功处理
     */
    successFunPost: function (res, selfObj) {
        var that = this;

        // console.warn(res.data);
        var createTime = res.data.createTime;
        // time.customFormatTime(createTime, 'Y-M-D h:m:s');
        // 当前时间的日期格式
        // var date = new Date();
        // console.log(time.formatTime(date+"123"));

        that.setData({
            postTitle: res.data.title,
            postVisits: res.data.visits,
            postLikes: res.data.likes,
            postContent: res.data.originalContent,
            postDate: time.customFormatTime(createTime, 'Y-M-D'),
            postTags: res.data.tags,
            postThumbnail: res.data.thumbnail,
        })
        // console.warn(postTags);

    },
    /**
     * 文章详情请求--接口调用失败处理
     */
    failFunPost: function (res, selfObj) {
        console.error('failFunPosts', res)
    },


    /**
     * 评论列表请求--接口调用成功处理
     */
    successComment: function (res, selfObj) {
        var that = this;
        // console.warn(res.data);
        var list = res.data.content;
        for (let i = 0; i < list.length; ++i) {
            list[i].createTime = time.customFormatTime(list[i].createTime, 'Y-M-D  h:m:s');
            list[i].falg = true;
            if (list[i].isAdmin) {
                list[i].email = '';
                list[i].authorUrl = 'https://cn.gravatar.com/avatar/3958035fa354403fa9ca3fca36b08068?s=256&d=mm';
            }
        }

        list[list.length - 1].falg = false;
        that.setData({
            commentList: res.data.content,
        })
    },
    /**
     * 评论列表请求--接口调用失败处理
     */
    failComment: function (res, selfObj) {
        console.error('failComment', res)
    },
    /**
     * 评论模块
     */
    Comment: function (e) {
        var content = e.detail.value.replace(/\s+/g, '');
        // console.log(content);
        var that = this;
        that.setData({
            CommentContent: content,
        });
    },

    CommentSubmit: function (e) {

        // console.warn(this.userInfo);
        var that = this;

        if (!that.data.CommentContent) {
            wx.showToast({
                title: '评论内容不能为空！',
                icon: 'none',
                duration: 2000
            })
            // console.error("评论内容为空!");
        } else {
            that.setData({
                CommentShow: true,
            });
            that.data.ButtonTimer = setInterval(function () {
                if (countdown == 0) {
                    that.setData({
                        CommentShow: false,
                    })
                    countdown = 60;
                    clearInterval(that.data.ButtonTimer);
                    return;
                } else {
                    that.setData({
                        LastTime: countdown
                    });
                    // console.warn(countdown);
                    countdown--;
                }
            }, 1000)
            // console.warn(that.data.CommentContent);

            var urlPostList = app.globalData.url + '/api/content/posts/comments';
            var token = app.globalData.token;
            var params = {
                author: app.globalData.userInfo.nickName,
                authorUrl: "https://github.com/aquanlerou/WeHalo",
                content: that.data.CommentContent,
                email: "aquanlerou@eunji.cn",
                parentId: 0,
                postId: that.data.postId,
            };


            //@todo 搜索文章网络请求API数据
            request.requestPostApi(urlPostList, token, params, this, this.successSendComment, this.failSendComment);
        }


        
    },

    CommentSubmitTips: function() {
        wx.showToast({
            title: this.data.LastTime + "s 后再次评论",
            icon: 'none',
            duration: 1000
        })
    },

    Likes: function() {
        wx.showToast({
            title: "文章点赞功能开发中...",
            icon: 'none',
            duration: 2000
        })
    },


    successSendComment: function (res, selfObj) {
        var that = this;
        // console.warn(res.data);
        var token = app.globalData.token;
        var urlContent = app.globalData.url + '/api/content/posts/' + that.data.postId;
        var urlComments = urlContent + '/comments/list_view';
        var params = {};
        //@todo 评论列表网络请求API数据
        request.requestGetApi(urlComments, token, params, this, this.successComment, this.failComment);
    },

    failSendComment: function (res, selfObj) {
        console.error('failComment', res)
    },

     /**
     * 评论开关按钮回调
     */
    successSwitch: function(res, selfObj) {
        var that = this;
        // console.warn(res.data);
        that.setData({
            CommentSwitch: !res.data,
        });
    },
    failSwitch: function (res, selfObj) {
        console.error('failSwitch', res)
    },


})