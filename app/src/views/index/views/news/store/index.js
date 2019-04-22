import api from '@/api'
var Qs = require('qs');
export default {
    name:'news',
    namespaced:true,
    state:() => ({
        news:{},
        comment : {"page":1,"total_pages":1,"comment_exist":true,"comments":[{"id":34491094,"compiled_content":"我觉得是作者太自私了，我哥哥有两个儿子，大儿子自从有了弟弟后也经常发生这样的事，父母教育哥哥让着弟弟是一种美德，虽然是养母，但是他们并没有少给你什么，弟弟的出生本来就需要分一部分爱给弟弟，你这简直就是只想着自己，还成长？","floor":11,"note_id":38058955,"user_id":14226304,"created_at":"2019-01-25T10:56:44.000+08:00","images_count":0,"images":[],"user":{"id":14226304,"slug":"5de8a9f4e3b5","nickname":"小混蛋_dabc","avatar":"http://upload.jianshu.io/users/upload_avatars/14226304/7594b8ac-dd55-4f66-8bd2-927f9ec0e9d7","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":3,"children":[{"id":34579726,"compiled_content":"<a href=\"/u/5de8a9f4e3b5\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"5de8a9f4e3b5\">@小混蛋_dabc</a> 我们都不是经历其中的人，也无法去体验到他的真实生活与感受，所以至于是不是他本人自私，也不好盲目判断的呢。","user_id":9300674,"parent_id":34491094,"images_count":0,"images":[],"created_at":"2019-01-27T01:57:21.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}},{"id":34637746,"compiled_content":"<a href=\"/u/b7748b86de76\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"b7748b86de76\">@情花初开</a> 我就是根据文章的意思这么一说，明显文中的意思就是嫌弃养母有了弟弟后对自己的关爱变少了，更加偏袒弟弟。说白点不就是不知足嘛，认为自己曾经拥有过的就该永远拥有。那反过来，如果这个养母待他还是那么好，那弟弟肯定不买账啊，又该弟弟说了，标题：父母对我我一个亲身的还不如买来的孩子。知足常乐","user_id":14226304,"parent_id":34491094,"images_count":0,"images":[],"created_at":"2019-01-28T09:58:30.000+08:00","user":{"id":14226304,"slug":"5de8a9f4e3b5","nickname":"小混蛋_dabc"}},{"id":34640562,"compiled_content":"<a href=\"/u/5de8a9f4e3b5\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"5de8a9f4e3b5\">@小混蛋_dabc</a> 莫激动，我只是阐述了我的这位朋友的故事。其它的我只能说“不是事中人，不知其中事”吧。","user_id":9300674,"parent_id":34491094,"images_count":0,"images":[],"created_at":"2019-01-28T11:07:56.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":34457521,"compiled_content":"实际上，有时候亲生父母还不如养母，那种痛还是透过骨髓也不能说。想起来泪奔，只能好好抱抱自己，愿我们都安好！","floor":10,"note_id":38058955,"user_id":15694215,"created_at":"2019-01-24T19:06:35.000+08:00","images_count":0,"images":[],"user":{"id":15694215,"slug":"f230a146bcd8","nickname":"如一_8663","avatar":"http://upload.jianshu.io/users/upload_avatars/15694215/951d79b0-7fbc-447b-9294-ea132eab3969","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":2,"children":[{"id":34579727,"compiled_content":"<a href=\"/u/f230a146bcd8\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"f230a146bcd8\">@如一_8663</a> 抱抱👄","user_id":9300674,"parent_id":34457521,"images_count":0,"images":[],"created_at":"2019-01-27T01:57:35.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}},{"id":34607164,"compiled_content":"<a href=\"/u/b7748b86de76\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"b7748b86de76\">@情花初开</a> 抱抱","user_id":15694215,"parent_id":34457521,"images_count":0,"images":[],"created_at":"2019-01-27T17:14:43.000+08:00","user":{"id":15694215,"slug":"f230a146bcd8","nickname":"如一_8663"}}]},{"id":34428777,"compiled_content":"贩卖人口是犯罪，即使他们是你的亲生父母。","floor":9,"note_id":38058955,"user_id":12921048,"created_at":"2019-01-24T08:46:50.000+08:00","images_count":0,"images":[],"user":{"id":12921048,"slug":"99d90584d191","nickname":"张风铎","avatar":"http://upload.jianshu.io/users/upload_avatars/12921048/a5880c16-5086-4f69-80a5-5c4ce1db00e7.jpg","is_author":false,"badge":{"id":83,"icon":"excellent","image":null,"color":null,"text":"日更达人","created_at":"2018-07-16T17:39:41.000+08:00","updated_at":"2018-07-16T17:39:41.000+08:00","intro_url":"https://www.jianshu.com/mobile/campaign/day_by_day/join","count_limit":1,"note_recommendation":false,"note_recommendation_score":"0.0","approve_submission":false}},"liked":false,"likes_count":2,"children_count":2,"children":[{"id":34579730,"compiled_content":"<a href=\"/u/99d90584d191\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"99d90584d191\">@张风铎</a> 或许也有太多的迫不得已……","user_id":9300674,"parent_id":34428777,"images_count":0,"images":[],"created_at":"2019-01-27T01:58:02.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}},{"id":34604195,"compiled_content":"<a href=\"/u/b7748b86de76\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"b7748b86de76\">@情花初开</a> 哪有那么多迫不得已？就算真的有，为啥不把自己卖了？","user_id":12921048,"parent_id":34428777,"images_count":0,"images":[],"created_at":"2019-01-27T16:00:19.000+08:00","user":{"id":12921048,"slug":"99d90584d191","nickname":"张风铎"}}]},{"id":33900986,"compiled_content":"孩子认为父母不容易一碗水端平！","floor":8,"note_id":38058955,"user_id":7356814,"created_at":"2019-01-14T14:42:27.000+08:00","images_count":0,"images":[],"user":{"id":7356814,"slug":"29c9293258b5","nickname":"立邦柒","avatar":"http://upload.jianshu.io/users/upload_avatars/7356814/06c1ffa5-9c82-4995-a7b6-107c3f597dc1.jpg","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":1,"children":[{"id":33923829,"compiled_content":"<a href=\"/u/29c9293258b5\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"29c9293258b5\">@立邦柒</a> 嘿嘿，其实心态好，烦恼就少了🙂","user_id":9300674,"parent_id":33900986,"images_count":0,"images":[],"created_at":"2019-01-14T21:18:47.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33892405,"compiled_content":"我想，即便你是亲生的，养父母的对待方法也是一样的，不是因为你是领养的，这是每个父母都会做的举动，不要因为自己的身份就想多，你还是你，还是这个家的一份子，没有什么区别，因为心不同，看事的角度就不同，敞开心扉，你会看到更美的天空。","floor":7,"note_id":38058955,"user_id":2705699,"created_at":"2019-01-14T11:46:01.000+08:00","images_count":0,"images":[],"user":{"id":2705699,"slug":"65eaf3c757e0","nickname":"云隐雾轻","avatar":"http://upload.jianshu.io/users/upload_avatars/2705699/67dee9ba6c82.jpeg","is_author":false,"badge":{"id":83,"icon":"excellent","image":null,"color":null,"text":"日更达人","created_at":"2018-07-16T17:39:41.000+08:00","updated_at":"2018-07-16T17:39:41.000+08:00","intro_url":"https://www.jianshu.com/mobile/campaign/day_by_day/join","count_limit":1,"note_recommendation":false,"note_recommendation_score":"0.0","approve_submission":false}},"liked":false,"likes_count":1,"children_count":1,"children":[{"id":33923897,"compiled_content":"<a href=\"/u/65eaf3c757e0\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"65eaf3c757e0\">@云隐雾轻</a> 嗯呢，心态很重要🙂","user_id":9300674,"parent_id":33892405,"images_count":0,"images":[],"created_at":"2019-01-14T21:19:30.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33850359,"compiled_content":"加油","floor":6,"note_id":38058955,"user_id":10980766,"created_at":"2019-01-13T18:14:03.000+08:00","images_count":0,"images":[],"user":{"id":10980766,"slug":"bdec09143f5c","nickname":"护城河_925","avatar":"http://upload.jianshu.io/users/upload_avatars/10980766/0f46aa00-0ef7-4aa9-96e6-d98f1d11d8a7.jpg","is_author":false,"badge":{"id":83,"icon":"excellent","image":null,"color":null,"text":"日更达人","created_at":"2018-07-16T17:39:41.000+08:00","updated_at":"2018-07-16T17:39:41.000+08:00","intro_url":"https://www.jianshu.com/mobile/campaign/day_by_day/join","count_limit":1,"note_recommendation":false,"note_recommendation_score":"0.0","approve_submission":false}},"liked":false,"likes_count":0,"children_count":1,"children":[{"id":33923907,"compiled_content":"<a href=\"/u/bdec09143f5c\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"bdec09143f5c\">@护城河_925</a> 谢谢😊","user_id":9300674,"parent_id":33850359,"images_count":0,"images":[],"created_at":"2019-01-14T21:19:41.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33696359,"compiled_content":"总归养父母没有虐待你就好！有很多孩子亲生的还虐待的，你好好珍惜吧，路靠自己走。相信你前面的路更美好。","floor":5,"note_id":38058955,"user_id":12084502,"created_at":"2019-01-10T21:03:28.000+08:00","images_count":0,"images":[],"user":{"id":12084502,"slug":"9f0f6b0dfe89","nickname":"刘趁娜","avatar":"http://cdn2.jianshu.io/assets/default_avatar/3-9a2bcc21a5d89e21dafc73b39dc5f582.jpg","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":1,"children":[{"id":33714308,"compiled_content":"<a href=\"/u/9f0f6b0dfe89\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"9f0f6b0dfe89\">@刘趁娜</a> 是的，谢谢🙏","user_id":9300674,"parent_id":33696359,"images_count":0,"images":[],"created_at":"2019-01-11T08:22:40.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33690671,"compiled_content":"有些事，不提起，就无所谓，一旦提起，就是酸涩的眼泪。","floor":4,"note_id":38058955,"user_id":11069056,"created_at":"2019-01-10T19:29:01.000+08:00","images_count":0,"images":[],"user":{"id":11069056,"slug":"44476ad89d47","nickname":"我不说123","avatar":"http://upload.jianshu.io/users/upload_avatars/11069056/ee1af043-ba36-428e-82b5-f3339c02058a.jpg","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":1,"children":[{"id":33691379,"compiled_content":"<a href=\"/u/44476ad89d47\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"44476ad89d47\">@我不说123</a> 是的呢，心事这东西，捂着嘴，它却从眼里跑了出来","user_id":9300674,"parent_id":33690671,"images_count":0,"images":[],"created_at":"2019-01-10T19:40:16.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33673676,"compiled_content":"明白就好，人总归是要成长的，依靠的只能是自己。","floor":3,"note_id":38058955,"user_id":12328610,"created_at":"2019-01-10T13:48:50.000+08:00","images_count":0,"images":[],"user":{"id":12328610,"slug":"9f883c94e765","nickname":"小鲤鱼跃龙门","avatar":"http://upload.jianshu.io/users/upload_avatars/12328610/3d5b6d31-6094-4c71-a597-4406f40d37fc.jpg","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":1,"children":[{"id":33674162,"compiled_content":"<a href=\"/u/9f883c94e765\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"9f883c94e765\">@小鲤鱼跃龙门</a> 嗯嗯，路总归要自己走","user_id":9300674,"parent_id":33673676,"images_count":0,"images":[],"created_at":"2019-01-10T13:58:54.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}}]},{"id":33454066,"compiled_content":"如果换作我是养父母，绝对不会因为有了亲生的而去冷落养子！因为那是忘恩呀！","floor":2,"note_id":38058955,
        "user_id":7891764,"created_at":"2019-01-06T15:53:47.000+08:00","images_count":0,"images":[],"user":{"id":7891764,"slug":"5e3983eace39","nickname":"原军鹏1","avatar":"http://upload.jianshu.io/users/upload_avatars/7891764/001d07ed-b052-4aa4-af15-ab8d28e3a890.jpg","is_author":false,"badge":null},"liked":false,"likes_count":0,"children_count":2,"children":[{"id":33500501,"compiled_content":"<a href=\"/u/5e3983eace39\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"5e3983eace39\">@原军鹏1</a> 是的，如果换作是我，我还是会像亲生孩子一样好好爱养子","user_id":9300674,"parent_id":33454066,"images_count":0,"images":[],"created_at":"2019-01-07T12:40:02.000+08:00","user":{"id":9300674,"slug":"b7748b86de76","nickname":"情花初开"}},{"id":33720575,"compiled_content":"<a href=\"/u/b7748b86de76\" class=\"maleskine-author\" target=\"_blank\" data-user-slug=\"b7748b86de76\">@情花初开</a> 都以为自己和别人不一样，说到底是自己没走到那一步罢了。","user_id":1371781,"parent_id":33454066,"images_count":0,"images":[],"created_at":"2019-01-11T10:24:50.000+08:00","user":{"id":1371781,"slug":"29a079927fd3","nickname":"鹤闻"}}]}]},
    }),
    actions : {
        getNews({commit},data){
            return api.instance({
                method:'get',
                url:`/article/${data.id}`
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data){
                        commit('setNews',res.data)
                    }else{
                        commit('setNews',{})
                    }
                }
                return res
            })
        },
        getComment({commit},data) {
            if(!data.sortType){
                data.sortType = 'asc'
            }
            
            return api.instance({
                method:'get',
                url:`/article/getComment?id=${data.id}&pageNo=${data.pageNo}&sortType=${data.sortType}`
            })
            .then((res)=>{
                if(res.status==200){
                    if(res.data){
                        commit('getComment',res.data)
                    }else{
                        commit('getComment',{})
                    }
                }
                return res
            })
        },
        articleLike({commit},data) {
            return api.instance({
                method:'post',
                url:`/article/like`,
                data:data
            })
            .then((res)=>{
                if(res.status==200){
                   
                }
                return res
            })
        },
        //评论
        _articleReply({commit,dispatch},data) {
            return api.instance({
                method:'post',
                url:`/article/reply`,
                data:Qs.stringify(data)
            })
            
        },
        //点赞
        _articleLike({commit,dispatch},data){
            return api.instance({
                method:'post',
                url:`/article/like`,
                data:Qs.stringify(data)
            })
        }
    },
    mutations:{
        setNews(state,data) {
            state.news = data;
        },
        getComment(state,data) {
            state.comment = data;
        }
    }
}